package br.com.hfsframework.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public final class JSONConverter<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(JSONConverter.class);

	public String toJSON(T obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			return objectMapper.writeValueAsString(obj);

		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public Optional<T> jsonToObject(String textJSON, TypeReference<T> mapType) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			//TypeReference<T> mapType = new TypeReference<T>() {
			//};
			T obj = objectMapper.readValue(textJSON, mapType);
			
			return Optional.of(obj);
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return Optional.empty();
	}

}
