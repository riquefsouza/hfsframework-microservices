package br.com.hfsframework.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.BaseRestController;
import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.service.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleRestController extends BaseRestController<Role, Long, RoleService> {

	private static final long serialVersionUID = 1L;

}
