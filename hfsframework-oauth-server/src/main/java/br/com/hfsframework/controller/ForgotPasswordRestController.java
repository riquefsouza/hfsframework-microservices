package br.com.hfsframework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.oauth.client.domain.UserDTO;
import br.com.hfsframework.oauth.service.IUserService;
import br.com.hfsframework.useful.mail.IMailUtil;

@RestController
public class ForgotPasswordRestController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IMailUtil mailUtil;

	@GetMapping("/api/public/forgotPassword")
	public ResponseEntity<String> send(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "subject", required = true) String subject,
			@RequestParam(name = "text", required = true) String text) {

		Optional<UserDTO> user = userService.findByUsername(username);

		try {
			if (user.isPresent()) {
				mailUtil.sendSimpleMessage(user.get().getEmail(), subject, text);
			} else {
				user = userService.findByEmail(username);

				if (user.isPresent()) {
					mailUtil.sendSimpleMessage(user.get().getEmail(), subject, text);
				} else {
					return ResponseEntity.notFound().build();
				}
			}
		} catch (MailException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(user.get().getUsername());
	}
}
