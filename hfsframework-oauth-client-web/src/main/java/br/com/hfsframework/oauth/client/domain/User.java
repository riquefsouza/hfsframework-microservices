package br.com.hfsframework.oauth.client.domain;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import br.com.hfsframework.base.client.BaseEntityRestClient;

public class User implements BaseEntityRestClient<Long> {

	@NotBlank
	private Long id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	private List<Role> roles;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@URL
	private String urlPhoto;
	
	private String currentPassword;		
	private String newPassword;
	private String confirmNewPassword;

	public User() {
		super();
	}

	public User(String username, String password, String email, String urlPhoto, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.urlPhoto = urlPhoto;		
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + ", email="
				+ email + ", urlPhoto=" + urlPhoto + "]";
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

}
