package br.com.hfsframework.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
    private static final String CLIENT_ID = "hfsClient";
    private static final String CLIENT_SECRET = "hfsSecret";	

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
	
	protected OAuth2RestTemplate restTemplate(String server, String login, String password) {
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri(server);
		resourceDetails.setClientId(CLIENT_ID);
		resourceDetails.setClientSecret(CLIENT_SECRET);

		List<String> scopes = new ArrayList<String>();
		scopes.add("read");
		scopes.add("write");
		scopes.add("trust");
		resourceDetails.setScope(scopes);

		resourceDetails.setUsername(login);
		resourceDetails.setPassword(password);

		OAuth2RestTemplate rt = new OAuth2RestTemplate(resourceDetails);
		mappingJackson2HttpMessageConverter = createMappingJackson2HttpMessageConverter(); 
		rt.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		rt.getMessageConverters().add(createXmlHttpMessageConverter());
		
		return rt;
	}

}
