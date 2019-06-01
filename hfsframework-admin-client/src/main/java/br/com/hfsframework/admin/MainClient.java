package br.com.hfsframework.admin;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.hfsframework.admin.client.test.AdmParameterCategoryRestClientTest;
import br.com.hfsframework.util.copyright.CopyrightEnum;
import br.com.hfsframework.util.copyright.CopyrightUtil;

public class MainClient {

	private static final Logger log = LogManager.getLogger(MainClient.class);

	private static final String ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server";
	private static final String OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";

	private AdmParameterCategoryRestClientTest restClient;
	
	public static void main(String[] args) throws IOException {
		
    	System.out.print(CopyrightUtil.getAsString(CopyrightEnum.HFSFRAMEWORK_FOR_MICROSERVICES));
    	System.out.print(CopyrightUtil.getAsString(CopyrightEnum.DEVELOPED_BY));
    	
		log.info("------------------------------------------------------------------------");
		log.info("HFSFramework for Microservices");
		log.info("Developed by Henrique Figueiredo de Souza");
		log.info("Version 1.0 - 2019");
		log.info("------------------------------------------------------------------------");
		log.info("Starting HFS Framework Administrative Client...");
		
		System.out.print(CopyrightUtil.getAsString(CopyrightEnum.ADMINISTRATIVE_CLIENT_CONSOLE));
		
		MainClient mainClient = new MainClient();
		mainClient.run();
	}

	public void run() throws IOException {
		restClient = new AdmParameterCategoryRestClientTest(OAUTH_SERVER, ADMIN_SERVER); 
		
		//Parametro p = new Parametro(1L, "ALFA valor", "ALFA descricao", "ALFA codigo", 1L, new ParametroCategoria(69L));		
		//String json = OAuth2RestTemplateUtil.json(p);
		
		restClient.addAll();
		restClient.getAll();
		restClient.getById();
		restClient.updateById();		
		restClient.getEntity();
		restClient.deleteById();
	}

}
