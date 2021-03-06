package br.com.hfsframework.base.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.interceptor.BaseHeaderClientHttpRequestInterceptor;
import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.util.network.HttpMessageConverterUtil;

public abstract class BaseOAuth2RestTemplateClient {
	
	private String server, clientId, clientSecret;
	
	private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;
	
	protected enum METHOD_ACTION {
	       ADD_ALL, GET_ALL, GET_BY_ID, UPDATE_BY_ID, DELETE_BY_ID;
		
		@Override
		public String toString() {			
			return "############" + this.name() + "############";
		}
	} 	
    
	protected String json(Object o) throws IOException {
	    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	    mappingJackson2HttpMessageConverter.write(
	            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	    return mockHttpOutputMessage.getBodyAsString();
	}    

	@SuppressWarnings("unchecked")
	private OAuth2RestTemplate restTemplate(String server, String clientId, String clientSecret, 
			String login, String password) throws RestClientException {
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri(server.trim() + "/oauth/token");
		resourceDetails.setClientId(clientId.trim());		
		resourceDetails.setClientSecret(clientSecret.trim());

		List<String> scopes = new ArrayList<String>();
		scopes.add("read");
		scopes.add("write");
		scopes.add("trust");
		resourceDetails.setScope(scopes);

		resourceDetails.setUsername(login.trim());
		resourceDetails.setPassword(password.trim());

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BaseHeaderClientHttpRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));

		OAuth2RestTemplate rt = new OAuth2RestTemplate(resourceDetails);
		rt.setInterceptors(interceptors);
		rt.getMessageConverters().addAll(HttpMessageConverterUtil.getMessageConverters());
		mappingJackson2HttpMessageConverter = (HttpMessageConverter<Object>) rt.getMessageConverters().get(0);
		
		return rt;
	}
	
	private void setProperties(Environment env, String projectId) {
		this.server = env.getRequiredProperty("oauth2."+ projectId + ".server");
		this.clientId = env.getProperty("oauth2."+ projectId + ".client-id");
		this.clientSecret = env.getProperty("oauth2."+ projectId + ".client-secret");		
	}
	
	protected OAuth2RestTemplate restTemplate(Environment env, String projectId, String login, String password) 
			throws RestClientException {
		setProperties(env, projectId);
		return restTemplate(server, clientId, clientSecret, login, password);
	}
	
	protected OAuth2RestTemplate restTemplate(String server, String login, String password) 
			throws RestClientException {
		
		return restTemplate(server, "hfsClient", "hfsSecret", login, password);
	}
	
	private BaseOAuth2RestUser login(String server, String clientId, String clientSecret, 
			String login, String password) {
		
		List<String> listRoles = new ArrayList<String>();
		String[] roles = new String[listRoles.size()];
		//String csenha = BCrypt.hashpw(password, BCrypt.gensalt());
		BaseOAuth2RestUser baseUser = new BaseOAuth2RestUser(login, password, 
				AuthorityUtils.createAuthorityList(roles));
		
		try {
			OAuth2RestTemplate token = restTemplate(server, clientId, clientSecret, login, password);
			String sToken = token.getAccessToken().getValue();

			if (!sToken.trim().isEmpty()) {
				baseUser.setAuthenticated(true);
				baseUser.setUrlAuthorizationServer(this.server);
				baseUser.setAccessToken(token.getAccessToken());
			} else {
				baseUser.setAuthenticated(false);				
			}
			
		} catch (OAuth2AccessDeniedException e) {
			baseUser.setAuthenticated(false);
			baseUser.setMessageException(e.getMessage());
		} catch (Exception e) {
			baseUser.setAuthenticated(false);
			baseUser.setMessageException(e.getMessage());			
		}
		
		return baseUser;
	}
	
	protected BaseOAuth2RestUser login(Environment env, String projectId, String login, String password) {
		setProperties(env, projectId);
		return login(this.server, this.clientId, this.clientSecret, login, password);
	}
	
	protected BaseOAuth2RestUser login(String server, String login, String password) {
		return login(server, "hfsClient", "hfsSecret", login, password);
	}
	
}
