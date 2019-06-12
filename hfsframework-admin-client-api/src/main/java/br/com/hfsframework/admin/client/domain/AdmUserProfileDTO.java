package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmUserProfileDTO implements BaseEntityRestClient<AdmUserProfileDTO, Long> {

	private String jsonText;
	
	private Long id;

	private Long userSeq;

	private Long profileSeq;

	private AdmProfileDTO admProfile;

	public AdmUserProfileDTO() {
		super();
		this.admProfile = new AdmProfileDTO();
	}	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getProfileSeq() {
		return profileSeq;
	}

	public void setProfileSeq(Long profileSeq) {
		this.profileSeq = profileSeq;
	}

	public AdmProfileDTO getAdmProfile() {
		return admProfile;
	}

	public void setAdmProfile(AdmProfileDTO admProfile) {
		this.admProfile = admProfile;
	}

	public void clear() {
		this.jsonText = "";
		this.userSeq = null;
		this.profileSeq = null;
		this.admProfile = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((profileSeq == null) ? 0 : profileSeq.hashCode());
		result = prime * result + ((userSeq == null) ? 0 : userSeq.hashCode());
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
		AdmUserProfileDTO other = (AdmUserProfileDTO) obj;
		if (profileSeq == null) {
			if (other.profileSeq != null)
				return false;
		} else if (!profileSeq.equals(other.profileSeq))
			return false;
		if (userSeq == null) {
			if (other.userSeq != null)
				return false;
		} else if (!userSeq.equals(other.userSeq))
			return false;
		return true;
	}	

	@Override
	public String toJSON() {
		JSONConverter<AdmUserProfileDTO> conv = new JSONConverter<AdmUserProfileDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmUserProfileDTO> fromJSON(String jsonText) {
		JSONConverter<AdmUserProfileDTO> conv = new JSONConverter<AdmUserProfileDTO>();
		TypeReference<AdmUserProfileDTO> mapType = new TypeReference<AdmUserProfileDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmUserProfileDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmUserProfileDTO> list) {
		JSONListConverter<AdmUserProfileDTO> conv = new JSONListConverter<AdmUserProfileDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmUserProfileDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmUserProfileDTO> conv = new JSONListConverter<AdmUserProfileDTO>();
		TypeReference<List<AdmUserProfileDTO>> mapType = new TypeReference<List<AdmUserProfileDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}
	
}
