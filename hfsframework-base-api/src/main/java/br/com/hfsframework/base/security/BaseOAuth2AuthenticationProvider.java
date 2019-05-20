package br.com.hfsframework.base.security;

import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

		try {
			UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
	
	        String username = auth.getPrincipal().toString();
	        String password = auth.getCredentials().toString();
	
	        BaseOAuth2RestUser user = this.login(env, projectId, username, password);
	        
            if (user == null) {
                throw new UsernameNotFoundException(String.format(URLEncoder.encode("Invalid username or password", "UTF-8"), authentication.getPrincipal()));
            }

			if (user.isAuthenticated()) {
/*				
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        
				Object details = authentication.getDetails();        
				if ( details instanceof OAuth2AuthenticationDetails ){
				    OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)details;

				    Map<String, Object> decodedDetails = (Map<String, Object>)oAuth2AuthenticationDetails.getDecodedDetails();

				    System.out.println( "My custom claim value: " + decodedDetails.get("MyClaim") );
				}*/  				
				
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
				return token;
			} else {		
				log.error("Token vazio!");
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
