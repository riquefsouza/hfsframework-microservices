package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmPage;

public class AdmPageFormatter implements Formatter<AdmPage> {

	@Override
	public String print(AdmPage object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmPage parse(String text, Locale locale) throws ParseException {
		AdmPage obj = new AdmPage();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
