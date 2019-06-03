package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmParameterCategory;

public class AdmParameterCategoryFormatter implements Formatter<AdmParameterCategory> {

	@Override
	public String print(AdmParameterCategory object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmParameterCategory parse(String text, Locale locale) throws ParseException {
		AdmParameterCategory obj = new AdmParameterCategory();
		obj.setId(Long.parseLong(text));
		return obj;
	}
}
