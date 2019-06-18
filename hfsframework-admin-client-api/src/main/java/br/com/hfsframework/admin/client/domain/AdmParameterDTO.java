package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hfsframework.admin.client.deserializer.AdmParameterCategoryDTODeserializer;
import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmParameterDTO implements BaseEntityRestClient<AdmParameterDTO, Long> {

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

	@JsonDeserialize(using = AdmParameterCategoryDTODeserializer.class)
	//@JsonBackReference("parameter")
	private AdmParameterCategoryDTO admParameterCategory;

	public AdmParameterDTO() {
		super();
		this.admParameterCategory = new AdmParameterCategoryDTO();
		this.clear();
	}

	public AdmParameterDTO(Long id) {
		super();
	    this.id = id;
	}
	
	public AdmParameterDTO(Long id, String value, String description, String code, Long idParameterCategory, AdmParameterCategoryDTO parameterCategory) {
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
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

	public AdmParameterCategoryDTO getAdmParameterCategory() {
		return this.admParameterCategory;
	}

	public void setAdmParameterCategory(AdmParameterCategoryDTO admParameterCategory) {
		this.admParameterCategory = admParameterCategory;
	}

	public Long getIdAdmParameterCategory() {
		return idAdmParameterCategory;
	}

	public void setIdAdmParameterCategory(Long idAdmParameterCategory) {
		this.idAdmParameterCategory = idAdmParameterCategory;
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
		AdmParameterDTO other = (AdmParameterDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
		JSONConverter<AdmParameterDTO> conv = new JSONConverter<AdmParameterDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmParameterDTO> fromJSON(String jsonText) {
		JSONConverter<AdmParameterDTO> conv = new JSONConverter<AdmParameterDTO>();
		TypeReference<AdmParameterDTO> mapType = new TypeReference<AdmParameterDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmParameterDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmParameterDTO> list) {
		JSONListConverter<AdmParameterDTO> conv = new JSONListConverter<AdmParameterDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmParameterDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmParameterDTO> conv = new JSONListConverter<AdmParameterDTO>();
		TypeReference<List<AdmParameterDTO>> mapType = new TypeReference<List<AdmParameterDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}