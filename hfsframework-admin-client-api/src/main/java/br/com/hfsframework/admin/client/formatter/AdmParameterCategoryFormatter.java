package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;

public class AdmParameterCategoryFormatter implements Formatter<AdmParameterCategoryDTO> {

	@Override
	public String print(AdmParameterCategoryDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmParameterCategoryDTO parse(String text, Locale locale) throws ParseException {
		AdmParameterCategoryDTO obj = new AdmParameterCategoryDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}
}
