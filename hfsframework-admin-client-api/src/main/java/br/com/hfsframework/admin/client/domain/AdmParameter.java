package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hfsframework.admin.client.deserializer.AdmParameterCategoryDeserializer;
import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmParameter implements BaseEntityRestClient<AdmParameter, Long> {

	private String jsonText;
	
	private Long id;

	@NotBlank
	@Size(min=4, max=64)
	private String code;

	@NotBlank
	@Size(min=4, max=255)
	private String description;

	@NotBlank
	@Size(min=4, max=4000)
	private String value;

	private Long idAdmParameterCategory;

	@JsonDeserialize(using = AdmParameterCategoryDeserializer.class)
	//@JsonBackReference("parameter")
	private AdmParameterCategory admParameterCategory;

	public AdmParameter() {
		super();
		this.admParameterCategory = new AdmParameterCategory();
		this.clear();
	}

	public AdmParameter(Long id) {
		super();
	    this.id = id;
	}
	
	public AdmParameter(Long id, String value, String description, String code, Long idParameterCategory, AdmParameterCategory parameterCategory) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.value = value;
		this.idAdmParameterCategory = idParameterCategory;
		this.admParameterCategory = parameterCategory;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.code;
	}

	public void setCodigo(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AdmParameterCategory getParameterCategory() {
		return this.admParameterCategory;
	}

	public void setParameterCategory(AdmParameterCategory parameterCategory) {
		this.admParameterCategory = parameterCategory;
	}

	public Long getIdParameterCategory() {
		return idAdmParameterCategory;
	}

	public void setIdParameterCategory(Long idParameterCategory) {
		this.idAdmParameterCategory = idParameterCategory;
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
		AdmParameter other = (AdmParameter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parameter [id=" + id + ", code=" + code + ", description=" + description + ", value=" + value
				+ ", idParameterCategory=" + idAdmParameterCategory + ", parameterCategory=" + admParameterCategory + "]";
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
		this.code = "";
		this.description = "";
		this.value = "";
		this.idAdmParameterCategory = null;
		this.admParameterCategory.clear();
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmParameter> conv = new JSONConverter<AdmParameter>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmParameter> fromJSON(String jsonText) {
		JSONConverter<AdmParameter> conv = new JSONConverter<AdmParameter>();
		TypeReference<AdmParameter> mapType = new TypeReference<AdmParameter>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmParameter> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmParameter> list) {
		JSONListConverter<AdmParameter> conv = new JSONListConverter<AdmParameter>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmParameter> jsonToLista(String jsonText) {
		JSONListConverter<AdmParameter> conv = new JSONListConverter<AdmParameter>();
		TypeReference<List<AdmParameter>> mapType = new TypeReference<List<AdmParameter>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}