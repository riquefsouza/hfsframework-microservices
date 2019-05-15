package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {
	
	//@Value("${server.contextPath}")
	//private String baseURL;

	@RequestMapping(value = "/home")
	public String index() {
		return "index.html";
	}
}
