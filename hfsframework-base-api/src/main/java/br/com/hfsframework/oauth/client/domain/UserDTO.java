package br.com.hfsframework.oauth.client.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class UserDTO implements BaseEntityRestClient<UserDTO, Long> {

	private String jsonText;
	
	private Long id;

	@NotBlank
	@Size(min=4, max=64)
	private String username;

	@NotBlank
	@Size(min=4, max=64)
	private String password;

	@NotBlank
	@Email
	@Size(min=16, max=100)
	private String email;
	
	@NotBlank
	@URL
	@Size(min=8, max=255)
	private String urlPhoto;
			
	@Size(min=4, max=64)
	private String currentPassword;
	
	@Size(min=4, max=64)
	private String newPassword;
	
	@Size(min=4, max=64)
	private String confirmNewPassword;

	private Set<RoleDTO> roles;
	
	public UserDTO() {
		super();
		roles = new HashSet<RoleDTO>();
		this.clear();
	}

	public UserDTO(String username, String password, String email, String urlPhoto, Set<RoleDTO> roles) {
		this.username = username;
		this.password=password;
		this.email=email;
		this.urlPhoto=urlPhoto;
		this.roles = roles;
		this.currentPassword = "";
		this.newPassword = "";
		this.confirmNewPassword = "";
	}

	public UserDTO(Long id, String username, String password, String email, String urlPhoto) {
		this.id = id;
		this.username = username;
		this.password=password;
		this.email=email;
		this.urlPhoto=urlPhoto;
		this.roles = new HashSet<RoleDTO>();
		this.currentPassword = "";
		this.newPassword = "";
		this.confirmNewPassword = "";
	}
	
	@Override
	public void clear() {
		this.jsonText = "";
		this.id = null;
		this.username = "";
		this.password = "";
		this.email = "";
		this.urlPhoto = "";
		this.roles.clear();
		this.currentPassword = "";
		this.newPassword = "";
		this.confirmNewPassword = "";		
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
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	
	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	@Override
	public String toJSON() {
		JSONConverter<UserDTO> conv = new JSONConverter<UserDTO>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<UserDTO> fromJSON(String jsonText) {
		JSONConverter<UserDTO> conv = new JSONConverter<UserDTO>();
		TypeReference<UserDTO> mapType = new TypeReference<UserDTO>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}
	
	@Override
	public Optional<UserDTO> fromJSON(){
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<UserDTO> list) {
		JSONListConverter<UserDTO> conv = new JSONListConverter<UserDTO>();
		return conv.listToJSON(list);
	}

	@Override
	public List<UserDTO> jsonToLista(String jsonText) {
		JSONListConverter<UserDTO> conv = new JSONListConverter<UserDTO>();
		TypeReference<List<UserDTO>> mapType = new TypeReference<List<UserDTO>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", urlPhoto=" + urlPhoto + ", roles=" + roles + ", currentPassword=" + currentPassword
				+ ", newPassword=" + newPassword + ", confirmNewPassword=" + confirmNewPassword + "]";
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
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
