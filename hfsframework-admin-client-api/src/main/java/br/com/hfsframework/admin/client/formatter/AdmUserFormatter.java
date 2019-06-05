package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmUser;

public class AdmUserFormatter implements Formatter<AdmUser> {

	@Override
	public String print(AdmUser object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmUser parse(String text, Locale locale) throws ParseException {
		AdmUser obj = new AdmUser();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
