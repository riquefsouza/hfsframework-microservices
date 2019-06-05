package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.JSONConverter;
import br.com.hfsframework.util.JSONListConverter;

public class AdmUser implements BaseEntityRestClient<AdmUser, Long> {

	private String jsonText;
	
	private Long id;

	public AdmUser() {
		super();
		this.clear();
	}

	public AdmUser(Long id) {
		super();
	    this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
		AdmUser other = (AdmUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public void clear() {
		this.jsonText = "";
		this.id = null;
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmUser> conv = new JSONConverter<AdmUser>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmUser> fromJSON(String jsonText) {
		JSONConverter<AdmUser> conv = new JSONConverter<AdmUser>();
		TypeReference<AdmUser> mapType = new TypeReference<AdmUser>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmUser> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmUser> list) {
		JSONListConverter<AdmUser> conv = new JSONListConverter<AdmUser>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmUser> jsonToLista(String jsonText) {
		JSONListConverter<AdmUser> conv = new JSONListConverter<AdmUser>();
		TypeReference<List<AdmUser>> mapType = new TypeReference<List<AdmUser>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}