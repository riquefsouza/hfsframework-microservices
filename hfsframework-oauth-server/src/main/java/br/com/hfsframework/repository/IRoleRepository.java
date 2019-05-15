package br.com.hfsframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.domain.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	
}
