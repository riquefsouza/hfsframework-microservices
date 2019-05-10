package br.com.hfsframework.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.domain.User;
import br.com.hfsframework.domain.UserRole;
import br.com.hfsframework.service.SignupService;

@RestController
public class SignupRestController {
	
	@Autowired
	private SignupService signupService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
   		user.setRoles(Arrays.asList(new UserRole("USER")));
    	//User newUser = signupService.addUser(user);
   		signupService.addUser(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
