package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class VwAdmLogValue implements BaseEntityRestClient<VwAdmLogValue, Long> {

	private String jsonText;
	
	private Long id;

	public VwAdmLogValue() {
		super();
		this.clear();
	}
	
	public VwAdmLogValue(Long id) {
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
		VwAdmLogValue other = (VwAdmLogValue) obj;
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
		JSONConverter<VwAdmLogValue> conv = new JSONConverter<VwAdmLogValue>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<VwAdmLogValue> fromJSON(String jsonText) {
		JSONConverter<VwAdmLogValue> conv = new JSONConverter<VwAdmLogValue>();
		TypeReference<VwAdmLogValue> mapType = new TypeReference<VwAdmLogValue>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<VwAdmLogValue> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<VwAdmLogValue> list) {
		JSONListConverter<VwAdmLogValue> conv = new JSONListConverter<VwAdmLogValue>();
		return conv.listToJSON(list);
	}

	@Override
	public List<VwAdmLogValue> jsonToLista(String jsonText) {
		JSONListConverter<VwAdmLogValue> conv = new JSONListConverter<VwAdmLogValue>();
		TypeReference<List<VwAdmLogValue>> mapType = new TypeReference<List<VwAdmLogValue>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}