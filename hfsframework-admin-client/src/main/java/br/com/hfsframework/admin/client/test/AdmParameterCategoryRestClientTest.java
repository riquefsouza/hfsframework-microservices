package br.com.hfsframework.admin.client.test;

import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;
import br.com.hfsframework.base.test.BaseRestClientTest;

public class AdmParameterCategoryRestClientTest extends BaseRestClientTest<AdmParameterCategoryDTO, Long> {

    public AdmParameterCategoryRestClientTest(String oauthServer, String server) {
    	super(oauthServer, server, AdmParameterCategoryDTO.class, "admin", "admin");

    	this.server = server + "/api/v1/parametroCategoria";
    	
        objList.add(new AdmParameterCategoryDTO(1L, "ALFA Parâmetros", 1L));
        objList.add(new AdmParameterCategoryDTO(2L, "BETA Parâmetros", 2L));
        objList.add(new AdmParameterCategoryDTO(3L, "GAMA Parâmetros", 3L));
        objList.add(new AdmParameterCategoryDTO(4L, "TETA Parâmetros", 4L));
        
	}
	
}
