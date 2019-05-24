package br.com.hfsframework.oauth.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.base.BaseRestController;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.dto.NewUserDTO;
import br.com.hfsframework.oauth.service.UserService;
import br.com.hfsframework.oauth.validator.NewUserValidator;
import br.com.hfsframework.oauth.validator.UsernameAndPasswordDifferentValidator;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController extends BaseRestController<User, Long, UserService> {

	@InitBinder("newUserDTO")
	public void init(WebDataBinder binder) {
		binder.addValidators(new UsernameAndPasswordDifferentValidator(), new NewUserValidator(this.getServico()));
	}

	@ApiOperation("Insert bean Validate")
	@PostMapping("/usuarios")
	public ResponseEntity<User> validateAndSave(@Valid @RequestBody NewUserDTO dto) {

		this.getServico().getRepositorio().save(dto.build());

		return ResponseEntity.ok().build();
	}

}
