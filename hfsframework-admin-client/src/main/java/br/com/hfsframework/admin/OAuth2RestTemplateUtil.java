package br.com.hfsframework.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public final class OAuth2RestTemplateUtil {

	public static OAuth2RestTemplate restTemplate(String server, String login, String password) {
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri(server);
		resourceDetails.setClientId("hfsClient");
		resourceDetails.setClientSecret("hfsSecret");

		List<String> scopes = new ArrayList<String>();
		scopes.add("read");
		scopes.add("write");
		scopes.add("trust");
		resourceDetails.setScope(scopes);

		resourceDetails.setUsername(login);
		resourceDetails.setPassword(password);

		return new OAuth2RestTemplate(resourceDetails);
	}

}
