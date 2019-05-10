/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class PermissaoVO.
 */
@Component
public class PermissaoVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The perfil. */
	private PerfilVO perfil;
	
	/** The paginas. */
	private List<PaginaVO> paginas;

	/**
	 * Instantiates a new permissao VO.
	 */
	public PermissaoVO() {
		super();
		this.paginas = new ArrayList<PaginaVO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.perfil = new PerfilVO();
		this.paginas.clear();
	}

	/**
	 * Pega o the perfil.
	 *
	 * @return o the perfil
	 */
	public PerfilVO getPerfil() {
		return perfil;
	}

	/**
	 * Atribui o the perfil.
	 *
	 * @param perfil
	 *            o novo the perfil
	 */
	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	/**
	 * Pega o the paginas.
	 *
	 * @return o the paginas
	 */
	public List<PaginaVO> getPaginas() {
		return paginas;
	}

	/**
	 * Atribui o the paginas.
	 *
	 * @param paginas
	 *            o novo the paginas
	 */
	public void setPaginas(List<PaginaVO> paginas) {
		this.paginas = paginas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PermissaoVO [perfil=" + perfil + ", paginas=" + paginas + "]";
	}



}
