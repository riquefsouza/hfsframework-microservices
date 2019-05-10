/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import br.com.hfsframework.util.metadados.MetadadosConfig;
import br.com.hfsframework.util.metadados.MetadadosUtil;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class LogAdmExemplo.
 */
public class LogAdmExemplo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 */
	public static void main(String[] args) throws IOException, TemplateException {
		final LoggerContext ctx = PrivateManager.getContext();
		ctx.reconfigure();

		gerarLogAdmOracle();
		gerarLogAdmPostgreSQL();
	}

	/**
	 * Gerar log adm oracle.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateNotFoundException the template not found exception
	 * @throws MalformedTemplateNameException the malformed template name exception
	 * @throws ParseException the parse exception
	 * @throws TemplateException the template exception
	 */
	private static void gerarLogAdmOracle() throws IOException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, TemplateException {
		MetadadosUtil mu = new MetadadosUtil();
		mu.configurar(MetadadosConfig.hfsbancoDS());
		String modelo = "logOracle.txt";

		/*
		 * Oracle 1 create table 2 sequence 3 trigger 4 enable trigger 5 view
		 * vw_adm_log 6 VIEW VW_ADM_LOG_VALOR 7 drop
		 */

		/*
		 * PostgreSQL 1 table 2 sequence 3 function 4 trigger 5 enable trigger 6
		 * view vw_adm_log 7 VIEW VW_ADM_LOG_VALOR 8 drop
		 */

		File arquivo;
		String texto = "";
		for (int i = 1; i <= 7; i++) {
			arquivo = TemplateUtil.gerarAdmLog(mu, "hfsbanco", "", modelo, i, "c:/temp/blob/logOracle" + i + ".sql");
			texto += FileUtils.readFileToString(arquivo, StandardCharsets.UTF_8);
			// arquivo.delete();
			texto += "\n/*--------------------------------------------------------------------------------------------------------------------*/\n\n";
		}
		FileUtils.writeStringToFile(new File("c:/temp/blob/SQL_LOG_ORACLE.sql"), texto, StandardCharsets.UTF_8);
	}

	/**
	 * Gerar log adm postgre SQL.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateNotFoundException the template not found exception
	 * @throws MalformedTemplateNameException the malformed template name exception
	 * @throws ParseException the parse exception
	 * @throws TemplateException the template exception
	 */
	private static void gerarLogAdmPostgreSQL() throws IOException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, TemplateException {
		MetadadosUtil mu = new MetadadosUtil();
		mu.configurar(MetadadosConfig.hfsbancoDS());
		String modelo = "logPostgreSQL.txt";

		/*
		 * Oracle 1 create table 2 sequence 3 trigger 4 enable trigger 5 view
		 * vw_adm_log 6 VIEW VW_ADM_LOG_VALOR 7 drop
		 */

		/*
		 * PostgreSQL 1 table 2 sequence 3 function 4 trigger 5 enable trigger 6
		 * view vw_adm_log 7 VIEW VW_ADM_LOG_VALOR 8 drop
		 */

		File arquivo;
		String texto = "";
		for (int i = 1; i <= 8; i++) {
			arquivo = TemplateUtil.gerarAdmLog(mu, "public", "", modelo, i, "c:/temp/blob/logPostgreSQL" + i + ".sql");
			texto += FileUtils.readFileToString(arquivo, StandardCharsets.UTF_8);
			// arquivo.delete();
			texto += "\n/*--------------------------------------------------------------------------------------------------------------------*/\n\n";
		}
		FileUtils.writeStringToFile(new File("c:/temp/blob/SQL_LOG_POSTGRESQL.sql"), texto, StandardCharsets.UTF_8);
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
		 * @param name the name
		 * @return the logger
		 */
		public static org.apache.logging.log4j.Logger getLogger(final String name) {
			return getLogger(FQCN, name);
		}
	}

}
