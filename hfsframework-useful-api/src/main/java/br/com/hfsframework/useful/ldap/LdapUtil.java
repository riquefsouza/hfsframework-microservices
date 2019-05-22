package br.com.hfsframework.useful.ldap;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

public interface LdapUtil {

	public LdapContextSource contextSource(Environment env);

	public LdapTemplate ldapTemplate(Environment env);

	public void authenticate(Environment env, String username, String password);

	public void create(Environment env, String username, String password);
	
	public void modify(Environment env, String username, String password);
	
	public List<String> search(Environment env, String username);
	
	
}
