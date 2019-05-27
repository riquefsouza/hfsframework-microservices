package br.com.hfsframework.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.UserRestClient;
import br.com.hfsframework.oauth.client.domain.User;

@RestController
public class InfoUserRestController extends BaseViewController {

	public InfoUserRestController() {
		super();
	}

	@RequestMapping(value = "/private/userinfo")
	public User getUser() {
		if (getPrincipal().isPresent()) {
			this.authServerURL = this.getPrincipal().get().getUrlAuthorizationServer();
			this.accesToken = this.getPrincipal().get().getAccessToken().getValue();
			String username = this.getPrincipal().get().getUsername();

			UserRestClient restClient = new UserRestClient(this.authServerURL, this.accesToken);
			Optional<User> user = restClient.findByUsername(username);
			
			if (user.isPresent()) {
				return user.get();
			}
		}
		
		return null;
	}

}
