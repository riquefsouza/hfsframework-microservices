package br.com.hfsframework.base.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.hfsframework.base.client.BaseOAuth2RestTemplateClient;

@Component
public class BaseOAuth2AuthenticationProvider extends BaseOAuth2RestTemplateClient implements AuthenticationProvider {

	private static final Logger log = LogManager.getLogger(BaseOAuth2AuthenticationProvider.class);
	
	private Environment env;
	
	private String projectId;
	
	public void setInfo(Environment env, String projectId) {
		this.env = env;
		this.projectId = projectId;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        BaseOAuth2RestUser user = this.login(env, projectId, username, password);
        
		if (user.isAuthenticated()) {
			return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		} else {		
			log.error("Token vazio!");
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
