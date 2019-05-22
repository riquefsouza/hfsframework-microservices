package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternationalController {

	@GetMapping("/public/international")
	public String getInternationalPage() {
		return "international";
	}
}
