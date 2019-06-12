package br.com.hfsframework.oauth.service;

import java.util.Optional;

import br.com.hfsframework.base.IBaseBusinessService;
import br.com.hfsframework.oauth.client.domain.UserDTO;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IUserRepository;

public interface IUserService extends IBaseBusinessService<UserDTO, User, Long, IUserRepository> {

	IUserRepository getRepositorio();
	
	Optional<UserDTO> findByUsername(String username);

	Optional<UserDTO> findByEmail(String email);
}
