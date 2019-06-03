package br.com.hfsframework.admin.client.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.JSONConverter;
import br.com.hfsframework.util.JSONListConverter;

public class AdmParameterCategory implements BaseEntityRestClient<AdmParameterCategory, Long> {

	private String jsonText;
	
	private Long id;

	private String description;

	private Long order;

	//@JsonManagedReference("admParameter")
	private List<Long> admParameters;

	public AdmParameterCategory() {
		super();
		this.admParameters = new ArrayList<Long>();
		this.clear();
	}

	/*
	@JsonCreator
	public admParameterCategory(@JsonProperty("admParameterCategory") Long id ) {
		super();		
	    this.id = id;
	    this.admParameters = new ArrayList<Long>();
	}
	*/

	public AdmParameterCategory(Long id) {
		super();
		this.id = id;
		this.admParameters = new ArrayList<Long>();
	}

	public AdmParameterCategory(Long id, String description, Long order) {
		super();
		this.id = id;
		this.description = description;
		this.order = order;
		this.admParameters = new ArrayList<Long>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrder() {
		return this.order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public List<Long> getParameters() {
		return this.admParameters;
	}

	public void setParameters(List<Long> parameters) {
		this.admParameters = parameters;
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
		AdmParameterCategory other = (AdmParameterCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParameterCategory [id=" + id + ", description=" + description + ", order=" + order + ", parameters="
				+ admParameters + "]";
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
	public void clear() {
		this.jsonText = "";
		this.id = null;
		this.description = null;
		this.order = null;
		this.admParameters.clear();
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmParameterCategory> conv = new JSONConverter<AdmParameterCategory>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmParameterCategory> fromJSON(String jsonText) {
		JSONConverter<AdmParameterCategory> conv = new JSONConverter<AdmParameterCategory>();
		TypeReference<AdmParameterCategory> mapType = new TypeReference<AdmParameterCategory>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmParameterCategory> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmParameterCategory> list) {
		JSONListConverter<AdmParameterCategory> conv = new JSONListConverter<AdmParameterCategory>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmParameterCategory> jsonToLista(String jsonText) {
		JSONListConverter<AdmParameterCategory> conv = new JSONListConverter<AdmParameterCategory>();
		TypeReference<List<AdmParameterCategory>> mapType = new TypeReference<List<AdmParameterCategory>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}