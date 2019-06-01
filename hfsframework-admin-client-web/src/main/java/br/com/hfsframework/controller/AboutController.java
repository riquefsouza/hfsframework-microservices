package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
	
	@RequestMapping(value = "/private/about")
	public String getAbout() {
		return "/private/about-client";
	}
}
