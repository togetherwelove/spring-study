package dev.chanwook.springstudy.infra.auth.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.chanwook.springstudy.infra.auth.repository.entity.Tokens;

@Repository
public interface TokenCustom {
	List<Tokens> findAllValidTokenByUsername(String email);
}
