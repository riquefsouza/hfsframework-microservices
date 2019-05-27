package br.com.hfsframework.oauth.dto;

import java.util.List;
import java.util.Locale;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;

import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.domain.User;

public class NewUserDTO {

	@Autowired
	private MessageSource messageSource;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@URL
	private String urlPhoto;
	
	private List<Role> roles;

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

	public boolean isUsernameAndPasswordDifferent() {
		Assert.notNull(username, messageSource.getMessage("validation.NotNull", 
				new String[]{"user.username"}, Locale.getDefault()));
		Assert.notNull(password, messageSource.getMessage("validation.NotNull", 
				new String[]{"user.password"}, Locale.getDefault()));

		return !this.username.equals(password);
	}

	public User build() {
		return new User(username, password, email, urlPhoto, roles);
	}
	
}
