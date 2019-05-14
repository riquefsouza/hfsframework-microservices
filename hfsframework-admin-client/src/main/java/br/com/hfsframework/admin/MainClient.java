package br.com.hfsframework.admin;

import java.io.IOException;

import br.com.hfsframework.admin.client.ParametroCategoriaRestClient;

public class MainClient {

	//private static final Logger log = LogManager.getLogger(MainClient.class);

	private static final String ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server";
	private static final String OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";

	private ParametroCategoriaRestClient parametroCategoriaRestClient;
	
	public static void main(String[] args) throws IOException {
		MainClient mainClient = new MainClient();
		mainClient.run();
	}

	public void run() throws IOException {
		parametroCategoriaRestClient = new ParametroCategoriaRestClient(OAUTH_SERVER, ADMIN_SERVER); 
		
		//Parametro p = new Parametro(1L, "ALFA valor", "ALFA descricao", "ALFA codigo", 1L, new ParametroCategoria(69L));		
		//String json = OAuth2RestTemplateUtil.json(p);
		
		parametroCategoriaRestClient.addAll();
		//getAll();
		//getById(3L);
		//update();
		//deleteById(4L);
		//getEntityById(3L);
	}

}
