package br.com.hfsframework.admin.client.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.CPFCNPJUtil;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmUserDTO implements BaseEntityRestClient<AdmUserDTO, Long> {

	private String jsonText;
	
	private Long id;
	
	@Size(max=11)
	private BigDecimal cpf;

	@NotEmpty
	@Size(min=4, max=255)
	@Email
	private String email;

	@Size(max=255)
	private String ldapDN;

	@NotEmpty
	@Size(min=4, max=64)
	private String login;

	@NotEmpty
	@Size(min=4, max=64)
	private String name;

	@NotEmpty
	@Size(min=4, max=128)
	private String password;

	private Set<String> admUserIps;

	private LocalDateTime createdDate;
 
    private LocalDateTime modifiedDate;

    private String createdBy;
 
    private String modifiedBy;

	public AdmUserDTO() {
		super();
		this.admUserIps = new HashSet<String>();
		this.clear();
	}

	public AdmUserDTO(Long id) {
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


	public BigDecimal getCpf() {
		return cpf;
	}

	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLdapDN() {
		return ldapDN;
	}

	public void setLdapDN(String ldapDN) {
		this.ldapDN = ldapDN;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Set<String> getAdmUserIps() {
		return admUserIps;
	}

	public void setAdmUserIps(Set<String> admUserIps) {
		this.admUserIps = admUserIps;
	}	

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
		AdmUserDTO other = (AdmUserDTO) obj;
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
		this.cpf = BigDecimal.ZERO;
		this.email = "";
		this.ldapDN = "";
		this.login = "";
		this.name = "";
		this.password = "";
		this.admUserIps.clear();
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmUserDTO> conv = new JSONConverter<AdmUserDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmUserDTO> fromJSON(String jsonText) {
		JSONConverter<AdmUserDTO> conv = new JSONConverter<AdmUserDTO>();
		TypeReference<AdmUserDTO> mapType = new TypeReference<AdmUserDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmUserDTO> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmUserDTO> list) {
		JSONListConverter<AdmUserDTO> conv = new JSONListConverter<AdmUserDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmUserDTO> jsonToLista(String jsonText) {
		JSONListConverter<AdmUserDTO> conv = new JSONListConverter<AdmUserDTO>();
		TypeReference<List<AdmUserDTO>> mapType = new TypeReference<List<AdmUserDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

	public String getCpfFormatado() {
		try {
			return CPFCNPJUtil.formatCPForCPNJ(cpf.longValue(), false);
		} catch (Exception e) {
			return this.cpf.toString();
		}
	}

}