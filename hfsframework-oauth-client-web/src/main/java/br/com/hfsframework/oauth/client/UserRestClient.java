package br.com.hfsframework.oauth.client;

import org.springframework.beans.factory.annotation.Value;

import br.com.hfsframework.base.BaseRestClient;
import br.com.hfsframework.oauth.client.domain.User;

public class UserRestClient extends BaseRestClient<User, Long> {

	@Value("${oauth2.hfsframework.provider.token-uri}")
	private static String baseURL;

	public UserRestClient(String sAccesToken) {
		super(baseURL + "/api/v1/user", sAccesToken, User.class);
		
		this.server = server + "/api/v1/user";
	}

}
