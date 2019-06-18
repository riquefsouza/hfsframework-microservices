package br.com.hfsframework.admin.client.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;

public class AdmParameterCategoryDTODeserializer extends JsonDeserializer<AdmParameterCategoryDTO> {

	@Override
	public AdmParameterCategoryDTO deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		final Long id = node.get("id").asLong();
		final String description = node.get("description").asText();
		final Long order = node.get("order").asLong();
		//JsonNode admParameters = node.get("admParameters");

		//Iterator<JsonNode> iter = admParameters.elements();
		
		//AdmParameter admParameter;
		AdmParameterCategoryDTO pc = new AdmParameterCategoryDTO(id, description, order);
		//for (JsonNode item : admParameters) {
			//admParameter = new AdmParameter(item.asLong());
			//pc.getAdmParameters().add(admParameter);
			//pc.getAdmParameters().add(item.asLong());
		//}
		 
		return pc;
	}

}
