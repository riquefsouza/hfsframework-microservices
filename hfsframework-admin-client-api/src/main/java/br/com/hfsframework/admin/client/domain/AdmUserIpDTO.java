package br.com.hfsframework.admin.client.domain;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmUserIpDTO implements BaseEntityRestClient<AdmUserIpDTO, Long> {

	private String jsonText;

	private Long id;
	
	private Long userSeq;

	private String ip;

	private Boolean active;
	
	private AdmUserDTO admUser;

	public AdmUserIpDTO() {
		super();
		this.admUser = new AdmUserDTO();
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AdmUserDTO getAdmUser() {
		return admUser;
	}

	public void setAdmUser(AdmUserDTO admUser) {
		this.admUser = admUser;
	}

	public void clear() {
		this.jsonText = "";
		this.id = null;
		this.userSeq = null;
		this.ip = null;
		this.active = false;
		this.admUser = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
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
		AdmUserIpDTO other = (AdmUserIpDTO) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
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
		JSONConverter<AdmUserIpDTO> conv = new JSONConverter<AdmUserIpDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmUserIpDTO> fromJSON(String jsonText) {
		JSONConverter<AdmUserIpDTO> conv = new JSONConverter<AdmUserIpDTO>();
		TypeReference<AdmUserIpDTO> mapType = new TypeReference<AdmUserIpDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmUserIpDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmUserIpDTO> list) {
		JSONListConverter<AdmUserIpDTO> conv = new JSONListConverter<AdmUserIpDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmUserIpDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmUserIpDTO> conv = new JSONListConverter<AdmUserIpDTO>();
		TypeReference<List<AdmUserIpDTO>> mapType = new TypeReference<List<AdmUserIpDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}	
}
