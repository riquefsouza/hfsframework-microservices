/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.exceptions;

import org.slf4j.Logger;

import br.com.hfsframework.util.ExceptionUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TransacaoException.
 */
public class TransactionException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new transacao exception.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public TransactionException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Instantiates a new transacao exception.
	 *
	 * @param causa
	 *            the causa
	 */
	public TransactionException(Throwable causa) {
		super(causa);
	}

	/**
	 * Instantiates a new transacao exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 * @param causa
	 *            the causa
	 */
	public TransactionException(Logger log, String mensagem, Throwable causa) {
		super(mensagem, causa);
		ExceptionUtil.getErros(log, this, mensagem, true);
	}

}
