package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmMenuDTO implements BaseEntityRestClient<AdmMenuDTO, Long> {

	private String jsonText;
	
	private Long id;
	
	@NotBlank
	@Size(min=4, max=255)
	private String description;
	
	private Integer order;
	
	private Long idPage;
	
	private AdmPageDTO admPage;
	
	private AdmMenuDTO admMenuParent;
	
	//private List<Long> admSubMenus;

	public AdmMenuDTO() {
		super();
		//admSubMenus = new ArrayList<Long>();
		admPage = new AdmPageDTO();
		this.clear();
	}

	public AdmMenuDTO(Long id) {
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdPage() {
		return idPage;
	}

	public void setIdPage(Long idPage) {
		this.idPage = idPage;
	}

	public AdmPageDTO getAdmPage() {
		return admPage;
	}

	public void setAdmPage(AdmPageDTO admPage) {
		this.admPage = admPage;
	}

	public AdmMenuDTO getAdmMenuParent() {
		return admMenuParent;
	}

	public void setAdmMenuParent(AdmMenuDTO admMenuParent) {
		this.admMenuParent = admMenuParent;
	}
/*
	public List<Long> getAdmSubMenus() {
		return admSubMenus;
	}

	public void setAdmSubMenus(List<Long> admSubMenus) {
		this.admSubMenus = admSubMenus;
	}
*/	
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
		AdmMenuDTO other = (AdmMenuDTO) obj;
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
		description = "";		
		order = null;
		idPage = null;
		admPage.clear();
		admMenuParent = null;
		//admSubMenus.clear();		
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmMenuDTO> conv = new JSONConverter<AdmMenuDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmMenuDTO> fromJSON(String jsonText) {
		JSONConverter<AdmMenuDTO> conv = new JSONConverter<AdmMenuDTO>();
		TypeReference<AdmMenuDTO> mapType = new TypeReference<AdmMenuDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmMenuDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmMenuDTO> list) {
		JSONListConverter<AdmMenuDTO> conv = new JSONListConverter<AdmMenuDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmMenuDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmMenuDTO> conv = new JSONListConverter<AdmMenuDTO>();
		TypeReference<List<AdmMenuDTO>> mapType = new TypeReference<List<AdmMenuDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}