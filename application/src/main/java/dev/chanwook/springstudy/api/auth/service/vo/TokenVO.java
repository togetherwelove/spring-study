package dev.chanwook.springstudy.api.auth.service.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {
	private String accessToken;
	private String refreshToken;
}
