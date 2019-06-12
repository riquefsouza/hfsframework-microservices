package br.com.hfsframework.oauth.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.oauth.client.domain.RoleDTO;

public class RoleFormatter implements Formatter<RoleDTO> {

	@Override
	public String print(RoleDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public RoleDTO parse(String text, Locale locale) throws ParseException {
		RoleDTO obj = new RoleDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
