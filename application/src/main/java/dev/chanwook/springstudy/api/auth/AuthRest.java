package dev.chanwook.springstudy.api.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.chanwook.springstudy.api.auth.dto.AuthRequest;
import dev.chanwook.springstudy.api.auth.dto.AuthResponse;
import dev.chanwook.springstudy.api.auth.service.AuthService;
import dev.chanwook.springstudy.api.auth.service.vo.TokenVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRest {
	private final AuthService authService;

	// authentication	: 로그인, 인증
	// authority		: 회원가입, 인가
	@Operation(summary = "로그인", description = "로그인 후 토큰 발급", tags = {"로그인"})
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {

		TokenVO authResponse = authService.authenticate(login);

		ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", authResponse.getRefreshToken())
				.httpOnly(true)
				.secure(true)
				.path("/")
				.maxAge(86400)
				.domain("localhost")
				.build();

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
				.body(new AuthResponse(authResponse.getAccessToken()));
	}

	@Operation(summary = "토큰 재발급", description = "액세스 토큰 만료 시 재발급", tags = { "로그인" })
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(@CookieValue String refreshToken, HttpServletResponse response) throws IOException {
		String accessToken = "";
		Optional<String> refreshedAccessToken = authService.refreshToken(refreshToken, response);
		if (refreshedAccessToken.isPresent()) {
			accessToken = refreshedAccessToken.get();
		}
		return ResponseEntity.ok().body(new AuthResponse(accessToken));
	}
	
	// Logout은 SecurityConfig에 "auth/logout/"으로 설정되어있음
}
