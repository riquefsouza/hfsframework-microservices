package br.com.hfsframework.oauth.validator;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.dto.NewUserDTO;
import br.com.hfsframework.oauth.service.IUserService;

public class NewUserValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	private IUserService service;

	public NewUserValidator(IUserService service) {
		this.service = service;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewUserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewUserDTO dto = (NewUserDTO) target;

		Optional<User> user = service.findByUsername(dto.getUsername());
		if (user.isPresent()) {
			errors.rejectValue("username", "", messageSource.getMessage("validation.login",
					new String[] { "user.username" }, Locale.getDefault()));
		}

	}

}
