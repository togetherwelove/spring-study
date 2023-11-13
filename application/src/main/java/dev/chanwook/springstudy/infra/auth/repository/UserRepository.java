package dev.chanwook.springstudy.infra.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.chanwook.springstudy.infra.auth.repository.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
