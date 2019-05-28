package br.com.hfsframework.base.security;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.hfsframework.base.client.BaseOAuth2RestTemplateClient;

@Component
public class BaseOAuth2AuthenticationProvider extends BaseOAuth2RestTemplateClient implements AuthenticationProvider {

	public static final Logger log = LoggerFactory.getLogger(BaseOAuth2AuthenticationProvider.class);
	
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
	
	        String username = auth.getPrincipal().toString();
	        String password = auth.getCredentials().toString();
	
	        BaseOAuth2RestUser user = this.login(env, projectId, username, password);
	        
            if (user == null) {
                throw new UsernameNotFoundException(
                		String.format(URLEncoder.encode("Invalid username or password", "UTF-8"), 
                				authentication.getPrincipal()));
            }

			if (user.isAuthenticated()) {
				String sToken = user.getAccessToken().getValue();
				DecodedJwt decodedJwt = DecodedJwt.getDecodedJwt(sToken);
				String[] roles = new String[decodedJwt.getAuthorities().size()];
				roles = decodedJwt.getAuthorities().toArray(roles);
				
				user = new BaseOAuth2RestUser(user, roles);
				
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, 
						user.getPassword(), AuthorityUtils.createAuthorityList(roles));
				return token;
			} else {		
				log.error("Error from BaseOAuth2RestTemplateClient.login: " + user.getMessageException());				
				return null;
			}
        } catch (Exception e) {
            log.error( "Error in BaseOAuth2AuthenticationProvider.authenticate()", e);
            throw new AuthenticationServiceException(e.getMessage());
        }
			
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
