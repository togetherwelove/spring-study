package dev.chanwook.springstudy.domain.auth.api.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupCommand {
	private String email;
	private String name;
	private String password;
	private String passwordVerify;
}
