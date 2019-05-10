/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.pdf;

import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PdfException.
 */
public class PdfException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new pdf exception.
	 *
	 * @param message
	 *            the message
	 */
	public PdfException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new pdf exception.
	 *
	 * @param log
	 *            the log
	 * @param message
	 *            the message
	 */
	public PdfException(Logger log, String message) {
		super(message);
		log.fatal(message);
	}

}
