/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.report;

import org.slf4j.Logger;

import br.com.hfsframework.util.ExceptionUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class RelatorioException.
 */
public class ReportException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new relatorio exception.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public ReportException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Instantiates a new relatorio exception.
	 *
	 * @param causa
	 *            the causa
	 */
	public ReportException(Throwable causa) {
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
	public ReportException(Logger log, String mensagem, Throwable causa) {
		super(mensagem, causa);
		ExceptionUtil.getErros(log, this, mensagem, true);
	}

}
