package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmParameter;

public class AdmParameterFormatter implements Formatter<AdmParameter> {

	@Override
	public String print(AdmParameter object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmParameter parse(String text, Locale locale) throws ParseException {
		AdmParameter obj = new AdmParameter();
		obj.setId(Long.parseLong(text));
		return obj;
	}
}
