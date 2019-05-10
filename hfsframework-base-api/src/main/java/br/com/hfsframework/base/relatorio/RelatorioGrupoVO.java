/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base.relatorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class RelatorioGrupoVO.
 */
@Component
public class RelatorioGrupoVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The grupo. */
	private String grupo;
	
	/** The tipos. */
	private List<RelatorioTipoEnum> tipos;

	/**
	 * Instantiates a new relatorio grupo VO.
	 */
	public RelatorioGrupoVO() {
		super();
	}

	/**
	 * Instantiates a new relatorio grupo VO.
	 *
	 * @param id the id
	 * @param grupo the grupo
	 * @param tipos the tipos
	 */
	public RelatorioGrupoVO(String grupo, List<RelatorioTipoEnum> tipos) {
		super();
		this.grupo = grupo;
		this.tipos = tipos;
	}

	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Sets the grupo.
	 *
	 * @param grupo the new grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Gets the tipos.
	 *
	 * @return the tipos
	 */
	public List<RelatorioTipoEnum> getTipos() {
		return tipos;
	}

	/**
	 * Sets the tipos.
	 *
	 * @param tipos the new tipos
	 */
	public void setTipos(List<RelatorioTipoEnum> tipos) {
		this.tipos = tipos;
	}

}
