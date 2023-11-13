package dev.chanwook.springstudy.domain.auth.infra;

import java.util.Optional;

import dev.chanwook.springstudy.domain.auth.User;

public interface UserSignupCommandPort {
	Optional<User> addUser(User user);
}
