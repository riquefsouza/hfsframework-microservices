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
public class TemplateExample {

	/** The Constant pacote. */
	private static final String pacote = "br.com.hfsframework.admin";
	//private static final String pacote = "br.com.hfsframework.test.admin";
	
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
				{"AdmParameter", "Long"},
				{"AdmParameterCategory", "Long"}
		};
		
        String saida = "C:/Temp/blob";
        
        //String caminhoModelo = "";
        String caminhoModelo = "C:/ambiente/workspace/hfsframework-microservices/hfsframework-base-api/src/main/java/br/com/hfsframework/template";
        
        TemplateUtil.gerarCodigoFonte(TemplateEnum.IRepository, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.IService, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.Service, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.RestController, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.Controller, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.RestClient, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.Formatter, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.Deserializer, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.ListSerializer, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.list, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.edit, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.JSPList, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.JSPEdit, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.JSList, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.JSEdit, caminhoModelo, pacote, classes, saida);
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
