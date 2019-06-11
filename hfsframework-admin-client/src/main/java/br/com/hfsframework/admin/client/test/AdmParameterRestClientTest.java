package br.com.hfsframework.admin.client.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmParameterDTO;
import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;
import br.com.hfsframework.base.client.BaseOAuth2RestTemplateClient;

public class AdmParameterRestClientTest extends BaseOAuth2RestTemplateClient {

	private static final Logger log = LogManager.getLogger(AdmParameterRestClientTest.class);
	
	private List<AdmParameterDTO> objList = new ArrayList<AdmParameterDTO>();
	
	private OAuth2RestTemplate oauth2RestTemplate = null;
	
	private String adminServer;
	
    public AdmParameterRestClientTest(String oauthServer, String adminServer) {
    	this.adminServer = adminServer + "/api/v1/parametro";
    	
    	objList.add(new AdmParameterDTO(1L, "ALFA valor", "ALFA descricao", "ALFA codigo", 1L, new AdmParameterCategoryDTO(1L)));
    	objList.add(new AdmParameterDTO(2L, "BETA valor", "BETA descricao", "BETA codigo", 1L, new AdmParameterCategoryDTO(1L)));
    	objList.add(new AdmParameterDTO(3L, "GAMA valor", "GAMA descricao", "GAMA codigo", 1L, new AdmParameterCategoryDTO(1L)));
    	objList.add(new AdmParameterDTO(4L, "TETA valor", "TETA descricao", "TETA codigo", 1L, new AdmParameterCategoryDTO(1L)));
        
        oauth2RestTemplate = restTemplate(oauthServer, "admin", "admin");
	}
	
	public void addAll() {
    	log.info(METHOD_ACTION.ADD_ALL);
    	
    	this.objList.stream().forEach(bean -> {
    		try {
    			
    			AdmParameterDTO saved = oauth2RestTemplate.postForObject(
    					this.adminServer, bean, AdmParameterDTO.class);
    			
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
			AdmParameterDTO[] obj = oauth2RestTemplate.getForObject(
					this.adminServer, AdmParameterDTO[].class);
			Arrays.asList(obj).stream().forEach(bean -> log.info("getAll: " + bean));
		} catch (RestClientException e) {
			log.error(e.getMessage());			
		}
	}

	public void getById() {
		log.info(METHOD_ACTION.GET_BY_ID);
    	this.objList.stream().forEach(bean -> {
    		
			try {
				AdmParameterDTO obj = oauth2RestTemplate.getForObject(
						this.adminServer + "/{id}", AdmParameterDTO.class, bean.getId());
				
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
				ResponseEntity<AdmParameterDTO> entity = oauth2RestTemplate.getForEntity(this.adminServer + "/{id}", 
						AdmParameterDTO.class, bean.getId());
				AdmParameterDTO obj = (AdmParameterDTO) entity.getBody();
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
