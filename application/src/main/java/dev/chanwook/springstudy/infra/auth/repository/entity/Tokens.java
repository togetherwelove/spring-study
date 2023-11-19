package dev.chanwook.springstudy.infra.auth.repository.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.chanwook.springstudy.infra.auth.repository.entity.type.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {

	private boolean expired;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id private long id;

	private boolean revoked;

	private String token;

	@Enumerated(EnumType.STRING)
	private TokenType tokenType;

	private String username;
}
