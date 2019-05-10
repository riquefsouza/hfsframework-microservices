package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@ApiIgnore
public class HomeController {
	
	//@Value("${server.contextPath}")
	//private String baseURL;

	@RequestMapping(value = "/")
	public String index() {
		//return "redirect:swagger-ui.html";
		return "index.html";
	}
}
