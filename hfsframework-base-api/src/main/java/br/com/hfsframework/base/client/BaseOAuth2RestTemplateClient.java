package br.com.hfsframework.base.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hfsframework.base.security.BaseOAuth2RestUser;

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
	
    private HttpMessageConverter<Object> createMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return converter;
    }
    
    private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter = 
          new MarshallingHttpMessageConverter();
 
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
 
        return xmlConverter;
    }
    
	protected String json(Object o) throws IOException {
	    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	    mappingJackson2HttpMessageConverter.write(
	            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	    return mockHttpOutputMessage.getBodyAsString();
	}    

	private OAuth2RestTemplate restTemplate(String server, String clientId, String clientSecret, 
			String login, String password) throws RestClientException {
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri(server.trim());
		resourceDetails.setClientId(clientId.trim());		
		resourceDetails.setClientSecret(clientSecret.trim());

		List<String> scopes = new ArrayList<String>();
		scopes.add("read");
		scopes.add("write");
		scopes.add("trust");
		resourceDetails.setScope(scopes);

		resourceDetails.setUsername(login.trim());
		resourceDetails.setPassword(password.trim());

		OAuth2RestTemplate rt = new OAuth2RestTemplate(resourceDetails);
		mappingJackson2HttpMessageConverter = createMappingJackson2HttpMessageConverter(); 
		rt.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		rt.getMessageConverters().add(createXmlHttpMessageConverter());
		
		return rt;
	}
	
	private void setProperties(Environment env, String projectId) {
		this.server = env.getRequiredProperty("oauth2."+ projectId +".provider.token-uri");
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
		
		String[] roles = { "ROLE_USER" };
		//String csenha = BCrypt.hashpw(password, BCrypt.gensalt());
		BaseOAuth2RestUser baseUser = new BaseOAuth2RestUser(login, password, 
				AuthorityUtils.createAuthorityList(roles));
		
		try {
			OAuth2RestTemplate token = restTemplate(server, clientId, clientSecret, login, password);
			String sToken = token.getAccessToken().getValue();
			if (!sToken.trim().isEmpty()) {
				baseUser.setAuthenticated(true);
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