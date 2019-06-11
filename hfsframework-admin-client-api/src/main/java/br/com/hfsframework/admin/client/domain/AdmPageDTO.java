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

public class AdmPageDTO implements BaseEntityRestClient<AdmPageDTO, Long> {

	private String jsonText;
	
	private Long id;
	
	@NotBlank
	@Size(min=4, max=255)
	private String description;

	@NotBlank
	@Size(min=4, max=255)
	private String url;
	
	private Set<Long> admProfiles;
	
	private Set<Long> admMenus;
		
	public AdmPageDTO() {
		super();
		admProfiles = new HashSet<Long>();
		admMenus = new HashSet<Long>();
		this.clear();
	}

	public AdmPageDTO(Long id) {
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
		AdmPageDTO other = (AdmPageDTO) obj;
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
		this.description = "";
		this.url = "";
		admProfiles.clear();
		admMenus.clear();
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmPageDTO> conv = new JSONConverter<AdmPageDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmPageDTO> fromJSON(String jsonText) {
		JSONConverter<AdmPageDTO> conv = new JSONConverter<AdmPageDTO>();
		TypeReference<AdmPageDTO> mapType = new TypeReference<AdmPageDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmPageDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmPageDTO> list) {
		JSONListConverter<AdmPageDTO> conv = new JSONListConverter<AdmPageDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmPageDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmPageDTO> conv = new JSONListConverter<AdmPageDTO>();
		TypeReference<List<AdmPageDTO>> mapType = new TypeReference<List<AdmPageDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Long> getAdmProfiles() {
		return admProfiles;
	}

	public void setAdmProfiles(Set<Long> admProfiles) {
		this.admProfiles = admProfiles;
	}

	public Set<Long> getAdmMenus() {
		return admMenus;
	}

	public void setAdmMenus(Set<Long> admMenus) {
		this.admMenus = admMenus;
	}

}