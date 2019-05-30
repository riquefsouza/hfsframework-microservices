package br.com.hfsframework.oauth.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.oauth.client.domain.Role;

public class RoleFormatter implements Formatter<Role> {

	@Override
	public String print(Role object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public Role parse(String text, Locale locale) throws ParseException {
		Role obj = new Role();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
