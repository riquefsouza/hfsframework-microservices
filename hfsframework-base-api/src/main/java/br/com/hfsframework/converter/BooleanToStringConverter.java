/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// TODO: Auto-generated Javadoc
/**
 * The Class BooleanToStringConverter.
 */
@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public String convertToDatabaseColumn(Boolean value) {
		return (value != null && value) ? "S" : "N";
	}

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public Boolean convertToEntityAttribute(String value) {
		return "S".equals(value);
	}

}
