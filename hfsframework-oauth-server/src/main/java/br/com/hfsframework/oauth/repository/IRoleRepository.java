package br.com.hfsframework.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.oauth.domain.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(String name);
}
