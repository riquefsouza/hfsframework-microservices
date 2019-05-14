package br.com.hfsframework.admin.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.domain.ParametroCategoria;
import br.com.hfsframework.base.BaseOAuth2RestTemplateClient;

public class ParametroCategoriaRestClient extends BaseOAuth2RestTemplateClient {

	private static final Logger log = LogManager.getLogger(ParametroCategoriaRestClient.class);
	
	private List<ParametroCategoria> objList = new ArrayList<ParametroCategoria>();
	
	private OAuth2RestTemplate oauth2RestTemplate = null;
	
	private String adminServer;
	
    public ParametroCategoriaRestClient(String oauthServer, String adminServer) {
    	this.adminServer = adminServer;
    	
        objList.add(new ParametroCategoria(1L, "ALFA Parâmetros", 1L));
        objList.add(new ParametroCategoria(2L, "BETA Parâmetros", 2L));
        objList.add(new ParametroCategoria(3L, "GAMA Parâmetros", 3L));
        objList.add(new ParametroCategoria(4L, "TETA Parâmetros", 4L));
        
        oauth2RestTemplate = restTemplate(oauthServer, "admin", "admin");
	}
	
	public void addAll() {
    	log.info(METHOD_ACTION.ADD_ALL);
    	
    	this.objList.stream().forEach(bean -> {
    		try {
    			
    			ParametroCategoria saved = oauth2RestTemplate.postForObject(
    					this.adminServer + "/api/v1/parametroCategoria", bean, ParametroCategoria.class);
    			
    			log.info("added: " + saved);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}

	/*
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
*/	
}
