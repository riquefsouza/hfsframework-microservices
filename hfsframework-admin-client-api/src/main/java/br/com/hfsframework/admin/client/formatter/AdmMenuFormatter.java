package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmMenuDTO;

public class AdmMenuFormatter implements Formatter<AdmMenuDTO> {

	@Override
	public String print(AdmMenuDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmMenuDTO parse(String text, Locale locale) throws ParseException {
		AdmMenuDTO obj = new AdmMenuDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
