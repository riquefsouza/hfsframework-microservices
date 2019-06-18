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

public class AdmProfileDTO implements BaseEntityRestClient<AdmProfileDTO, Long> {

	private String jsonText;
	
	private Long id;

	private Boolean administrator;
	
	@NotBlank
	@Size(min=4, max=255)
	private String description;
	
	private Boolean general;
	
	private Set<AdmPageDTO> admPages;
	
	private Set<AdmUserDTO> admUsers;
	
	public AdmProfileDTO() {
		super();
		this.admPages = new HashSet<AdmPageDTO>();
		this.admUsers = new HashSet<AdmUserDTO>();		
		this.clear();
	}

	public AdmProfileDTO(Long id) {
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

	public Boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getGeneral() {
		return general;
	}

	public void setGeneral(Boolean general) {
		this.general = general;
	}

	public Set<AdmPageDTO> getAdmPages() {
		return admPages;
	}

	public void setAdmPages(Set<AdmPageDTO> admPages) {
		this.admPages = admPages;
	}

	public Set<AdmUserDTO> getAdmUsers() {
		return admUsers;
	}

	public void setAdmUsers(Set<AdmUserDTO> admUsers) {
		this.admUsers = admUsers;
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
		AdmProfileDTO other = (AdmProfileDTO) obj;
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
		this.administrator = false;
		this.description = "";
		this.general = false;
		this.admPages.clear();
		this.admUsers.clear();		
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmProfileDTO> conv = new JSONConverter<AdmProfileDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmProfileDTO> fromJSON(String jsonText) {
		JSONConverter<AdmProfileDTO> conv = new JSONConverter<AdmProfileDTO>();
		TypeReference<AdmProfileDTO> mapType = new TypeReference<AdmProfileDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmProfileDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmProfileDTO> list) {
		JSONListConverter<AdmProfileDTO> conv = new JSONListConverter<AdmProfileDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmProfileDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmProfileDTO> conv = new JSONListConverter<AdmProfileDTO>();
		TypeReference<List<AdmProfileDTO>> mapType = new TypeReference<List<AdmProfileDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}