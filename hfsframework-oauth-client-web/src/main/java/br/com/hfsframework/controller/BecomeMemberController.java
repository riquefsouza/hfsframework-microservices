package br.com.hfsframework.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.hfsframework.oauth.client.domain.User;

@Controller
public class BecomeMemberController {

	@PostMapping("/public/becomeMember")
	public RedirectView save(@Valid User obj, 
			BindingResult result, RedirectAttributes attributes) {
	
		
		return new RedirectView("/login.html");
	}

}
