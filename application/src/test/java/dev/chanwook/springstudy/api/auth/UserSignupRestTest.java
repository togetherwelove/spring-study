package dev.chanwook.springstudy.api.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.chanwook.springstudy.api.config.service.JwtService;
import dev.chanwook.springstudy.domain.auth.api.UserSignupUsecase;
import dev.chanwook.springstudy.domain.auth.api.service.UserSignupCommand;

@WebMvcTest(controllers = UserSignupRest.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserSignupRestTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	UserSignupUsecase userSignupService;
	
	@MockBean
	JwtService jwtService;
	
	private UserSignupCommand dto;
	
	@BeforeEach
	void initDto() {
		dto = UserSignupCommand.builder()
				.email("user@user.dev")
				.password("1234qwer")
				.passwordVerify("1234qwer")
				.name("dean").build();
	}
	
	@Test
	@DisplayName("입력 필수값 체크 테스트")
	void checkRequired() throws Exception {
		mockMvc.perform(
				post("/auth/signup/check")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[?(@.message == 'success')]").exists());
		
		verify(userSignupService, times(1)).checkRequired(any());
	}
	
	@Test
	@DisplayName("회원가입 요청 테스트")
	void requestSignup() throws Exception {
		mockMvc.perform(
				post("/auth/signup/request")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[?(@.message == 'success')]").exists());
		
		verify(userSignupService, times(1)).requestSignup(any());		
	}
	
	@Test
	@DisplayName("이메일 재전송 테스트")
	void resend() throws Exception {
		mockMvc.perform(
				post("/auth/signup/resend")
				.contentType(MediaType.APPLICATION_JSON)
				.content("user@user.dev"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[?(@.message == 'success')]").exists());
		
		verify(userSignupService, times(1)).resendMail(any());		
	}
}
