package br.com.hfsframework.admin.client;

import br.com.hfsframework.admin.domain.ParametroCategoria;
import br.com.hfsframework.base.BaseRestClient;

public class ParametroCategoriaRestClient extends BaseRestClient<ParametroCategoria, Long> {

    public ParametroCategoriaRestClient(String oauthServer, String adminServer) {
    	super(oauthServer, adminServer, ParametroCategoria.class, "admin", "admin");

    	this.adminServer = adminServer + "/api/v1/parametroCategoria";
    	
        objList.add(new ParametroCategoria(1L, "ALFA Parâmetros", 1L));
        objList.add(new ParametroCategoria(2L, "BETA Parâmetros", 2L));
        objList.add(new ParametroCategoria(3L, "GAMA Parâmetros", 3L));
        objList.add(new ParametroCategoria(4L, "TETA Parâmetros", 4L));
        
	}
	
}
