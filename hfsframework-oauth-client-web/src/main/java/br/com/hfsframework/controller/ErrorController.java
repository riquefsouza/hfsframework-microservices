package br.com.hfsframework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
/*
    @RequestMapping(value="/500")
    public @ResponseBody String handleException(HttpServletRequest req) {
        // you can get the exception thrown
        //Throwable t = (Throwable)req.getAttribute("javax.servlet.error.exception");

        // customize response to what you want
        return "Internal server error.";
    }
}
*/
	
	@RequestMapping(value = "/paginaErro")
	public String errorPage(HttpServletRequest req) {
		//Throwable t = (Throwable)req.getAttribute("javax.servlet.error.exception");
	
		return "error.html";
	}
}
