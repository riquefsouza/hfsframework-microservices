package br.com.hfsframework.oauth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.oauth.client.domain.RoleDTO;
import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.repository.IRoleRepository;

@Service
public class RoleService extends BaseBusinessService<RoleDTO, Role, Long, IRoleRepository> 
	implements IRoleService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public Optional<Role> findByName(String name) {
		return getRepositorio().findByName(name);
	}

}
