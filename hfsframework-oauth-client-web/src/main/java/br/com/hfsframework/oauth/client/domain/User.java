package br.com.hfsframework.oauth.client.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.JSONConverter;
import br.com.hfsframework.util.JSONListConverter;

public class User implements BaseEntityRestClient<User, Long> {

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
	
	private List<Role> roles;

	@Size(min=4, max=64)
	private String currentPassword;
	
	@Size(min=4, max=64)
	private String newPassword;
	
	@Size(min=4, max=64)
	private String confirmNewPassword;

	public User() {
		super();
		roles = new ArrayList<Role>();
		this.clear();
	}

	public User(String username, String password, String email, String urlPhoto, List<Role> roles) {
		this.username = username;
		this.password=password;
		this.email=email;
		this.urlPhoto=urlPhoto;
		this.roles = roles;
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
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
        this.roles.add(role);
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
		JSONConverter<User> conv = new JSONConverter<User>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<User> fromJSON(String jsonText) {
		JSONConverter<User> conv = new JSONConverter<User>();
		TypeReference<User> mapType = new TypeReference<User>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}
	
	@Override
	public Optional<User> fromJSON(){
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<User> list) {
		JSONListConverter<User> conv = new JSONListConverter<User>();
		return conv.listToJSON(list);
	}

	@Override
	public List<User> jsonToLista(String jsonText) {
		JSONListConverter<User> conv = new JSONListConverter<User>();
		TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
