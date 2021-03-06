package br.com.hfsframework.admin.client.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hfsframework.admin.client.domain.AdmPageDTO;

public class AdmPageDeserializer extends JsonDeserializer<AdmPageDTO> {

	@Override
	public AdmPageDTO deserialize(JsonParser jp, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		final Long id = node.get("id").asLong();
		//JsonNode sublist = node.get();

		// Iterator<JsonNode> iter = sublist.elements();

		AdmPageDTO pc = new AdmPageDTO(id);
		//for (JsonNode item : sublist) {
			//objSubList = new ClassSubList(item.asLong());
			//pc.getSubClass().add(objSubList);
		//}

		return pc;
	}

}
