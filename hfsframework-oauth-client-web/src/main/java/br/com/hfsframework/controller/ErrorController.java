package br.com.hfsframework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	//private static final Logger log = LogManager.getLogger(ErrorController.class);
	
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
		/*
		Throwable t = (Throwable)req.getAttribute("javax.servlet.error.exception");
		
		log.error("errorType: " + t.getClass());
		log.error("errorCausa: " + ExceptionUtils.getRootCauseMessage(t));
		log.error("errorCausaRaiz: " + ExceptionUtils.getRootCause(t));
		log.error("errorMsg: " + t.getMessage());
		log.error("errorStackTrace: " + ExceptionUtils.getStackTrace(t));
		 */
		return "error.html";
	}
}
