package br.com.hfsframework.oauth.service;

import java.util.Optional;

import br.com.hfsframework.base.IBaseBusinessService;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IUserRepository;

public interface IUserService extends IBaseBusinessService<User, Long, IUserRepository> {

	IUserRepository getRepositorio();
	
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
}
