/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.zip;

import org.slf4j.Logger;

import br.com.hfsframework.util.ExcecaoUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ZipException.
 */
public class ZipException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public ZipException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param causa
	 *            the causa
	 */
	public ZipException(Throwable causa) {
		super(causa);
	}

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 * @param causa
	 *            the causa
	 */
	public ZipException(Logger log, String mensagem, Throwable causa) {
		super(mensagem, causa);
		ExcecaoUtil.getErros(log, this, mensagem, true);
	}

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 */
	public ZipException(Logger log, String mensagem) {
		this(log, mensagem, null);
	}
}
