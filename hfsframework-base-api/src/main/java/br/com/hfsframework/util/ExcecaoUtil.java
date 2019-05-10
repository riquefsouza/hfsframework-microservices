/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcecaoUtil.
 */
public final class ExcecaoUtil implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the erros.
	 *
	 * @param log
	 *            the log
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 * @param mostrarStackTrace
	 *            the mostrar stack trace
	 * @return the erros
	 */
	public static String getErros(Logger log, Throwable e, String mensagem, boolean mostrarStackTrace) {
		String mens = "<br/>[Tipo]: " + e.getClass();
		mens += "<br/>[Causa]: " + ExceptionUtils.getRootCauseMessage(e);
		mens += "<br/>[Causa Raiz]: " + ExceptionUtils.getRootCause(e);
		mens += "<br/>[Mensagem]: " + e.getMessage();
		if (mostrarStackTrace) {
			mens += "<br/>[StackTrace]: " + ExceptionUtils.getStackTrace(e);
		}
		log.error(mensagem + "\n" + mens);
		return mensagem + "<br/>" + mens;
	}

	/**
	 * Gets the erros.
	 *
	 * @param log
	 *            the log
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 * @return the erros
	 */
	public static String getErros(Logger log, Throwable e, String mensagem) {
		return getErros(log, e, mensagem, false);
	}

}
