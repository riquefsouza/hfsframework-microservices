/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseListSerializer.
 *
 * @param <T> the generic type
 */
public abstract class BaseListSerializer<T> extends StdSerializer<List<T>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(BaseListSerializer.class);

	/**
	 * Instantiates a new base list serializer.
	 */
	public BaseListSerializer() {
		this(null);
	}

	/**
	 * Instantiates a new base list serializer.
	 *
	 * @param t the t
	 */
	public BaseListSerializer(Class<List<T>> t) {
		super(t);
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(List<T> items, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Method metodo;
		Long valor;		
		List<Long> ids = new ArrayList<Long>();
		
		for (T item : items) {
			metodo = ReflectionUtils.findMethod(item.getClass(), "getId");
			valor = (Long) ReflectionUtils.invokeMethod(metodo, item);
			
			try {
				ids.add(valor);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
			}
		}
		generator.writeObject(ids);
	}
}
