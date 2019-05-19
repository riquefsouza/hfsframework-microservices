package br.com.hfsframework.admin.client.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hfsframework.admin.client.domain.AdmParameter;
import br.com.hfsframework.admin.client.domain.AdmParameterCategory;

public class AdmParameterCategoryDeserializer extends JsonDeserializer<AdmParameterCategory> {

	@Override
	public AdmParameterCategory deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		final Long id = node.get("id").asLong();
		final String descricao = node.get("descricao").asText();
		final Long ordem = node.get("ordem").asLong();
		JsonNode parametros = node.get("parametros");

		//Iterator<JsonNode> iter = parametros.elements();
		
		AdmParameter parametro;
		AdmParameterCategory pc = new AdmParameterCategory(id, descricao, ordem);
		for (JsonNode item : parametros) {
			parametro = new AdmParameter(item.asLong());
			//pc.getParametros().add(parametro);			
		}
				
		return pc;
	}

}
