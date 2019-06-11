package br.com.hfsframework.base.serializer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class BaseSetSerializer<T> extends StdSerializer<Set<T>> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(BaseSetSerializer.class);

	public BaseSetSerializer() {
		this(null);
	}

	public BaseSetSerializer(Class<Set<T>> t) {
		super(t);
	}

	@Override
	public void serialize(Set<T> items, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Method metodo;
		Long valor;		
		Set<Long> ids = new HashSet<Long>();
		
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
