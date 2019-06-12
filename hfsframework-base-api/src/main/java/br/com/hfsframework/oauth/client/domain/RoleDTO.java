package br.com.hfsframework.oauth.client.domain;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class RoleDTO implements BaseEntityRestClient<RoleDTO, Long> {
	
	private String jsonText; 
	
	private Long id;

	@NotBlank
	@Size(min=4, max=64)
	private String name;

	public RoleDTO() {
		super();
		this.clear();
	}
	
	public RoleDTO(Long id) {
		super();
		this.id = id;
	}
	
	public RoleDTO(String name) {
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
		JSONConverter<RoleDTO> conv = new JSONConverter<RoleDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<RoleDTO> fromJSON(String jsonText) {
		JSONConverter<RoleDTO> conv = new JSONConverter<RoleDTO>();
		TypeReference<RoleDTO> mapType = new TypeReference<RoleDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<RoleDTO> fromJSON(){
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<RoleDTO> list) {
		JSONListConverter<RoleDTO> conv = new JSONListConverter<RoleDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<RoleDTO> jsonToLista(String jsonText) {
		JSONListConverter<RoleDTO> conv = new JSONListConverter<RoleDTO>();
		TypeReference<List<RoleDTO>> mapType = new TypeReference<List<RoleDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
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
		RoleDTO other = (RoleDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
