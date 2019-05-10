/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class NetUtil.
 */
public final class NetUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(NetUtil.class);

	/**
	 * Prints the ip host.
	 */
	public static void printIpHost() {
		try {
			// Local address
			log.info("Local IP address : " + InetAddress.getLocalHost().getHostAddress());
			log.info("Local Hostname : " + InetAddress.getLocalHost().getHostName());

			// Remote address
			log.info("Remote IP address : " + InetAddress.getLoopbackAddress().getHostAddress());
			log.info("Remote Hostname : " + InetAddress.getLoopbackAddress().getHostName());

		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Gets the local IP address.
	 *
	 * @return the local IP address
	 */
	public static String getLocalIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		}
		return "";
	}

	/**
	 * Gets the local hostname.
	 *
	 * @return the local hostname
	 */
	public static String getLocalHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		}
		return "";
	}

	/**
	 * Gets the remote IP address.
	 *
	 * @return the remote IP address
	 */
	public static String getRemoteIPAddress() {
		return InetAddress.getLoopbackAddress().getHostAddress();
	}

	/**
	 * Gets the remote hostname.
	 *
	 * @return the remote hostname
	 */
	public static String getRemoteHostname() {
		return InetAddress.getLoopbackAddress().getHostName();
	}

}
