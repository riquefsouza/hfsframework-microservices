package br.com.hfsframework.util.converter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public final class JSONListConverter<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(JSONListConverter.class);

	public String listToJSON(List<T> list) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			return objectMapper.writeValueAsString(list);

		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public List<T> jsonToList(String textJSON, TypeReference<List<T>> mapType) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			//TypeReference<List<T>> mapType = new TypeReference<List<T>>() {
			//};
			List<T> jsonToList = objectMapper.readValue(textJSON, mapType);
			return jsonToList;			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return new ArrayList<T>();
	}

}
