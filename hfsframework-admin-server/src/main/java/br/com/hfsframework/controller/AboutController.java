package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class AboutController {
	
	@RequestMapping(value = "/api/public/about")
	public String getAbout() {
		return "/about-server.html";
	}
}
