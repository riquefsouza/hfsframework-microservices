package br.com.hfsframework.base.serializer;

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

public abstract class BaseListSerializer<T> extends StdSerializer<List<T>> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(BaseListSerializer.class);

	public BaseListSerializer() {
		this(null);
	}

	public BaseListSerializer(Class<List<T>> t) {
		super(t);
	}

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
