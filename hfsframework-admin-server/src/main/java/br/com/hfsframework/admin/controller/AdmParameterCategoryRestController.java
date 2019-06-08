package br.com.hfsframework.admin.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.AdmParameterCategory;
import br.com.hfsframework.admin.service.IAdmParameterCategoryService;
import br.com.hfsframework.base.BaseRestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/admParameterCategory")
public class AdmParameterCategoryRestController
		extends BaseRestController<AdmParameterCategory, Long, IAdmParameterCategoryService> {

	private static final long serialVersionUID = 1L;

	public AdmParameterCategoryRestController() {
		super(true);
	}
	
	@ApiOperation("Find by description")
	@GetMapping("/find")
	public ResponseEntity<AdmParameterCategory> findByDescription(
			@RequestParam(name = "description", required = true) String description) {

		//if (AuthenticationUtil.tokenHasRole(request, "ADMIN")) {
			//return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//}
	
		Optional<AdmParameterCategory> obj = this.service.findByDescription(description);
		
		if (!obj.isPresent()) {
			log.info("FIND BY description NOT FOUND: " + description);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get());
	}


}
