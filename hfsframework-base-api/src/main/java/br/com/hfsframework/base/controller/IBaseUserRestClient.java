package br.com.hfsframework.base.controller;

import java.util.Optional;

import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.IBaseRestClient;
import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.oauth.client.domain.User;

public interface IBaseUserRestClient extends IBaseRestClient<User, Long> {

	public boolean init(String authServerURL, String sAccesToken) throws RestClientException;
	
	public Optional<User> findByUsername(String username) throws RestClientException;
	
	public User getLoggedUser(Optional<BaseOAuth2RestUser> principal) throws RestClientException;
	
	public Optional<User> findByEmail(String email) throws RestClientException;
}
