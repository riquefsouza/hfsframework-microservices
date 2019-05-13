package br.com.hfsframework.admin;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.domain.Parametro;

public class MainClient {

	private static final Logger log = LogManager.getLogger(MainClient.class);

	private static final String ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server/api/v1/parametro";
	private static final String OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";

	// private RestTemplate restTemplate = new RestTemplate();
	private OAuth2RestTemplate oauth2RestTemplate = null;

	public static void main(String[] args) {
		MainClient mainClient = new MainClient();
		mainClient.run();
	}

	public void run() {
		oauth2RestTemplate = OAuth2RestTemplateUtil.restTemplate(OAUTH_SERVER, "admin", "admin");

		//add();
		//getAll();
		getById(3L);
		//update();
		//deleteById(4L);
		//getEntityById(3L);
		/*
		URI uri;
		try {
			uri = new URI(ADMIN_SERVER+"/1");
			
			ResponseEntity<Parametro> result = oauth2RestTemplate.getForEntity(uri, Parametro.class);
			log.info(result.getStatusCodeValue());
	        log.info(result.getBody());
	        
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
		}
		*/
		
		/*
		ResponseEntity<List<Parametro>> response = oauth2RestTemplate.exchange(ADMIN_SERVER, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Parametro>>() {});

		List<Parametro> lista = response.getBody();
		
		for (Parametro parametro : lista) {
			log.info(parametro.getDescricao());
		}
		*/
	}

	private void getAll() {
		try {
			Parametro[] parametros = oauth2RestTemplate.getForObject(ADMIN_SERVER, Parametro[].class);
			for (Parametro parametro : parametros) {
				log.info(parametro);
			}
		} catch (RestClientException e) {
			log.error(e.getMessage());			
		}
	}

	private Optional<Parametro> getById(Long id) {
		Parametro parametro = null;

		try {
			parametro = oauth2RestTemplate.getForObject(ADMIN_SERVER + "/{id}", Parametro.class, id);
			log.info("parametro: " + parametro);
		} catch (RestClientException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return Optional.ofNullable(parametro);
	}

	private void add() {
		try {
			Parametro parametro = new Parametro();
			parametro.setValor("novo valor");
			parametro.setDescricao("nova descricao");
			parametro.setCodigo("novo codigo");
			parametro.setIdParametroCategoria(1L);

			Parametro saved = oauth2RestTemplate.postForObject(ADMIN_SERVER, parametro, Parametro.class);

			log.info("added: " + saved);

		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
	}

	private void update() {
		try {
			Optional<Parametro> parametro = getById(1L);
			parametro.get().setValor("valor altered");

			oauth2RestTemplate.put(ADMIN_SERVER + "/{id}", parametro, parametro.get().getId());

			Parametro updated = getById(4L).get();

			log.info("updated: " + updated);

		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
	}

	private void deleteById(Long id) {
		try {
			oauth2RestTemplate.delete(ADMIN_SERVER + "/{id}", id);
			log.info("deleted");
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
	}

	private Optional<Parametro> getEntityById(Long id) {
		Parametro parametro = null;

		try {
			ResponseEntity<Parametro> entity = oauth2RestTemplate.getForEntity(ADMIN_SERVER + "/{id}", Parametro.class,
					id);
			parametro = (Parametro) entity.getBody();
			log.info("parametro: " + parametro);

			HttpHeaders responseHeaders = entity.getHeaders();
			responseHeaders.entrySet().forEach(header -> {
				log.info(header.getKey() + ":" + header.getValue());
			});

			log.info("status name: " + entity.getStatusCode().name());
			log.info("status value: " + entity.getStatusCode().value());
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}

		return Optional.ofNullable(parametro);
	}
}
