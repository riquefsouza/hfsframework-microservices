package br.com.hfsframework.admin.client;

import br.com.hfsframework.admin.client.domain.AdmParameterCategory;
import br.com.hfsframework.base.test.BaseRestClientTest;

public class AdmParameterCategoryRestClientTest extends BaseRestClientTest<AdmParameterCategory, Long> {

    public AdmParameterCategoryRestClientTest(String oauthServer, String server) {
    	super(oauthServer, server, AdmParameterCategory.class, "admin", "admin");

    	this.server = server + "/api/v1/parametroCategoria";
    	
        objList.add(new AdmParameterCategory(1L, "ALFA Parâmetros", 1L));
        objList.add(new AdmParameterCategory(2L, "BETA Parâmetros", 2L));
        objList.add(new AdmParameterCategory(3L, "GAMA Parâmetros", 3L));
        objList.add(new AdmParameterCategory(4L, "TETA Parâmetros", 4L));
        
	}
	
}
