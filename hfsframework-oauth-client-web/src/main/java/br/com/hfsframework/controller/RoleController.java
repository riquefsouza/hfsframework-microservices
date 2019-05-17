package br.com.hfsframework.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.hfsframework.oauth.client.RoleRestClient;
import br.com.hfsframework.oauth.client.domain.Role;

@Controller
public class RoleController {
	
	private RoleRestClient roleRestClient;
	
	@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Role> getAll() {
		roleRestClient = new RoleRestClient("o token");
		return roleRestClient.getAll();
	}
}
