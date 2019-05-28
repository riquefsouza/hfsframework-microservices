package br.com.hfsframework.base.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class BaseOAuth2RestUser extends User {

	private static final long serialVersionUID = 1L;

	private String urlAuthorizationServer;

	private OAuth2AccessToken accessToken;

	private boolean authenticated;

	private String messageException;

	public BaseOAuth2RestUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.urlAuthorizationServer = "";
		this.accessToken = null;
		this.messageException = "";
		this.authenticated = false;
	}
	
	public BaseOAuth2RestUser(BaseOAuth2RestUser other, String[] roles) {
		super(other.getUsername(), other.getPassword(), AuthorityUtils.createAuthorityList(roles));
		this.urlAuthorizationServer = other.getUrlAuthorizationServer();
		this.accessToken = other.getAccessToken();
		this.messageException = other.getMessageException();
		this.authenticated = other.isAuthenticated();		
	}

	public String getUrlAuthorizationServer() {
		return urlAuthorizationServer;
	}

	public void setUrlAuthorizationServer(String urlAuthorizationServer) {
		this.urlAuthorizationServer = urlAuthorizationServer;
	}

	public OAuth2AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(OAuth2AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getMessageException() {
		return messageException;
	}

	public void setMessageException(String messageException) {
		this.messageException = messageException;
	}

}
