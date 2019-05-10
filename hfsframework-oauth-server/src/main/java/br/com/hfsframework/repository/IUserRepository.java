package br.com.hfsframework.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.domain.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
}
