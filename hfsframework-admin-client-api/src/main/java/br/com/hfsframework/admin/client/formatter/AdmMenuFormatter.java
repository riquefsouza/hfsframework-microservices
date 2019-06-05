package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmMenu;

public class AdmMenuFormatter implements Formatter<AdmMenu> {

	@Override
	public String print(AdmMenu object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmMenu parse(String text, Locale locale) throws ParseException {
		AdmMenu obj = new AdmMenu();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
