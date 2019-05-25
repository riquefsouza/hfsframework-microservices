package br.com.hfsframework.oauth.client;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.oauth.client.domain.Role;

public class RoleRestClient extends BaseRestClient<Role, Long> {
	
	public RoleRestClient(String authServerURL, String accesToken) {
		super(authServerURL + "/api/v1/role", accesToken, Role.class);
	}

}
