package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.AdmUserDTO;

public class AdmUserFormatter implements Formatter<AdmUserDTO> {

	@Override
	public String print(AdmUserDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public AdmUserDTO parse(String text, Locale locale) throws ParseException {
		AdmUserDTO obj = new AdmUserDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
