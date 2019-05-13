package br.com.hfsframework.admin;

import java.util.ArrayList;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.hfsframework.util.interceptors.HeaderRequestInterceptor;

public final class RestTemplateUtil {

	@SuppressWarnings("unchecked")
	public static RestTemplate restTemplate(String server, String login, String password) {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory("hfsClient", "hfsSecret"));
		//restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		//restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
		request.set("username", login);
		request.set("password", password);
		request.set("grant_type", "password");
		Map<String, Object> token = restTemplate.postForObject(server, request, Map.class);

		//System.out.println("response: " + token + " - " + token.get("access_token"));
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "Bearer "+token.get("access_token")));
		interceptors.add(new HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));

		RestTemplate restTemplate2 = new RestTemplate();
		restTemplate2.setInterceptors(interceptors);
		
		return restTemplate2; 
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
