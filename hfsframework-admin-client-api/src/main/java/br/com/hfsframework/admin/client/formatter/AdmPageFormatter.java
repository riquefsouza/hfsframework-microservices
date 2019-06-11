package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmPageDTO;

public class AdmPageFormatter implements Formatter<AdmPageDTO> {

	@Override
	public String print(AdmPageDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmPageDTO parse(String text, Locale locale) throws ParseException {
		AdmPageDTO obj = new AdmPageDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
