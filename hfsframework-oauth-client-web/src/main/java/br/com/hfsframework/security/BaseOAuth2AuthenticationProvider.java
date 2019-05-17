package br.com.hfsframework.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.stereotype.Component;

import br.com.hfsframework.base.BaseOAuth2RestTemplateClient;

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

		try {
			UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

	        String username = String.valueOf(auth.getPrincipal());
	        String password = String.valueOf(auth.getCredentials());

	        //String name = authentication.getName();
			//String password = authentication.getCredentials().toString();

			OAuth2RestTemplate oauth2RestTemplate = restTemplate(env, projectId, username, password);
			String sToken = oauth2RestTemplate.getAccessToken().getValue();
			//Set<String> scopes = oauth2RestTemplate.getAccessToken().getScope();
			
			String[] roles = { "USER" };
			//String[] roles = scopes.toArray(String[]::new);
			String csenha = BCrypt.hashpw(password, BCrypt.gensalt());
			
			if (!sToken.isEmpty()) {
				//SecurityContextHolder.getContext().setAuthentication(authentication);				
				//return new UsernamePasswordAuthenticationToken(name, csenha, AuthorityUtils.createAuthorityList(roles));
				UserDetails user = new User(username, csenha, AuthorityUtils.createAuthorityList(roles));
				return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
			} else {		
				log.error("Token vazio!");
				return null;
			}
		} catch (IllegalStateException | UserRedirectRequiredException | IllegalArgumentException e) {
			log.error(e.getMessage());
			return null; 
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
