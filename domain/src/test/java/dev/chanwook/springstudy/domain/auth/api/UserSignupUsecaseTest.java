package dev.chanwook.springstudy.domain.auth.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.chanwook.springstudy.domain.auth.api.service.InvalidInputException;
import dev.chanwook.springstudy.domain.auth.api.service.UserSignupCommand;
import dev.chanwook.springstudy.domain.auth.api.service.UserSignupService;
import dev.chanwook.springstudy.domain.auth.infra.SmtpPort;
import dev.chanwook.springstudy.domain.auth.infra.UserSignupCommandPort;
import dev.chanwook.springstudy.domain.auth.infra.UserSignupRequsetException;

@ExtendWith(MockitoExtension.class)
public class UserSignupUsecaseTest {

	@InjectMocks
	UserSignupService userSignupService;
	
	@Mock
	UserSignupCommandPort userSignupCommandPort;
	
	@Mock
	SmtpPort smtpPort;
	
	@ParameterizedTest
	@MethodSource("commandProvider")
	void check(UserSignupCommand command, Boolean expect) {
		if(!expect) {
			assertThrows(InvalidInputException.class,
					() -> userSignupService.checkRequired(command), "");
		} else {
			assertDoesNotThrow(() -> userSignupService.checkRequired(command));
		}
	}
	
	static Stream<Arguments> commandProvider() {
		return Stream.of(
				// 정상
				arguments(UserSignupCommand.builder()
						.email("user@user.dev")
						.password("1234qwer")
						.passwordVerify("1234qwer")
						.build(), true),
				// 이메일 null
				arguments(UserSignupCommand.builder()
						.email(null)
						.password("1234qwer")
						.passwordVerify("1234qwer")
						.build(), false),
				// 비밀번호 null
				arguments(UserSignupCommand.builder()
						.email("user@user.dev")
						.password(null)
						.passwordVerify("1234qwer")
						.build(), false),
				// 비밀번호(확인) null
				arguments(UserSignupCommand.builder()
						.email("user@user.dev")
						.password("1234qwer")
						.passwordVerify(null)
						.build(), false)
				);
	}
	
	@Test
	void invalid_email() {
		UserSignupCommand command = UserSignupCommand.builder()
				.email("useruser.dev") // 올바르지 않은 이메일 형식
				.password("1234qwer")
				.passwordVerify("1234qwer")
				.build();
		
		assertThrowsExactly(InvalidInputException.class,
				() -> userSignupService.requestSignup(command), "");
		verify(userSignupCommandPort, times(0)).addUser(any());
		verify(smtpPort, times(0)).send(any());
	}
	
	@Test
	void invalid_password() {
		UserSignupCommand command = UserSignupCommand.builder()
				.email("user@user.dev")
				.password("1234") // 올바르지 않은 비밀번호 형식
				.passwordVerify("1234")
				.build();
		
		assertThrowsExactly(InvalidInputException.class,
				() -> userSignupService.requestSignup(command), "");
		verify(userSignupCommandPort, times(0)).addUser(any());
		verify(smtpPort, times(0)).send(any());
	}
	
	@Test
	void invalid_passwordVerify() {
		UserSignupCommand command = UserSignupCommand.builder()
				.email("user@user.dev")
				.password("1234qwer")
				.passwordVerify("qwer") // 비밀번호 확인 불일치
				.build();
		
		assertThrowsExactly(InvalidInputException.class,
				() -> userSignupService.requestSignup(command), "");
		verify(userSignupCommandPort, times(0)).addUser(any());
		verify(smtpPort, times(0)).send(any());
	}
	
	@Test
	void failed_addUser() {
		UserSignupCommand command = UserSignupCommand.builder()
				.email("user@user.dev")
				.password("1234qwer")
				.passwordVerify("1234qwer") // 비밀번호 확인 불일치
				.build();
		
		assertThrowsExactly(UserSignupRequsetException.class,
				() -> userSignupService.requestSignup(command), "");
		verify(userSignupCommandPort, times(1)).addUser(any());
		verify(smtpPort, times(0)).send(any());
	}
	
}
