package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmParameterDTO;

public class AdmParameterFormatter implements Formatter<AdmParameterDTO> {

	@Override
	public String print(AdmParameterDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmParameterDTO parse(String text, Locale locale) throws ParseException {
		AdmParameterDTO obj = new AdmParameterDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}
}
