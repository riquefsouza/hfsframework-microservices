/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JasperCompileManager;

/**
 * The Class CompilarRelatorios.
 */
public class CompilarRelatorios {

	private static Logger log = LogManager.getLogger(CompilarRelatorios.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		compilar("/ambiente/workspace");
	}

	/**
	 * Compilar.
	 *
	 * @param caminho the caminho
	 */
	private static void compilar(String caminho) {
		if (VisitarDiretorioUtil.getInstancia().ListarDiretorio(caminho,".jrxml")) {
			List<String> listaJRXML = VisitarDiretorioUtil.getInstancia().getListaDir();
			List<String> listaJasper = listaJRXML.stream()
					.map(item -> item.replaceAll(".jrxml", ".jasper"))
					.collect(Collectors.toList());

			for (String jasper : listaJasper) {
				Path relatorioJasper = Paths.get(jasper);
				if (!Files.exists(relatorioJasper)) {
					try {						
						String jrxml = jasper.replaceAll(".jasper", ".jrxml");
						JasperCompileManager.compileReportToFile(jrxml,	jasper);
						log.info("Relatório foi compilado: " + jasper);
					} catch (Throwable e) {
						log.error("Relatório deu erro ao compilar: " + jasper + ", erro: " + e.getMessage());
					}					
				} 
			}
		}
	}

}
