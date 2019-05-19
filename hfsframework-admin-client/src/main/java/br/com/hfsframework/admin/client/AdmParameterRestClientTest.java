package br.com.hfsframework.admin.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmParameter;
import br.com.hfsframework.admin.client.domain.AdmParameterCategory;
import br.com.hfsframework.base.client.BaseOAuth2RestTemplateClient;

public class AdmParameterRestClientTest extends BaseOAuth2RestTemplateClient {

	private static final Logger log = LogManager.getLogger(AdmParameterRestClientTest.class);
	
	private List<AdmParameter> objList = new ArrayList<AdmParameter>();
	
	private OAuth2RestTemplate oauth2RestTemplate = null;
	
	private String adminServer;
	
    public AdmParameterRestClientTest(String oauthServer, String adminServer) {
    	this.adminServer = adminServer + "/api/v1/parametro";
    	
    	objList.add(new AdmParameter(1L, "ALFA valor", "ALFA descricao", "ALFA codigo", 1L, new AdmParameterCategory(1L)));
    	objList.add(new AdmParameter(2L, "BETA valor", "BETA descricao", "BETA codigo", 1L, new AdmParameterCategory(1L)));
    	objList.add(new AdmParameter(3L, "GAMA valor", "GAMA descricao", "GAMA codigo", 1L, new AdmParameterCategory(1L)));
    	objList.add(new AdmParameter(4L, "TETA valor", "TETA descricao", "TETA codigo", 1L, new AdmParameterCategory(1L)));
        
        oauth2RestTemplate = restTemplate(oauthServer, "admin", "admin");
	}
	
	public void addAll() {
    	log.info(METHOD_ACTION.ADD_ALL);
    	
    	this.objList.stream().forEach(bean -> {
    		try {
    			
    			AdmParameter saved = oauth2RestTemplate.postForObject(
    					this.adminServer, bean, AdmParameter.class);
    			
    			bean.setId(saved.getId());
    			
    			log.info("added: " + saved);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}

	public void getAll() {
		log.info(METHOD_ACTION.GET_ALL);
		
		try {
			AdmParameter[] obj = oauth2RestTemplate.getForObject(
					this.adminServer, AdmParameter[].class);
			Arrays.asList(obj).stream().forEach(bean -> log.info("getAll: " + bean));
		} catch (RestClientException e) {
			log.error(e.getMessage());			
		}
	}

	public void getById() {
		log.info(METHOD_ACTION.GET_BY_ID);
    	this.objList.stream().forEach(bean -> {
    		
			try {
				AdmParameter obj = oauth2RestTemplate.getForObject(
						this.adminServer + "/{id}", AdmParameter.class, bean.getId());
				
				log.info("getById: " + obj);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}

	public void updateById() {
		log.info(METHOD_ACTION.UPDATE_BY_ID);

		this.objList.stream().forEach(bean -> bean.setDescription("DESC "+bean.getId()));
    	
    	this.objList.stream().forEach(bean -> {
			try {
				oauth2RestTemplate.put(this.adminServer + "/{id}", bean, bean.getId());
    			
				log.info("updated: " + bean);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}
	
	public void getEntity() {
		this.objList.stream().forEach(bean -> {
			try {
				ResponseEntity<AdmParameter> entity = oauth2RestTemplate.getForEntity(this.adminServer + "/{id}", 
						AdmParameter.class, bean.getId());
				AdmParameter obj = (AdmParameter) entity.getBody();
				log.info("getEntity: " + obj);
		
				HttpHeaders responseHeaders = entity.getHeaders();
				responseHeaders.entrySet().forEach(header-> {
					log.info(header.getKey() + ":" + header.getValue());
				});
		
				log.info("status name: " + entity.getStatusCode().name());
				log.info("status value: " + entity.getStatusCode().value());
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}
	
	public void deleteById() {
		log.info(METHOD_ACTION.DELETE_BY_ID);
		
    	this.objList.stream().forEach(bean -> {
			try {
				oauth2RestTemplate.delete(this.adminServer + "/{id}", bean.getId());
    			
				log.info("deleted: " + bean);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}	
}
