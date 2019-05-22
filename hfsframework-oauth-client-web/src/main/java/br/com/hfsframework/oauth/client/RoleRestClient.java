package br.com.hfsframework.oauth.client;

import org.springframework.beans.factory.annotation.Value;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.oauth.client.domain.Role;

public class RoleRestClient extends BaseRestClient<Role, Long> {

	@Value("${oauth2.hfsframework.server}")
	private static String baseURL;
	
	public RoleRestClient(String sAccesToken) {
		super(baseURL+ "/api/v1/role", sAccesToken, Role.class);
		
		//this.server = server + "/api/v1/role";
	}

}
