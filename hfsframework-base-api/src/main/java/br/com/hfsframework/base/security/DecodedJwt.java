package br.com.hfsframework.base.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DecodedJwt implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId;
	private String jti;
	private Integer exp;
	private String organization;
	private List<String> authorities;
	private List<String> scope;
	private String userName;
	private String email;
	private String urlPhoto;

	@SuppressWarnings("unchecked")
	public static DecodedJwt getDecodedJwt(String accessToken) {
		DecodedJwt decodedJwt = new DecodedJwt();

		if (!accessToken.isEmpty()) {
			try {
				Jwt jwtToken = JwtHelper.decode(accessToken);

				String claims = jwtToken.getClaims();
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> claimsMap = objectMapper.readValue(claims, Map.class);

				return new DecodedJwt(claimsMap);

			} catch (IOException e) {
				return decodedJwt;
			}
		}
		return decodedJwt;
	}
	
	public DecodedJwt() {
		super();
		this.clientId = "";
		this.jti = "";
		this.exp = 0;
		this.organization = "";
		this.authorities = new ArrayList<String>();
		this.scope = new ArrayList<String>();
		this.userName = "";
		this.email = "";
		this.urlPhoto = "";
	}

	@SuppressWarnings("unchecked")
	public DecodedJwt(Map<String, Object> claimsMap) {
		super();
		this.clientId = (String) claimsMap.get("client_id");
		this.jti = (String) claimsMap.get("jti");
		this.exp = (Integer) claimsMap.get("exp");
		this.organization = (String) claimsMap.get("organization");
		this.authorities = (List<String>) claimsMap.get("authorities");
		this.scope = (List<String>) claimsMap.get("scope");
		this.userName = (String) claimsMap.get("user_name");
		this.email = (String) claimsMap.get("email");
		this.urlPhoto = (String) claimsMap.get("url_photo");
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public List<String> getScope() {
		return scope;
	}

	public void setScope(List<String> scope) {
		this.scope = scope;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
