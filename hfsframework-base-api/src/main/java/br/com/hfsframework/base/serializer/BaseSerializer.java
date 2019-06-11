package br.com.hfsframework.base.serializer;

import java.io.IOException;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class BaseSerializer<T> extends StdSerializer<T> {

	private static final long serialVersionUID = 1L;

	public BaseSerializer() {
		this(null);
	}

	public BaseSerializer(Class<T> t) {
		super(t);
	}

	@Override
	public void serialize(T item, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Method metodo;
		Long id;
		
		metodo = ReflectionUtils.findMethod(item.getClass(), "getId");
		id = (Long) ReflectionUtils.invokeMethod(metodo, item);
		
		generator.writeObject(id);
	}
}
