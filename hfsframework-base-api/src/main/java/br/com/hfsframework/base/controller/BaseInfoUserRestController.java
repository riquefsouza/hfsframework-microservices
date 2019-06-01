package br.com.hfsframework.base.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.domain.Role;
import br.com.hfsframework.oauth.client.domain.User;

//@RestController
public class BaseInfoUserRestController extends BaseViewController {

	public BaseInfoUserRestController() {
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

			Set<Role> roles = new HashSet<Role>();
			
			this.getPrincipal().get().getAuthorities()
				.forEach(item -> roles.add(new Role(item.getAuthority())));
			
			User user = new User(username, "", email, urlPhoto, roles);
			
			return user;
		}
		
		return null;
	}

}
