package dev.chanwook.springstudy.api.auth.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import dev.chanwook.springstudy.api.auth.dto.AuthRequest;
import dev.chanwook.springstudy.api.auth.service.vo.TokenVO;
import dev.chanwook.springstudy.api.config.service.JwtService;
import dev.chanwook.springstudy.domain.auth.infra.UserSignupRequsetException;
import dev.chanwook.springstudy.infra.auth.repository.TokenRepository;
import dev.chanwook.springstudy.infra.auth.repository.UserRepository;
import dev.chanwook.springstudy.infra.auth.repository.entity.Tokens;
import dev.chanwook.springstudy.infra.auth.repository.entity.Users;
import dev.chanwook.springstudy.infra.auth.repository.entity.type.TokenType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;

	public TokenVO authenticate(AuthRequest login) {

		Users user = Users.builder()
				.email(login.getEmail())
				.password(login.getPassword())
				.build();

		try {
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new UserSignupRequsetException("비밀번호가 불일치합니다.");
		}

		String accessToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveToken(user, accessToken);
		saveRefreshToken(user, refreshToken);
		return new TokenVO(accessToken, refreshToken);
	}

	public Optional<String> refreshToken(String refreshToken, HttpServletResponse response) throws IOException {
		String accessToken = null;
		if (!ObjectUtils.isEmpty(refreshToken)) {
			final String username = jwtService.extractUsername(refreshToken);
			if (username != null) {
				var user = userRepository.findByEmail(username);
				List<Tokens> validResfreshTokens = tokenRepository.findByTokenAndUsernameAndRevoked(refreshToken, username, false);
				if (user.isPresent() && validResfreshTokens.size() > 0 && jwtService.isTokenValid(refreshToken, user.get())) {
					accessToken = jwtService.generateToken(user.get());
					saveToken(user.get(), accessToken);
				}
			}
		}
		return accessToken == null ? Optional.empty() : Optional.of(accessToken);
	}

	private void revokeAllUserTokens(Users user) {
		List<Tokens> validTokens = tokenRepository.findAllValidTokenByUsername(user.getUsername());
		if (!validTokens.isEmpty()) {
			validTokens.forEach(t -> {
				t.setExpired(true);
				t.setRevoked(true);
				tokenRepository.save(t);
			});
		}
	}

	private void saveRefreshToken(Users user, String jwtToken) {
		Tokens token = Tokens.builder()
				.token(jwtToken)
				.tokenType(TokenType.REFRESH)
				.expired(false)
				.revoked(false)
				.username(user.getUsername())
				.build();
		tokenRepository.save(token);
	}

	private void saveToken(Users user, String jwtToken) {
		Tokens token = Tokens.builder()
				.token(jwtToken)
				.tokenType(TokenType.ACCESS)
				.expired(false)
				.revoked(false)
				.username(user.getUsername())
				.build();
		tokenRepository.save(token);
	}
}
