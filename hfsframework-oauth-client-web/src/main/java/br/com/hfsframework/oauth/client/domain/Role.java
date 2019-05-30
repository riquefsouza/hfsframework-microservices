package br.com.hfsframework.oauth.client.domain;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.JSONConverter;
import br.com.hfsframework.util.JSONListConverter;

public class Role implements BaseEntityRestClient<Role, Long> {
	
	private String jsonText; 
	
	private Long id;

	@NotBlank
	@Size(min=4, max=64)
	private String name;

	public Role() {
		super();
		this.clear();
	}
	
	public Role(String name) {
		super();
		this.name = name;
	}

	@Override
	public void clear() {
		this.jsonText = "";
		this.id = null;
		this.name = "";
	}

	@Override
	public String getJsonText() {
		return this.jsonText;
	}

	@Override
	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;		
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toJSON() {
		JSONConverter<Role> conv = new JSONConverter<Role>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<Role> fromJSON(String jsonText) {
		JSONConverter<Role> conv = new JSONConverter<Role>();
		TypeReference<Role> mapType = new TypeReference<Role>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<Role> fromJSON(){
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<Role> list) {
		JSONListConverter<Role> conv = new JSONListConverter<Role>();
		return conv.listToJSON(list);
	}

	@Override
	public List<Role> jsonToLista(String jsonText) {
		JSONListConverter<Role> conv = new JSONListConverter<Role>();
		TypeReference<List<Role>> mapType = new TypeReference<List<Role>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
