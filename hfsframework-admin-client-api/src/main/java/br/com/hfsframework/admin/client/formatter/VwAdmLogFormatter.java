package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.VwAdmLog;

public class VwAdmLogFormatter implements Formatter<VwAdmLog> {

	@Override
	public String print(VwAdmLog object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public VwAdmLog parse(String text, Locale locale) throws ParseException {
		VwAdmLog obj = new VwAdmLog();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
