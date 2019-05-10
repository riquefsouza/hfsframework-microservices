/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

import org.slf4j.Logger;

import br.com.hfsframework.util.ExcecaoUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class RelatorioException.
 */
public class RelatorioException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new relatorio exception.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public RelatorioException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Instantiates a new relatorio exception.
	 *
	 * @param causa
	 *            the causa
	 */
	public RelatorioException(Throwable causa) {
		super(causa);
	}

	/**
	 * Instantiates a new relatorio exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 * @param causa
	 *            the causa
	 */
	public RelatorioException(Logger log, String mensagem, Throwable causa) {
		super(mensagem, causa);
		ExcecaoUtil.getErros(log, this, mensagem, true);
	}

}
