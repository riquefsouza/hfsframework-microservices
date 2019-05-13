package br.com.hfsframework.admin.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hfsframework.admin.domain.Parametro;
import br.com.hfsframework.admin.domain.ParametroCategoria;

public class ParametroCategoriaDeserializer extends JsonDeserializer<ParametroCategoria> {

	@Override
	public ParametroCategoria deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		final Long id = node.get("id").asLong();
		final String descricao = node.get("descricao").asText();
		final Long ordem = node.get("ordem").asLong();
		JsonNode parametros = node.get("parametros");

		//Iterator<JsonNode> iter = parametros.elements();
		
		Parametro parametro;
		ParametroCategoria pc = new ParametroCategoria(id, descricao, ordem);
		for (JsonNode item : parametros) {
			parametro = new Parametro(item.asLong());
			//pc.getParametros().add(parametro);			
		}
				
		return pc;
	}

}
