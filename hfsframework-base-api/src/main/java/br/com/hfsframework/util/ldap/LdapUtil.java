package br.com.hfsframework.util.ldap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LdapUtil implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* The log. 
	private static final Logger log = LoggerFactory.getLogger(LdapUtil.class);
*/
	private LdapBundle config;
	
	/** The lista ldap atributo. */
	private List<LdapAtributo> listaLdapAtributo;
	
	/**
	 * Instantiates a new ldap util.
	 */
	public LdapUtil() {
		super();
		this.listaLdapAtributo = new ArrayList<LdapAtributo>();
	}

	public void configurar(LdapBundle config){
		this.config = config;
	}

	public int getPortaLDAP() {
		if ("LDAP".compareTo(config.getLdapTipoConexao()) == 0) {
			return config.getLdapPorta();
		}

		if ("LDAPS".compareTo(config.getLdapTipoConexao()) == 0) {
			return config.getLdapSPorta();
		}

		return -1;
	}
	

	public boolean login(String usuario, String senha) {
		return false;
	}
	
	public List<LdapAtributo> getAtributos(String usuario) {
		List<LdapAtributo> lista = new ArrayList<LdapAtributo>();
		
		return lista;
	}
	
	/**
	 * Pega o the lista ldap atributo.
	 *
	 * @return o the lista ldap atributo
	 */
	public List<LdapAtributo> getListaLdapAtributo() {
		return listaLdapAtributo;
	}

	/**
	 * Atribui o the lista ldap atributo.
	 *
	 * @param listaLdapAtributo
	 *            o novo the lista ldap atributo
	 */
	public void setListaLdapAtributo(List<LdapAtributo> listaLdapAtributo) {
		this.listaLdapAtributo = listaLdapAtributo;
	}
	
	/**
	 * Gets the atributo.
	 *
	 * @param atributo
	 *            the atributo
	 * @return the atributo
	 */
	public String getAtributo(String atributo) {
		String svalor = "";
		List<LdapAtributo> atributos = this.getListaLdapAtributo();
		if (!atributos.isEmpty()) {
			for (LdapAtributo item : atributos) {
				if (item.getId().equals(atributo)) {
					svalor = item.getValor();
					break;
				}
			}
		}
		return svalor;
	}
	
	/**
	 * Gets the matricula.
	 *
	 * @return the matricula
	 */
	public Long getMatricula() {
		String atributo = getAtributo("description");
		if (atributo!=null && !atributo.isEmpty())
			return Long.parseLong(atributo);
		else
			return 0L;
	}
	
	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin(){
		return getAtributo("name");		
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome(){
		return getAtributo("displayname");		
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail(){
		return getAtributo("mail");
	}
	
	/**
	 * Gets the ldap DN.
	 *
	 * @return the ldap DN
	 */
	public String getLdapDN(){
		return getAtributo("distinguishedname");
	}	
}
