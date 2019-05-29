package br.com.hfsframework.oauth.client.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import br.com.hfsframework.base.client.BaseEntityRestClient;

public class User implements BaseEntityRestClient<Long> {

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
	}

	public User(String username, String password, String email, String urlPhoto, List<Role> roles) {
		this.username = username;
		this.password=password;
		this.email=email;
		this.urlPhoto=urlPhoto;
		this.roles = roles;
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
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", urlPhoto=" + urlPhoto + ", roles=" + roles + ", currentPassword=" + currentPassword
				+ ", newPassword=" + newPassword + ", confirmNewPassword=" + confirmNewPassword + "]";
	}

	
}
