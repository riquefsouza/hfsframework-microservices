package br.com.hfsframework.base;

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
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseOAuth2RestTemplateClient {
	
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
			String login, String password) throws IllegalStateException {
		
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

	protected OAuth2RestTemplate restTemplate(Environment env, String projectId, String login, String password) throws IllegalStateException {
		String server = env.getRequiredProperty("oauth2."+ projectId +".provider.token-uri");
		String clientId = env.getProperty("oauth2."+ projectId + ".client-id");
		String clientSecret = env.getProperty("oauth2."+ projectId + ".client-secret");

		return restTemplate(server, clientId, clientSecret, login, password);
	}
	
	protected OAuth2RestTemplate restTemplate(String server, String login, String password) throws IllegalStateException {
		return restTemplate(server, "hfsClient", "hfsSecret", login, password);
	}
	
}
