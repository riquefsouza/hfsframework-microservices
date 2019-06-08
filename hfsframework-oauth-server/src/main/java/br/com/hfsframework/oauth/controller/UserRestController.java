package br.com.hfsframework.oauth.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.BaseRestController;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.dto.NewUserDTO;
import br.com.hfsframework.oauth.service.IUserService;
import br.com.hfsframework.oauth.validator.NewUserValidator;
import br.com.hfsframework.oauth.validator.UsernameAndPasswordDifferentValidator;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController extends BaseRestController<User, Long, IUserService> {

	private static final long serialVersionUID = 1L;
	
	public UserRestController() {
		super(false);
	}
	
	@InitBinder("newUserDTO")
	public void init(WebDataBinder binder) {
		binder.addValidators(new UsernameAndPasswordDifferentValidator(), new NewUserValidator(this.service));
	}

	@ApiOperation("Insert bean Validate")
	@PostMapping("/validateAndSave")
	public ResponseEntity<User> validateAndSave(@Valid @RequestBody NewUserDTO dto) {

		this.service.getRepositorio().save(dto.build());

		return ResponseEntity.ok().build();
	}
	
	@ApiOperation("Find by username")
	@GetMapping("/find")
	public ResponseEntity<User> findByUsername(@RequestParam(name = "username", required = true) String username) {
		Optional<User> obj = this.service.findByUsername(username);
		
		if (!obj.isPresent()) {
			log.info("FIND BY USERNAME NOT FOUND: " + username);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get());
	}

	@ApiOperation("Find by email")
	@GetMapping("/findemail")
	public ResponseEntity<User> findByEmail(@RequestParam(name = "email", required = true) String email) {
		Optional<User> obj = this.service.findByEmail(email);
		
		if (!obj.isPresent()) {
			log.info("FIND BY EMAIL NOT FOUND: " + email);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get());
	}
	
}
