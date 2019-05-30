package br.com.hfsframework.oauth.client.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hfsframework.oauth.client.domain.Role;
import br.com.hfsframework.oauth.client.domain.User;

public class UserDeserializer extends JsonDeserializer<User> {

	@Override
	public User deserialize(JsonParser jp, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		//final Long id = node.get("id").asLong();
		//final String descricao = node.get("descricao").asText();
		//final Long ordem = node.get("ordem").asLong();
		JsonNode roles = node.get("roles");

		//Iterator<JsonNode> iter = parametros.elements();
		
		
		Role role;
		User pc = new User();
		for (JsonNode item : roles) {
			role = new Role();
			role.setId(item.asLong());
			//pc.getParametros().add(parametro);			
		}
	
		return pc;
	}

}
