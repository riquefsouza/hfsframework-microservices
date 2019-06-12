package br.com.hfsframework.oauth.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.BaseRestController;
import br.com.hfsframework.oauth.client.domain.RoleDTO;
import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.service.IRoleService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/role")
public class RoleRestController extends BaseRestController<RoleDTO, Role, Long, IRoleService> {

	private static final long serialVersionUID = 1L;
	
	public RoleRestController() {
		super(false);
	}
	
	@ApiOperation("Find by name")
	@GetMapping("/find")
	public ResponseEntity<Role> findByName(@RequestParam(name = "name", required = true) String name) {
		Optional<Role> obj = this.service.findByName(name);
		
		if (!obj.isPresent()) {
			log.info("FIND BY NAME NOT FOUND: " + name);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get());
	}
	

}
