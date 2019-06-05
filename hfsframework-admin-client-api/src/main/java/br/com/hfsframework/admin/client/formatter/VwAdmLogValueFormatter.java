package br.com.hfsframework.admin.client.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.hfsframework.admin.client.domain.VwAdmLogValue;

public class VwAdmLogValueFormatter implements Formatter<VwAdmLogValue> {

	@Override
	public String print(VwAdmLogValue object, Locale locale) {
		return object.getId().toString();
	}

	@Override
	public VwAdmLogValue parse(String text, Locale locale) throws ParseException {
		VwAdmLogValue obj = new VwAdmLogValue();
		obj.setId(Long.parseLong(text));
		return obj;
	}

}
