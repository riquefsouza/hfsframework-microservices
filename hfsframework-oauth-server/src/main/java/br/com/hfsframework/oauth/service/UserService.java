package br.com.hfsframework.oauth.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IUserRepository;

@Service
public class UserService extends BaseBusinessService<User, Long, IUserRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
