package br.com.hfsframework.admin.client.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmParameterCategoryDTO implements BaseEntityRestClient<AdmParameterCategoryDTO, Long> {

	private String jsonText;
	
	private Long id;

	@NotBlank
	@Size(min=4, max=64)
	private String description;

	private Long order;

	//@JsonManagedReference("admParameter")
	private Set<Long> admParameters;

	public AdmParameterCategoryDTO() {
		super();
		this.admParameters = new HashSet<Long>();
		this.clear();
	}

	/*
	@JsonCreator
	public admParameterCategory(@JsonProperty("admParameterCategory") Long id ) {
		super();		
	    this.id = id;
	    this.admParameters = new HashSet<Long>();
	}
	*/

	public AdmParameterCategoryDTO(Long id) {
		super();
		this.id = id;
		this.admParameters = new HashSet<Long>();
	}

	public AdmParameterCategoryDTO(Long id, String description, Long order) {
		super();
		this.id = id;
		this.description = description;
		this.order = order;
		this.admParameters = new HashSet<Long>();
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

	public Set<Long> getAdmParameters() {
		return this.admParameters;
	}

	public void setAdmParameters(Set<Long> admParameters) {
		this.admParameters = admParameters;
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
		AdmParameterCategoryDTO other = (AdmParameterCategoryDTO) obj;
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
		JSONConverter<AdmParameterCategoryDTO> conv = new JSONConverter<AdmParameterCategoryDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmParameterCategoryDTO> fromJSON(String jsonText) {
		JSONConverter<AdmParameterCategoryDTO> conv = new JSONConverter<AdmParameterCategoryDTO>();
		TypeReference<AdmParameterCategoryDTO> mapType = new TypeReference<AdmParameterCategoryDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmParameterCategoryDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmParameterCategoryDTO> list) {
		JSONListConverter<AdmParameterCategoryDTO> conv = new JSONListConverter<AdmParameterCategoryDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmParameterCategoryDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmParameterCategoryDTO> conv = new JSONListConverter<AdmParameterCategoryDTO>();
		TypeReference<List<AdmParameterCategoryDTO>> mapType = new TypeReference<List<AdmParameterCategoryDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}