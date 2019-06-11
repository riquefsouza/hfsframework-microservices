package br.com.hfsframework.admin.client.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class VwAdmLogDTO implements BaseEntityRestClient<VwAdmLogDTO, Long> {

	private String jsonText;
	
	private Long id;

	private String user;
	
	private Date date;
	
	private Long dateNumber;
	
	private String operation;
	
	private String ip;
	
	private String entity;
	
	private String mtable;
	
	private String key;
	
	public VwAdmLogDTO() {
		super();
		this.clear();
	}

	public VwAdmLogDTO(Long id) {
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


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(Long dateNumber) {
		this.dateNumber = dateNumber;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getMtable() {
		return mtable;
	}

	public void setMtable(String mtable) {
		this.mtable = mtable;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
		VwAdmLogDTO other = (VwAdmLogDTO) obj;
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
		JSONConverter<VwAdmLogDTO> conv = new JSONConverter<VwAdmLogDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<VwAdmLogDTO> fromJSON(String jsonText) {
		JSONConverter<VwAdmLogDTO> conv = new JSONConverter<VwAdmLogDTO>();
		TypeReference<VwAdmLogDTO> mapType = new TypeReference<VwAdmLogDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<VwAdmLogDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<VwAdmLogDTO> list) {
		JSONListConverter<VwAdmLogDTO> conv = new JSONListConverter<VwAdmLogDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<VwAdmLogDTO> jsonToLista(String jsonText) {
		JSONListConverter<VwAdmLogDTO> conv = new JSONListConverter<VwAdmLogDTO>();
		TypeReference<List<VwAdmLogDTO>> mapType = new TypeReference<List<VwAdmLogDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}