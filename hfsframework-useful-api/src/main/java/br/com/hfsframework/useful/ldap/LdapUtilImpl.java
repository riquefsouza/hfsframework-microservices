package br.com.hfsframework.useful.ldap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.naming.Name;

import org.springframework.core.env.Environment;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapNameBuilder;

public class LdapUtilImpl implements LdapUtil {

	public LdapContextSource contextSource(Environment env) {
		LdapContextSource contextSource = new LdapContextSource();

		contextSource.setUrl(env.getRequiredProperty("ldap.url"));
		contextSource.setBase(env.getRequiredProperty("ldap.partitionSuffix"));
		contextSource.setUserDn(env.getRequiredProperty("ldap.principal"));
		contextSource.setPassword(env.getRequiredProperty("ldap.password"));

		return contextSource;
	}

	public LdapTemplate ldapTemplate(Environment env) {
		return new LdapTemplate(contextSource(env));
	}

	public void authenticate(Environment env, String username, String password) {
		contextSource(env).getContext("cn=" + username + ",ou=users," + env.getRequiredProperty("ldap.partitionSuffix"),
				password);
	}

	public void create(Environment env, String username, String password) {
		Name dn = LdapNameBuilder.newInstance().add("ou", "users").add("cn", username).build();
		DirContextAdapter context = new DirContextAdapter(dn);

		context.setAttributeValues("objectclass",
				new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("cn", username);
		context.setAttributeValue("sn", username);
		context.setAttributeValue("userPassword", digestSHA(password));

		ldapTemplate(env).bind(context);
	}

	public void modify(Environment env, String username, String password) {
		Name dn = LdapNameBuilder.newInstance().add("ou", "users").add("cn", username).build();
		DirContextOperations context = ldapTemplate(env).lookupContext(dn);

		context.setAttributeValues("objectclass",
				new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("cn", username);
		context.setAttributeValue("sn", username);
		context.setAttributeValue("userPassword", digestSHA(password));

		ldapTemplate(env).modifyAttributes(context);
	}

	public List<String> search(Environment env, String username) {
		return ldapTemplate(env).search("ou=users", "cn=" + username,
				(AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
	}

	private String digestSHA(final String password) {
		String base64;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(password.getBytes());
			base64 = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return "{SHA}" + base64;
	}
}
