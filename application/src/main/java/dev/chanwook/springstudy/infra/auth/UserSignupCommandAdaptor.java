package dev.chanwook.springstudy.infra.auth;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.chanwook.springstudy.domain.auth.User;
import dev.chanwook.springstudy.domain.auth.infra.UserSignupCommandPort;
import dev.chanwook.springstudy.infra.auth.repository.UserRepository;
import dev.chanwook.springstudy.infra.auth.repository.entity.Users;

@Component
public class UserSignupCommandAdaptor implements UserSignupCommandPort {

	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserSignupCommandAdaptor(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public Optional<User> addUser(User user) {
	    if (userRepository.existsByEmail(user.getEmail())) {
	        return Optional.empty();
	    }

	    Users savedUser = userRepository.save(usersMapper.apply(user));
	    return Optional.of(userMapper.apply(savedUser));
	}


    Function<User, Users> usersMapper = user -> Users.builder()
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .name(user.getName())
            .build();

    Function<Users, User> userMapper = users -> User.builder()
            .id(users.getId())
            .email(users.getEmail())
            .name(users.getName())
            .build();

}
