package br.com.hfsframework.oauth.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.repository.IRoleRepository;

@Service
public class RoleService extends BaseBusinessService<Role, Long, IRoleRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
