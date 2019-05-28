package br.com.hfsframework.oauth.service;

import java.util.Optional;

import br.com.hfsframework.base.IBaseBusinessService;
import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.repository.IRoleRepository;

public interface IRoleService extends IBaseBusinessService<Role, Long, IRoleRepository> {

	Optional<Role> findByName(String name);
	
}
