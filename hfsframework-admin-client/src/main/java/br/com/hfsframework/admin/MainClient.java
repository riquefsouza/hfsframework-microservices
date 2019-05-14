package br.com.hfsframework.admin;

import java.io.IOException;

import br.com.hfsframework.admin.client.ParametroCategoriaRestClient;

public class MainClient {

	//private static final Logger log = LogManager.getLogger(MainClient.class);

	private static final String ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server";
	private static final String OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";

	private ParametroCategoriaRestClient restClient;
	
	public static void main(String[] args) throws IOException {
		MainClient mainClient = new MainClient();
		mainClient.run();
	}

	public void run() throws IOException {
		restClient = new ParametroCategoriaRestClient(OAUTH_SERVER, ADMIN_SERVER); 
		
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
