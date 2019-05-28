package br.com.hfsframework.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.domain.Role;
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
			String email = this.getPrincipal().get().getEmail();
			String urlPhoto = this.getPrincipal().get().getUrlPhoto();			

			//UserRestClient restClient = new UserRestClient(this.authServerURL, this.accesToken);
			//Optional<User> user = restClient.findByUsername(username);
			
			List<Role> roles = new ArrayList<Role>();
			
			this.getPrincipal().get().getAuthorities()
				.forEach(item -> roles.add(new Role(item.getAuthority())));
			
			User user = new User(username, "", email, urlPhoto, roles);
			
			return user;
		}
		
		return null;
	}

}
