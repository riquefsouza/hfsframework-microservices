package br.com.hfsframework.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hfsframework.util.interceptors.HeaderRequestInterceptor;

public abstract class BaseRestTemplateClient {

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

	@SuppressWarnings("unchecked")
	protected RestTemplate restTemplate(String server, String login, String password) {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory(CLIENT_ID, CLIENT_SECRET));

		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
		request.set("username", login);
		request.set("password", password);
		request.set("grant_type", "password");
		Map<String, Object> token = restTemplate.postForObject(server, request, Map.class);

		//System.out.println("response: " + token + " - " + token.get("access_token"));
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "Bearer "+token.get("access_token")));
		interceptors.add(new HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));

		RestTemplate rt = new RestTemplate();
		rt.setInterceptors(interceptors);

		mappingJackson2HttpMessageConverter = createMappingJackson2HttpMessageConverter(); 
		rt.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		rt.getMessageConverters().add(createXmlHttpMessageConverter());
		
		return rt; 
	}

	private static HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory(String login, String password) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setHttpClient(httpClient(login, password));

		return clientHttpRequestFactory;
	}

	private static HttpClient httpClient(String login, String password) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, password));

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		return client;
	}

}
