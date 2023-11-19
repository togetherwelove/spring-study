package dev.chanwook.springstudy.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {
	private String email;
	private String name;
	private String password;
	private String passwordVerify;
}
