package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.VwAdmLogDTO;

public class VwAdmLogFormatter implements Formatter<VwAdmLogDTO> {

	@Override
	public String print(VwAdmLogDTO object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public VwAdmLogDTO parse(String text, Locale locale) throws ParseException {
		VwAdmLogDTO obj = new VwAdmLogDTO();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
