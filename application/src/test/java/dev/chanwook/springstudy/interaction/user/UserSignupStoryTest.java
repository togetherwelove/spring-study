package dev.chanwook.springstudy.interaction.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.chanwook.springstudy.api.auth.dto.UserSignupRequest;
import dev.chanwook.springstudy.api.config.common.ResponseCodeEnum;
import dev.chanwook.springstudy.api.config.handler.DefaultResponseBody;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSignupStoryTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void userSignupStory() {
		UserSignupRequest dto = UserSignupRequest.builder()
				.name("dean")
				.email("user@user.dev")
				.password("qwer1234")
				.passwordVerify("qwer1234")
				.build();

		// check
		ResponseEntity<DefaultResponseBody> commandCheckInputResponse = restTemplate.postForEntity(
				"/auth/signup/check", dto, DefaultResponseBody.class);
		assertEquals(HttpStatus.OK, commandCheckInputResponse.getStatusCode());
		DefaultResponseBody checkInputResponseBody = commandCheckInputResponse.getBody();
		assertEquals(ResponseCodeEnum.SUCCESS, checkInputResponseBody.getCode());

		// request
		ResponseEntity<DefaultResponseBody> commandRequestSignupResponse = restTemplate.postForEntity(
				"/auth/signup/request", dto, DefaultResponseBody.class);
		assertEquals(HttpStatus.OK, commandRequestSignupResponse.getStatusCode());
		DefaultResponseBody requestSignupResponseBody = commandRequestSignupResponse.getBody();
		assertEquals(ResponseCodeEnum.SUCCESS, requestSignupResponseBody.getCode());

	}
}
