package dev.chanwook.springstudy.domain.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
	private String email;
	private Long id;
	private String name;
	private String password;
}
