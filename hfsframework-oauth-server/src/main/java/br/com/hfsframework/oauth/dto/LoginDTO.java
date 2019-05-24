package br.com.hfsframework.oauth.dto;

import javax.validation.constraints.NotBlank;

public class LoginDTO {

	@NotBlank
	private String login;

	@NotBlank
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [login=" + login + ", password=" + password + "]";
	}

}
