package br.com.hfsframework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.BaseRestController;
import br.com.hfsframework.domain.Role;
import br.com.hfsframework.service.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleRestController extends BaseRestController<Role, Long, RoleService> {

}
