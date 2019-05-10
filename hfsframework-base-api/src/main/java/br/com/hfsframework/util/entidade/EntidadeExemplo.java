/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.entidade;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import freemarker.template.TemplateException;

// TODO: Auto-generated Javadoc
/**
 * The Class TemplateUtil.
 */
public class EntidadeExemplo {

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
		final LoggerContext ctx = PrivateManager.getContext();
        ctx.reconfigure();
        
        //List<EntidadeClasse> listaEntidade;
        
		String[][] classes = {
				{"AdmPagina", "Long"},
				{"AdmUsuario", "Long"}
		};

		/*
		List<String> listaClasse = new ArrayList<String>();
		for (String[] item : classes) {
			listaClasse.add(item[0]);
		}	
		
		listaEntidade = EntidadeUtil.lerClasse("br.com.hfsframework.admin.model.", listaClasse);
		
		for (EntidadeClasse entidade : listaEntidade) {
			//System.out.println(entidade.getPacote() +" - "+ entidade.getNome());			
			for (EntidadeCampo campo : entidade.getCampos()) {
				System.out.println("<field name=\"" + campo.getNome() +"\" class=\""+ campo.getTipo() + "\"/>");
			}
			System.out.println();
			System.out.println();
		}
		*/
		List<EntidadeCampo> campos = EntidadeUtil.lerCampos("br.com.hfsframework.admin.model.", classes[0][0]);
		for (EntidadeCampo campo : campos) {
			//System.out.println("<field name=\"" + campo.getNome() +"\" class=\""+ campo.getTipo() + "\"/>");
			System.out.println(campo.toString());
		}
		
	}


	/**
	 * The Class PrivateManager.
	 */
	private static class PrivateManager extends org.apache.logging.log4j.LogManager {
        
        /** The Constant FQCN. */
        private static final String FQCN = LogManager.class.getName();

        /**
		 * Gets the context.
		 *
		 * @return the context
		 */
        public static LoggerContext getContext() {
            return (LoggerContext) getContext(FQCN, false);
        }

        /**
		 * Gets the logger.
		 *
		 * @param name
		 *            the name
		 * @return the logger
		 */
        public static org.apache.logging.log4j.Logger getLogger(final String name) {
            return getLogger(FQCN, name);
        }
    }
	
	
}
