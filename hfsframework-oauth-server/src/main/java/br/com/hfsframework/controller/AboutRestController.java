package br.com.hfsframework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutRestController {

	@RequestMapping(value = "/about", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
    	return new ResponseEntity<>("This is the about page for application.", HttpStatus.OK);
    }
	
}
