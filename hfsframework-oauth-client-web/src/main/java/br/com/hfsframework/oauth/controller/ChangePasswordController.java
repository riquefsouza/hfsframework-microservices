package br.com.hfsframework.oauth.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/private/changePasswordView")
public class ChangePasswordController {

	private static final Logger log = LogManager.getLogger(ChangePasswordController.class);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public ModelAndView listar(HttpServletRequest request, Principal principal, Authentication authentication) {		
		Principal principalRequest = request.getUserPrincipal();
		
		if (principalRequest!=null) {
			log.info("LOGIN: " + principalRequest.getName());
		}
		if (principal!=null) {
			log.info("LOGIN: " + principal.getName());
		}
		if (authentication!=null) {
			log.info("LOGIN: " + authentication.getName());
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			log.info("User has authorities: " + userDetails.getAuthorities());
		}
		
		//request.isUserInRole("someAuthority");
		//ModelAndView mv = new ModelAndView(getPaginaListar());
		//mv.addObject("bean", bean.get());
		//setEntidade(bean.get());
		ModelAndView mv = new ModelAndView("/private/changePassword");
		return mv;
	}
	

}
