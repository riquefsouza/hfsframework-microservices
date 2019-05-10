/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

import java.io.IOException;

import freemarker.template.TemplateException;

// TODO: Auto-generated Javadoc
/**
 * The Class TemplateUtil.
 */
public class TemplateExemplo {

	/** The Constant pacote. */
	//private static final String pacote = "br.com.hfsframework.admin";
	private static final String pacote = "br.com.hfsframework.test.admin";
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TemplateException
	 *             the template exception
	 */
	public static void main(String[] args) throws IOException, TemplateException {
		//final SLF4JLoggerContext ctx = PrivateManager.getContext();
        //ctx.reconfigure();

		String[][] classes = {	
				{"AdmMenu", "Long"},
				{"AdmPagina", "Long"},
				{"AdmParametro", "Long"},
				{"AdmParametroCategoria", "Long"},
				{"AdmPerfil", "Long"},
				{"AdmUsuario", "Long"},
				{"VwAdmLog", "Long"},
				{"VwAdmLogValor", "Long"}
		};
		
        String saida = "C:/Temp/destino";
        
        String caminhoModelo = "";
        //String caminhoModelo = "C:/demoiselle3/workspace/hfssistema/src/main/java/br/com/hfsframework/template";
        
        TemplateUtil.gerarCodigoFonte(TemplateEnum.ServiceTest, caminhoModelo, pacote, classes, saida);
	}
/*	
	private static class PrivateManager extends LogManager {
        private static final String FQCN = org.apache.logging.log4j.LogManager.class.getName();

        public static SLF4JLoggerContext getContext() {
            return (SLF4JLoggerContext) getContext(FQCN, false);
        }

        public static org.apache.logging.log4j.Logger getLogger(final String name) {
            return getLogger(FQCN, name);
        }
    }
*/
}
