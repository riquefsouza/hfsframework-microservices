package br.com.hfsframework.base.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hfsframework.base.security.BaseRestUser;
import br.com.hfsframework.util.HttpMessageConverterUtil;
import br.com.hfsframework.util.interceptors.BaseHeaderRequestInterceptor;

public abstract class BaseRestTemplateClient {

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
	
	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory(String login, String password) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setHttpClient(httpClient(login, password));

		return clientHttpRequestFactory;
	}

	private HttpClient httpClient(String login, String password) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, password));

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		return client;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getOAuth2Token(String server, String clientId, String clientSecret, 
			String login, String password) throws RestClientException {
		
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory(clientId, clientSecret));

		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
		request.set("username", login);
		request.set("password", password);
		request.set("grant_type", "password");
		Map<String, Object> token = restTemplate.postForObject(server + "/oauth/token", request, Map.class);
		
		return token;
	}
		
	@SuppressWarnings("unchecked")
	protected RestTemplate restTemplate(String sAccesToken) throws RestClientException {

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BaseHeaderRequestInterceptor("Authorization", "Bearer " + sAccesToken));
		interceptors.add(new BaseHeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));

		RestTemplate rt = new RestTemplate();
		rt.setInterceptors(interceptors);

		rt.getMessageConverters().addAll(HttpMessageConverterUtil.getMessageConverters());
		mappingJackson2HttpMessageConverter = (HttpMessageConverter<Object>) rt.getMessageConverters().get(0);
		
		return rt; 
	}
	
	private RestTemplate restTemplate(String server, String clientId, String clientSecret, 
			String login, String password) throws RestClientException {

		Map<String, Object> token = getOAuth2Token(server, clientId, clientSecret, login, password);
		String sAccesToken = token.get("access_token").toString();
		
		return restTemplate(sAccesToken);
	}

	private void setProperties(Environment env, String projectId) {
		this.server = env.getRequiredProperty("oauth2."+ projectId +".server");
		this.clientId = env.getProperty("oauth2."+ projectId + ".client-id");
		this.clientSecret = env.getProperty("oauth2."+ projectId + ".client-secret");		
	}
	
	protected RestTemplate restTemplate(Environment env, String projectId, String login, String password) 
			throws RestClientException {
		setProperties(env, projectId);
		return restTemplate(server, clientId, clientSecret, login, password);
	}
	
	protected RestTemplate restTemplate(String server, String login, String password) 
			throws RestClientException {
		
		return restTemplate(server, "hfsClient", "hfsSecret", login, password);
	}
	
	private BaseRestUser login(String server, String clientId, String clientSecret, 
			String login, String password) {
		
		List<String> listRoles = new ArrayList<String>();
		String[] roles = new String[listRoles.size()];
		//String csenha = BCrypt.hashpw(password, BCrypt.gensalt());
		BaseRestUser baseUser = new BaseRestUser(login, password, 
				AuthorityUtils.createAuthorityList(roles));
				
		try {			
			Map<String, Object> token = getOAuth2Token(server, clientId, clientSecret, login, password);
			String sToken = token.get("access_token").toString();			
			if (!sToken.trim().isEmpty()) {
				baseUser.setAuthenticated(true);
				baseUser.setUrlAuthorizationServer(server);
				baseUser.setAccessToken(sToken);
			} else {
				baseUser.setAuthenticated(false);				
			}
		} catch (HttpClientErrorException e) {
			baseUser.setAuthenticated(false);
			baseUser.setMessageException(e.getMessage());
		} catch (RestClientException e) {
			baseUser.setAuthenticated(false);
			baseUser.setMessageException(e.getMessage());
		} catch (Exception e) {
			baseUser.setAuthenticated(false);
			baseUser.setMessageException(e.getMessage());
		}
		
		return baseUser;	
	}

	protected BaseRestUser login(Environment env, String projectId, String login, String password) {
		setProperties(env, projectId);
		return login(this.server, this.clientId, this.clientSecret, login, password);
	}
	
	protected BaseRestUser login(String server, String login, String password) {
		return login(server, "hfsClient", "hfsSecret", login, password);
	}
	
}
