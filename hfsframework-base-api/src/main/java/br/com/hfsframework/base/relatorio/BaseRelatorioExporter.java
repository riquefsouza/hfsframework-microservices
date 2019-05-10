/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import br.com.hfsframework.util.VisitarDiretorioUtil;
import br.com.hfsframework.util.zip.ZipException;
import br.com.hfsframework.util.zip.ZipUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseRelatorioExporter.
 */
public class BaseRelatorioExporter { 

	/** The log. */
	private static Logger log;

	/**
	 * Instantiates a new base relatorio exporter.
	 */
	public BaseRelatorioExporter() {		
	}

	/**
	 * Export.
	 *
	 * @param type
	 *            the type
	 * @param print
	 *            the print
	 * @return the byte array output stream
	 */
	public static synchronized ByteArrayOutputStream export(RelatorioTipoEnum type, JasperPrint print) {
		if (log == null) {
			log = LoggerFactory.getLogger(BaseRelatorioExporter.class);
		}

		log.info(RelatorioBundle.getString("gerando-relatorio", type.name()));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			switch (type) {
			case CSV:
				log.info(RelatorioBundle.getString("exportando-relatorio", "csv"));
				JRCsvExporter exporterCSV = new JRCsvExporter();
				exporterCSV.setExporterInput(new SimpleExporterInput(print));
				exporterCSV.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
				exporterCSV.exportReport();
				break;
			case HTML:
				//File folder = new ClassPathResource("WEB-INF").getFile();
				//File[] listOfFiles = folder.listFiles();
				//FacesContext context = FacesContext.getCurrentInstance();
				//ExternalContext ec = context.getExternalContext();
				//String sDiretorio = ec.getRealPath("WEB-INF");
				//String sDiretorio = System.getProperty("user.dir");
				
				String sDiretorio = new ClassPathResource("WEB-INF").getPath();
				
				String sArquivo = RandomStringUtils.randomAlphanumeric(32).toUpperCase() + ".html";
				File arquivo = new File(sDiretorio, sArquivo);
				log.info(RelatorioBundle.getString("exportando-relatorio", "html"));
				JasperExportManager.exportReportToHtmlFile(print, arquivo.getPath());
				byte[] conteudo = FileUtils.readFileToByteArray(arquivo);
				conteudo = compactarHTML(type, sDiretorio, sArquivo, conteudo);				
				outputStream.write(conteudo, 0, conteudo.length);
				outputStream.flush();
				arquivo.delete();
				break;
			case ODT:
				log.info(RelatorioBundle.getString("exportando-relatorio", "odt"));
				JROdtExporter exporterODT = new JROdtExporter();
				exporterODT.setExporterInput(new SimpleExporterInput(print));
				exporterODT.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				exporterODT.exportReport();
				break;
			case PDF:
				log.info(RelatorioBundle.getString("exportando-relatorio", "pdf"));
				JRPdfExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setExporterInput(new SimpleExporterInput(print));
				exporterPDF.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				SimplePdfReportConfiguration pdfReportConfiguration = new SimplePdfReportConfiguration();
				pdfReportConfiguration.setIgnoreHyperlink(true);
				exporterPDF.setConfiguration(pdfReportConfiguration);
				exporterPDF.exportReport();
				break;
			case TXT:
				log.info(RelatorioBundle.getString("exportando-relatorio", "txt"));
				JRTextExporter exporterTXT = new JRTextExporter();
				exporterTXT.setExporterInput(new SimpleExporterInput(print));
				exporterTXT.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
				SimpleTextReportConfiguration txtReportConfiguration = new SimpleTextReportConfiguration();
				txtReportConfiguration.setCharWidth(7.238F);
				txtReportConfiguration.setCharHeight(13.948F);
				txtReportConfiguration.setPageWidthInChars(80);
				txtReportConfiguration.setPageHeightInChars(40);
				exporterTXT.setConfiguration(txtReportConfiguration);
				exporterTXT.exportReport();
				break;
			case RTF:
				log.info(RelatorioBundle.getString("exportando-relatorio", "rtf"));
				JRRtfExporter exporterRTF = new JRRtfExporter();
				exporterRTF.setExporterInput(new SimpleExporterInput(print));
				exporterRTF.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
				exporterRTF.exportReport();
				break;
			/*	
			case XLS:
				log.info(RelatorioBundle.getString("exportando-relatorio", "xls"));
				JRXlsExporter exporterXLS = new JRXlsExporter();
				exporterXLS.setExporterInput(new SimpleExporterInput(print));
				exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
				xlsReportConfiguration.setOnePagePerSheet(false);
				xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
				xlsReportConfiguration.setDetectCellType(true);
				xlsReportConfiguration.setWhitePageBackground(false);
				exporterXLS.setConfiguration(xlsReportConfiguration);
				exporterXLS.exportReport();
				break;
			*/	
			case ODS:
				log.info(RelatorioBundle.getString("exportando-relatorio", "ods"));
				JROdsExporter exporterODS = new JROdsExporter();
				exporterODS.setExporterInput(new SimpleExporterInput(print));
				exporterODS.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				exporterODS.exportReport();
				break;

			case DOCX:
				log.info(RelatorioBundle.getString("exportando-relatorio", "docx"));
				JRDocxExporter exporterDOCX = new JRDocxExporter();
				exporterDOCX.setExporterInput(new SimpleExporterInput(print));
				exporterDOCX.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				exporterDOCX.exportReport();
				break;
			case PPTX:
				log.info(RelatorioBundle.getString("exportando-relatorio", "pptx"));
				JRPptxExporter exporterPPTX = new JRPptxExporter();
				exporterPPTX.setExporterInput(new SimpleExporterInput(print));
				exporterPPTX.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				exporterPPTX.exportReport();
				break;
			case XLSX:
				log.info(RelatorioBundle.getString("exportando-relatorio", "xlsx"));
				JRXlsxExporter exporterXLSX = new JRXlsxExporter();
				exporterXLSX.setExporterInput(new SimpleExporterInput(print));
				exporterXLSX.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
				xlsxReportConfiguration.setOnePagePerSheet(false);
				xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
				xlsxReportConfiguration.setDetectCellType(true);
				xlsxReportConfiguration.setWhitePageBackground(false);
				exporterXLSX.setConfiguration(xlsxReportConfiguration);
				exporterXLSX.exportReport();
				break;
			default:
				throw new RelatorioException(RelatorioBundle.getString("excecao-tipo-relatorio-nao-suportado", type.name()));
			}
		} catch (JRException jre) {
			throw new RelatorioException(log, RelatorioBundle.getString("excecao-gerando-relatorio", type.name()),
					jre);
		} catch (IOException e) {
			throw new RelatorioException(log, RelatorioBundle.getString("excecao-gerando-relatorio", type.name()), e);
		}
		return outputStream;
	}
	
	/**
	 * Compactar HTML.
	 *
	 * @param type
	 *            the type
	 * @param sDiretorio
	 *            the s diretorio
	 * @param sArquivo
	 *            the s arquivo
	 * @param conteudo
	 *            the conteudo
	 * @return the byte[]
	 */
	private static byte[] compactarHTML(RelatorioTipoEnum type, String sDiretorio, String sArquivo, byte[] conteudo) {
		ZipUtil zipUtil = new ZipUtil();
		String arquivoZIP = sDiretorio + "/" + sArquivo + ".zip";
		String dirResto = sDiretorio + "/" + sArquivo + "_files";
		File arquivo;
		byte[] conteudoZIP;

		try {
			if (VisitarDiretorioUtil.getInstancia().ListarDiretorio(dirResto)) {
				List<File> lista = VisitarDiretorioUtil.getInstancia().getListaFile();
				List<String> arquivos = new ArrayList<String>();
				List<String> dirs = new ArrayList<String>();

				dirs.add("");
				arquivos.add(sArquivo);

				for (int j = 0; j < lista.size(); j++) {
					dirs.add(lista.get(j).getParentFile().getName());
					arquivos.add(lista.get(j).getName());
				}
				zipUtil.criarZIP(arquivoZIP, sDiretorio, dirs, arquivos);

				for (File file : lista) {
					file.delete();
				}
				File subdir = new File(dirResto);
				subdir.delete();
			}
			arquivo = new File(arquivoZIP);
			conteudoZIP = FileUtils.readFileToByteArray(arquivo);
			// res.setContentType("application/zip");
			arquivo.delete();
		} catch (ZipException e) {
			throw new RelatorioException(log, RelatorioBundle.getString("excecao-gerando-relatorio", type.name()), e);
		} catch (IOException e) {
			throw new RelatorioException(log, RelatorioBundle.getString("excecao-gerando-relatorio", type.name()), e);
		}

		return conteudoZIP;
	}
}
