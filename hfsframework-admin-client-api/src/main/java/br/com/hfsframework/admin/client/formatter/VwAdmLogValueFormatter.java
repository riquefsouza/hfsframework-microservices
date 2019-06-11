package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.VwAdmLogValueDTO;

public class VwAdmLogValueFormatter implements Formatter<VwAdmLogValueDTO> {

	@Override
	public String print(VwAdmLogValueDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public VwAdmLogValueDTO parse(String text, Locale locale) throws ParseException {
		VwAdmLogValueDTO obj = new VwAdmLogValueDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
