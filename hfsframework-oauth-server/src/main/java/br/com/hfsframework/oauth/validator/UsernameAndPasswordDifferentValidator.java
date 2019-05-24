package br.com.hfsframework.oauth.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.hfsframework.oauth.dto.NewUserDTO;

public class UsernameAndPasswordDifferentValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return NewUserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewUserDTO dto = (NewUserDTO) target;
		if (!dto.isUsernameAndPasswordDifferent()) {
			errors.rejectValue("username", "",
					messageSource.getMessage("validation.usernameAndPasswordDifferent", 
							null, Locale.getDefault()));
		}
	}

}
