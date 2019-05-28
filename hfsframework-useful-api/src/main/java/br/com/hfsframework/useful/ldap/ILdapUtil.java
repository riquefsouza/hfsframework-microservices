package br.com.hfsframework.useful.ldap;

import java.util.List;

import org.springframework.ldap.core.LdapTemplate;

public interface ILdapUtil {

	public LdapTemplate ldapTemplate();

	public boolean authenticate(String filter);

	public void create(String username, String password);
	
	public void modify(String username, String password);
	
	public List<String> search(String username);
	
	
}
