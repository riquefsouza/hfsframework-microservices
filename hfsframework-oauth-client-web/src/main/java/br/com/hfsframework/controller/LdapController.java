package br.com.hfsframework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.hfsframework.useful.ldap.LdapAttributes;
import br.com.hfsframework.useful.ldap.LdapUtil;

@Controller
@RequestMapping(value = "/public/ldap")
public class LdapController {

	@Autowired
	private LdapUtil ldapUtil;

	@GetMapping("/list")
	public ModelAndView getLdapPage() {
		ModelAndView mv = new ModelAndView("ldap");
		List<LdapAttributes> lista = getLdapAttributes("henrique.souza");
		mv.addObject("listaLdap", lista);
		return mv;
	}

	public List<LdapAttributes> getLdapAttributes(String usuario) {
		//String ldapUserDN = "CN=consultapauta,OU=CGNC,OU=STI,OU=TRT,DC=trtrio,DC=gov,DC=br";
		//String ldapPassword = "C0nsult@2015";
		String ldapBaseDN = "OU=TRT,DC=trtrio,DC=gov,DC=br";
		String ldapFilter = "(cn=USER)";
		String filter = ldapFilter.replace("USER", usuario);

		String atributos[];

		String ldapAtributos = "description,userPrincipalName,objectCategory,name,memberOf,mail,distinguishedName,displayName,cn,logonCount";
		atributos = ldapAtributos.split(",");
		
		List<LdapAttributes> lista = new ArrayList<LdapAttributes>();
		
		if (ldapUtil.authenticate(filter)) {
		
			lista = ldapUtil.ldapTemplate().search(ldapBaseDN, filter, SearchControls.SUBTREE_SCOPE,
				atributos, new AttributesMapper<LdapAttributes>() {

					@Override
					public LdapAttributes mapFromAttributes(Attributes attributes) throws NamingException {
						LdapAttributes attr = new LdapAttributes();

						String atributo = (String) attributes.get("description").get();
						if (atributo != null && !atributo.isEmpty())
							attr.setRegistration(Long.parseLong(atributo));
						else
							attr.setRegistration(0L);

						attr.setLogin((String) attributes.get("name").get());
						attr.setName((String) attributes.get("displayname").get());
						attr.setEmail((String) attributes.get("mail").get());
						attr.setLdapDN((String) attributes.get("distinguishedname").get());

						return attr;
					}

				});
		
		}
		
		return lista;
	}
}
