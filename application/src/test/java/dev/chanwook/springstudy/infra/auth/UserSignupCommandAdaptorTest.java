package dev.chanwook.springstudy.infra.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.chanwook.springstudy.domain.auth.User;
import dev.chanwook.springstudy.infra.auth.repository.UserRepository;
import dev.chanwook.springstudy.infra.auth.repository.entity.Users;
import dev.chanwook.springstudy.infra.config.QueryDslConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ImportAutoConfiguration({QueryDslConfig.class, UserSignupCommandAdaptor.class})
public class UserSignupCommandAdaptorTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSignupCommandAdaptor userSignupCommandAdaptor;

	Function<User, Users> usersMapper = user -> Users.builder()
			.id(user.getId())
			.build();

	@Test
	@DisplayName("유저 추가 기능 테스트")
	void addUser() {
		User user = User.builder()
				.email("user@user.dev")
				.password("qwer1234")
				.name("dean")
				.build();

		Optional<User> savedUser = userSignupCommandAdaptor.addUser(user);

		assertTrue(savedUser.isPresent());

		userRepository.delete(usersMapper.apply(savedUser.get()));
	}
}
