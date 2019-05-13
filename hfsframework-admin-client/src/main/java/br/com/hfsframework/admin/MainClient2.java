package br.com.hfsframework.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hfsframework.admin.domain.Parametro;

public class MainClient2 {

	private static final Logger log = LogManager.getLogger(MainClient2.class);

	private static final String ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server/api/v1/parametro";
	private static final String OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";

	private RestTemplate restTemplate = null;
	//private OAuth2RestTemplate oauth2RestTemplate = null;

	public static void main(String[] args) {
		MainClient mainClient = new MainClient();
		mainClient.run();
	}

	public void run() {
		/*
		HttpHeaders createHeaders(String username, String password){
			   return new HttpHeaders() {{
			         String auth = username + ":" + password;
			         byte[] encodedAuth = Base64.encodeBase64( 
			            auth.getBytes(Charset.forName("US-ASCII")) );
			         String authHeader = "Basic " + new String( encodedAuth );
			         set( "Authorization", authHeader );
			      }};
			}		
		//		
		restTemplate.getInterceptors().add(
				  new BasicAuthorizationInterceptor("username", "password"));
		restTemplate.exchange(
				  "http://localhost:8082/spring-security-rest-basic-auth/api/foos/1", 
				  HttpMethod.GET, null, Foo.class);
				  */
		
		restTemplate = RestTemplateUtil.restTemplate(OAUTH_SERVER, "admin", "admin");

		getAll();
	}
	
	private void getAll() {
		try {
			Parametro[] parametros = restTemplate.getForObject(ADMIN_SERVER, Parametro[].class);
			for (Parametro parametro : parametros) {
				log.info(parametro);
			}
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
	}

}
