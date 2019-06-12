package br.com.hfsframework.admin.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.client.domain.AdmParameterDTO;
import br.com.hfsframework.admin.domain.AdmParameter;
import br.com.hfsframework.admin.service.IAdmParameterService;
import br.com.hfsframework.base.BaseRestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/admParameter")
public class AdmParameterRestController
		extends BaseRestController<AdmParameterDTO, AdmParameter, Long, IAdmParameterService> {

	private static final long serialVersionUID = 1L;

	public AdmParameterRestController() {
		super(true);
	}

	@ApiOperation("Find by description")
	@GetMapping("/find")
	public ResponseEntity<AdmParameterDTO> findByDescription(
			@RequestParam(name = "description", required = true) String description) {

		// if (AuthenticationUtil.tokenHasRole(request, "ADMIN")) {
		// return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		// }

		// Optional<AdmParameterCategory> obj =
		// this.service.findByDescription(description);
		Optional<AdmParameter> obj = this.service.get(2L);

		if (!obj.isPresent()) {
			log.info("FIND BY description NOT FOUND: " + description);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get().toDTO());
	}

}
