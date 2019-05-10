/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

// TODO: Auto-generated Javadoc
/**
 * The Class MetadadosExemplo.
 */
public class MetadadosExemplo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		MetadadosUtil mu = new MetadadosUtil();
		mu.configurar(MetadadosConfig.hfsbancoDS());
		
		System.out.println(mu.getMetadados("public").toString());
	}

}
