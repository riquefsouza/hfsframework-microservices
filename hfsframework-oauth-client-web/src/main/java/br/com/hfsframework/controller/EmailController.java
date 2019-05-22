package br.com.hfsframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.useful.mail.MailUtil;

@Controller
public class EmailController {

	@Autowired
	private MailUtil mailUtil;
	
	@RequestMapping(value = "/public/email")
	public String getEmailPage() {
		return "email";
	}

	@RequestMapping(value = "/public/email/send")
	public String getSendEmail() {
		
		mailUtil.sendSimpleMessage("henrique.souza@trt1.jus.br", "TESTE", "hfs framework");
		
		return "email";
	}
}
