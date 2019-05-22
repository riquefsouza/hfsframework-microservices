package br.com.hfsframework.useful.ldap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ldap.properties")
public class LdapUtilImpl implements LdapUtil {

	@Autowired
	private Environment env;

	private LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();

		String sServer = env.getRequiredProperty("ldap.server");
		String sPort = env.getRequiredProperty("ldap.port");
		String sBase = env.getRequiredProperty("ldap.base");
		String sUserDn = env.getRequiredProperty("ldap.userdn");
		String sPassword = env.getRequiredProperty("ldap.password");
		
		String sUrl = "ldap://" + sServer + ":" + sPort;
		
		contextSource.setUrl(sUrl);		
		contextSource.setBase(sBase);
		contextSource.setUserDn(sUserDn);
		contextSource.setPassword(sPassword);
		
		contextSource.afterPropertiesSet();

		return contextSource;
	}

	public LdapTemplate ldapTemplate() {		
		return new LdapTemplate(contextSource());
	}

	public boolean authenticate(String filter) {		
		String sUserDn = env.getRequiredProperty("ldap.userdn");
		String sPassword = env.getRequiredProperty("ldap.password");
		
		return ldapTemplate().authenticate(sUserDn, filter, sPassword);
	}

	public void create(String username, String password) {
		Name dn = LdapNameBuilder.newInstance().add("ou", "users").add("cn", username).build();
		DirContextAdapter context = new DirContextAdapter(dn);

		context.setAttributeValues("objectclass",
				new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("cn", username);
		context.setAttributeValue("sn", username);
		context.setAttributeValue("userPassword", digestSHA(password));

		ldapTemplate().bind(context);
	}

	public void modify(String username, String password) {
		Name dn = LdapNameBuilder.newInstance().add("ou", "users").add("cn", username).build();
		DirContextOperations context = ldapTemplate().lookupContext(dn);

		context.setAttributeValues("objectclass",
				new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("cn", username);
		context.setAttributeValue("sn", username);
		context.setAttributeValue("userPassword", digestSHA(password));

		ldapTemplate().modifyAttributes(context);
	}

	public List<String> search(String username) {
		return ldapTemplate().search("ou=users", "cn=" + username,
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
