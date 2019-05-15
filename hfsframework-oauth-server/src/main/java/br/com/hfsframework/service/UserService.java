package br.com.hfsframework.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.domain.User;
import br.com.hfsframework.repository.IUserRepository;

@Service
public class UserService extends BaseBusinessService<User, Long, IUserRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
