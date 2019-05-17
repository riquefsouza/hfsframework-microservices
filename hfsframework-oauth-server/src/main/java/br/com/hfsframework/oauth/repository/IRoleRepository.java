package br.com.hfsframework.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.oauth.domain.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	
}
