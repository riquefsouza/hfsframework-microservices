package br.com.hfsframework.oauth.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.oauth.client.RoleRestClient;
import br.com.hfsframework.oauth.client.domain.Role;

@Controller
@RequestMapping(value = "/private/role")
public class RoleController {
	
	private static final Logger log = LogManager.getLogger(RoleController.class);
	
	private RoleRestClient roleRestClient;
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@ResponseBody
	public List<Role> getAll() {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Role> listAll = new ArrayList<Role>();

		if (auth!=null) {
			log.info("LOGIN: " + auth.getName());
			
			BaseOAuth2RestUser baseUser = (BaseOAuth2RestUser)auth.getPrincipal();
			String sToken = baseUser.getAccessToken().getValue();
			log.info("TOKEN: " + sToken);
			roleRestClient = new RoleRestClient(sToken);
			listAll = roleRestClient.getAll();
		}		
		
		return listAll;
	}
}
