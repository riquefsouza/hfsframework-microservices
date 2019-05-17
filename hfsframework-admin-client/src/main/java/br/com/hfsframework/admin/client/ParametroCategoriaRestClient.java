package br.com.hfsframework.admin.client;

import br.com.hfsframework.admin.client.domain.ParametroCategoria;
import br.com.hfsframework.base.BaseRestClientTest;

public class ParametroCategoriaRestClient extends BaseRestClientTest<ParametroCategoria, Long> {

    public ParametroCategoriaRestClient(String oauthServer, String server) {
    	super(oauthServer, server, ParametroCategoria.class, "admin", "admin");

    	this.server = server + "/api/v1/parametroCategoria";
    	
        objList.add(new ParametroCategoria(1L, "ALFA Parâmetros", 1L));
        objList.add(new ParametroCategoria(2L, "BETA Parâmetros", 2L));
        objList.add(new ParametroCategoria(3L, "GAMA Parâmetros", 3L));
        objList.add(new ParametroCategoria(4L, "TETA Parâmetros", 4L));
        
	}
	
}
