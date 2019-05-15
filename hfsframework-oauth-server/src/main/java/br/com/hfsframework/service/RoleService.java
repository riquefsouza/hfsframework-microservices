package br.com.hfsframework.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.domain.Role;
import br.com.hfsframework.repository.IRoleRepository;

@Service
public class RoleService extends BaseBusinessService<Role, Long, IRoleRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
