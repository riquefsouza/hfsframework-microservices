package br.com.hfsframework.base.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.domain.RoleDTO;
import br.com.hfsframework.oauth.client.domain.UserDTO;

//@RestController
public class BaseInfoUserRestController extends BaseViewController {

	public BaseInfoUserRestController() {
		super();
	}

	@RequestMapping(value = "/private/userinfo")
	public UserDTO getUser() {
		if (getPrincipal().isPresent()) {
			this.authServerURL = this.getPrincipal().get().getUrlAuthorizationServer();
			this.accesToken = this.getPrincipal().get().getAccessToken().getValue();
			String username = this.getPrincipal().get().getUsername();
			String email = this.getPrincipal().get().getEmail();
			String urlPhoto = this.getPrincipal().get().getUrlPhoto();			

			Set<RoleDTO> roles = new HashSet<RoleDTO>();
			
			this.getPrincipal().get().getAuthorities()
				.forEach(item -> roles.add(new RoleDTO(item.getAuthority())));
			
			UserDTO user = new UserDTO(username, "", email, urlPhoto, roles);
			
			return user;
		}
		
		return null;
	}

}
