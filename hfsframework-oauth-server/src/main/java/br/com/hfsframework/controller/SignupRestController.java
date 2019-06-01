package br.com.hfsframework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.service.SignupService;
import br.com.hfsframework.util.exceptions.TransactionException;

@RestController
public class SignupRestController {

	@Autowired
	private SignupService signupService;

	@RequestMapping(value = "/api/public/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody User user) {
		try {
			Optional<User> newUser = signupService.addUser(user);

			if (newUser.isPresent())
				return ResponseEntity.ok(newUser.get().getId().toString());
			else
				return ResponseEntity.noContent().build();
			
		} catch (TransactionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
