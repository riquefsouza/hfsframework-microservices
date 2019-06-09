package br.com.hfsframework.util.converter;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public final class JSONSetConverter<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(JSONSetConverter.class);

	public String setToJSON(Set<T> list) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			return objectMapper.writeValueAsString(list);

		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public Set<T> jsonToSet(String textJSON, TypeReference<Set<T>> mapType) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			//TypeReference<Set<T>> mapType = new TypeReference<Set<T>>() {
			//};
			Set<T> jsonToSet = objectMapper.readValue(textJSON, mapType);
			return jsonToSet;			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return new HashSet<T>();
	}

}
