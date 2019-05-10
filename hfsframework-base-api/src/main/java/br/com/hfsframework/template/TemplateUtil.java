/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.hfsframework.util.entidade.EntidadeCampo;
import br.com.hfsframework.util.entidade.EntidadeUtil;
import br.com.hfsframework.util.metadados.Metadados;
import br.com.hfsframework.util.metadados.MetadadosColuna;
import br.com.hfsframework.util.metadados.MetadadosObjeto;
import br.com.hfsframework.util.metadados.MetadadosUtil;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class TemplateUtil.
 */
public class TemplateUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	//private static Logger log = LogManager.getLogger(TemplateUtil.class);

	/**
	 * Gerar codigo fonte.
	 *
	 * @param params
	 *            the params
	 * @param caminhoModelo
	 *            the caminho modelo
	 * @param pacote
	 *            the pacote
	 * @param classes
	 *            the classes
	 * @param diretorioSaida
	 *            the diretorio saida
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws TemplateException
	 *             the template exception
	 */
	public static void gerarCodigoFonte(TemplateEnum params, String caminhoModelo, String pacote, String[][] classes, String diretorioSaida)
			throws IOException, FileNotFoundException, TemplateException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		File arquivo, dir;
		String modelo = params.name() + ".txt";
		String saida, texto, extensao = ".java";
		List<EntidadeCampo> listaCampos;

		Template templateModelo = configurar(caminhoModelo, modelo);

		if (params.name().equals("listar") || params.name().equals("editar") || params.name().equals("reportar")) {
			extensao = ".html";
		}
		if (params.name().equals("paisagem") || params.name().equals("retrato")) {
			extensao = ".jrxml";
		}

		if (params.name().equals("listar") || params.name().equals("editar") || params.name().equals("reportar")) {
			for (int i = 0; i < classes.length; i++) {
				dir = new File(diretorioSaida + "/" + StringUtils.uncapitalize(classes[i][0]));
				if (!dir.exists()) {
					FileUtils.forceMkdir(dir);
				}
			}
		} else {
			for (int i = 0; i < classes.length; i++) {
				if (params.getDiretorio().equals("view"))
					dir = new File(diretorioSaida + "/" + StringUtils.uncapitalize(params.getDiretorio()) + "/"
							+ StringUtils.uncapitalize(classes[i][0]));
				else
					dir = new File(diretorioSaida + "/" + StringUtils.uncapitalize(params.getDiretorio()));

				if (!dir.exists()) {
					FileUtils.forceMkdir(dir);
				}
			}
		}

		for (int i = 0; i < classes.length; i++) {
			if (params.name().equals("listar") || params.name().equals("editar") || params.name().equals("reportar")) {
				saida = diretorioSaida + "/" + StringUtils.uncapitalize(classes[i][0]) + "/" + params.getTipo()
						+ classes[i][0] + extensao;
			} else {
				if (params.getDiretorio().equals("view"))
					saida = diretorioSaida + "/" + StringUtils.uncapitalize(params.getDiretorio()) + "/"
							+ StringUtils.uncapitalize(classes[i][0]) + "/" + classes[i][0] + params.getTipo()
							+ extensao;
				else
					saida = diretorioSaida + "/" + StringUtils.uncapitalize(params.getDiretorio()) + "/" + classes[i][0]
							+ params.getTipo() + extensao;
			}

			parametros.put("pacote", pacote);
			parametros.put("classe", classes[i][0]);
			parametros.put("objeto", StringUtils.uncapitalize(classes[i][0]));
			parametros.put("idTipo", classes[i][1]);

			if (params.name().equals("paisagem") || params.name().equals("retrato")) {
				listaCampos = EntidadeUtil.lerCampos(pacote + ".model.", classes[i][0]);
				parametros.put("campos", listaCampos);
			}

			arquivo = processar(templateModelo, parametros, saida);

			if (params.name().equals("listar") || params.name().equals("editar") || params.name().equals("reportar")
					|| params.name().equals("paisagem") || params.name().equals("retrato")) {

				texto = FileUtils.readFileToString(arquivo, StandardCharsets.UTF_8);
				texto = texto.replace("\\{", "{");
				texto = texto.replace("\\}", "}");
				FileUtils.writeStringToFile(arquivo, texto, StandardCharsets.UTF_8);
			}

		}

	}

	/**
	 * Configurar.
	 *
	 * @param caminho
	 *            the caminho
	 * @param modelo
	 *            the modelo
	 * @return the template
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TemplateNotFoundException
	 *             the template not found exception
	 * @throws MalformedTemplateNameException
	 *             the malformed template name exception
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Template configurar(String caminho, String modelo)
			throws IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException {
		if (caminho.isEmpty()){
			String sPath = Paths.get(".").toAbsolutePath().normalize().toString();
			caminho = sPath + "/src/main/java/" + TemplateUtil.class.getPackage().getName().replaceAll("[.]", "/");
		}
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(caminho));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		Template templateModelo = cfg.getTemplate(modelo);
		return templateModelo;
	}

	/**
	 * Processar.
	 *
	 * @param templateModelo
	 *            the template modelo
	 * @param parametros
	 *            the parametros
	 * @param saida
	 *            the saida
	 * @return the file
	 * @throws TemplateException
	 *             the template exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File processar(Template templateModelo, Map<String, Object> parametros, String saida)
			throws TemplateException, IOException {
		File arquivo = new File(saida);
		FileOutputStream fos = new FileOutputStream(arquivo);
		Writer out = new OutputStreamWriter(fos);

		templateModelo.process(parametros, out);
		out.flush();
		fos.flush();

		out.close();
		fos.close();

		return arquivo;
	}

	/**
	 * Gerar pages.
	 *
	 * @param diretorio
	 *            the diretorio
	 * @param classes
	 *            the classes
	 * @return the string
	 */
	public static String gerarPages(String diretorio, String[][] classes) {
		String txt = "";
		for (int i = 0; i < classes.length; i++) {
			txt += "@View(basePath = \"" + diretorio + StringUtils.uncapitalize(classes[i][0]) + "/\")\n";
			txt += "class Listar" + classes[i][0] + " implements ViewConfig {\n";
			txt += "}\n";
			txt += "\n";
			txt += "@View(basePath = \"" + diretorio + StringUtils.uncapitalize(classes[i][0]) + "/\")\n";
			txt += "class Editar" + classes[i][0] + " implements ViewConfig {\n";
			txt += "}\n";
			txt += "\n";
		}
		return txt;
	}

	/**
	 * Gerar menu.
	 *
	 * @param diretorio
	 *            the diretorio
	 * @param classes
	 *            the classes
	 * @return the string
	 */
	public static String gerarMenu(String diretorio, String[][] classes) {
		String txt = "";
		for (int i = 0; i < classes.length; i++) {
			txt += "	          <li><a href=\"#{request.contextPath}" + diretorio
					+ StringUtils.uncapitalize(classes[i][0]) + "/listar" + classes[i][0] + ".xhtml\">" + classes[i][0]
					+ "</a></li>\n";
		}
		return txt;
	}

	/**
	 * Gerar adm log.
	 *
	 * @param mu
	 *            the mu
	 * @param esquema
	 *            the esquema
	 * @param caminhoModelo
	 *            the caminho modelo
	 * @param modelo
	 *            the modelo
	 * @param opcao
	 *            the opcao
	 * @param arquivoSaida
	 *            the arquivo saida
	 * @return the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TemplateNotFoundException
	 *             the template not found exception
	 * @throws MalformedTemplateNameException
	 *             the malformed template name exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws TemplateException
	 *             the template exception
	 */
	public static File gerarAdmLog(MetadadosUtil mu, String esquema, String caminhoModelo, String modelo, int opcao, String arquivoSaida) throws IOException,
			TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<LogAdmVO> listaLogAdmVO = new ArrayList<LogAdmVO>();
		LogAdmVO logAdmVO;
		List<LogAdmValorVO> listaLogAdmValorVO = new ArrayList<LogAdmValorVO>();
		LogAdmValorVO logAdmValorVO;
		parametros.put("opcao", opcao);

		Metadados md = mu.getMetadados(esquema);
		for (MetadadosObjeto t : md.getObjetos()) {
			parametros.put("esquema", t.getEsquema().toLowerCase());
			
			if (!t.getObjeto().equalsIgnoreCase("ADM_USUARIO") 
					&& !t.getObjeto().equalsIgnoreCase("ADM_AUDITORIA_REVISAO")
					&& t.getObjeto().toUpperCase().indexOf("LOG_") != 0
					&& t.getObjeto().toUpperCase().indexOf("VW_") != 0
					&& !(t.getObjeto().toUpperCase().indexOf("_AUD") > 0)){
				
				logAdmVO = new LogAdmVO();
				logAdmVO.setEsquema(t.getEsquema().toLowerCase());
				logAdmVO.setTabela(t.getObjeto().toLowerCase());
				logAdmVO.setTabelaAbreviada(t.getObjeto().substring(4, t.getObjeto().length() > 21 ? 18: t.getObjeto().length()).toLowerCase());
				logAdmVO.setEntidade(
						StringUtils.capitalize(t.getObjeto().substring(4, t.getObjeto().length()).replace('_', ' ')));
				logAdmVO.setChave(t.getNomeColunasPK().size() == 1 ? "'" + t.getNomeColunasPK().get(0) + "'" : "NULL".toLowerCase());
				logAdmVO.setCampos(t.getColunas().toString().substring(1, t.getColunas().toString().length() - 1).toLowerCase());
				logAdmVO.setColunas(t.getNomeColunas().toString().substring(1, t.getNomeColunas().toString().length() - 1).toLowerCase());
				logAdmVO.setxColunas(
						t.getNomeColunas().toString().replaceAll(", ", ",x").replace('[', 'x').replace(']', ' ').trim().toLowerCase());
				if (modelo.indexOf("Oracle") > 0) {
					logAdmVO.setNewColunas(t.getNomeColunas().toString().replaceAll(", ", ",:new.").replace('[','@').replaceAll("@", ":new.")
							.replace(']', ' ').trim().toLowerCase());
					logAdmVO.setOldColunas(t.getNomeColunas().toString().replaceAll(", ", ",:old.").replace('[','@').replaceAll("@", ":old.")
							.replace(']', ' ').trim().toLowerCase());
				} else if (modelo.indexOf("PostgreSQL") > 0) {
					logAdmVO.setNewColunas(t.getNomeColunas().toString().replaceAll(", ", ",new.").replace('[','@').replaceAll("@", "new.")
							.replace(']', ' ').trim().toLowerCase());
					logAdmVO.setOldColunas(t.getNomeColunas().toString().replaceAll(", ", ",old.").replace('[','@').replaceAll("@", "old.")
							.replace(']', ' ').trim().toLowerCase());
				}

				listaLogAdmVO.add(logAdmVO);

				for (MetadadosColuna mc : t.getColunas()) {
					logAdmValorVO = new LogAdmValorVO();
					logAdmValorVO.setEntidade(logAdmVO.getEntidade());
					logAdmValorVO.setTabela(logAdmVO.getTabela());
					logAdmValorVO.setTabelaAbreviada(logAdmVO.getTabelaAbreviada());
					logAdmValorVO.setChave(logAdmVO.getChave());
					logAdmValorVO
							.setColuna(mc.getNome().substring(4, mc.getNome().length()).replace('_', ' ').toLowerCase());
					logAdmValorVO.setCampo(mc.getNome().toLowerCase());
					listaLogAdmValorVO.add(logAdmValorVO);
				}
			}

		}

		parametros.put("listaLogAdmVO", listaLogAdmVO);
		parametros.put("listaLogAdmValorVO", listaLogAdmValorVO);

		Template templateModelo = TemplateUtil.configurar(caminhoModelo, modelo);
		File arquivo = TemplateUtil.processar(templateModelo, parametros, arquivoSaida);
		return arquivo;
	}

	/**
	 * Gerar C sharp.
	 *
	 * @param mu the mu
	 * @param esquema the esquema
	 * @param diretorioSaida the diretorio saida
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateNotFoundException the template not found exception
	 * @throws MalformedTemplateNameException the malformed template name exception
	 * @throws ParseException the parse exception
	 * @throws TemplateException the template exception
	 */
	public static void gerarCSharp(MetadadosUtil mu, String esquema, String diretorioSaida) throws IOException,
			TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		String classe;
						
		Metadados md = mu.getMetadados(esquema);
		for (MetadadosObjeto t : md.getObjetos()) {
			if (t.getObjeto().toUpperCase().indexOf("LOG_") != 0 && t.getObjeto().toUpperCase().indexOf("VW_") != 0){
				
				String prop = "";
				String[] nomes = t.getObjeto().substring(4).split("_");
				for (String item : nomes) {
					prop += StringUtils.capitalize(item);
				}
				
				classe = "Adm" + prop;
				parametros.put("classe", classe);
				parametros.put("tabela", t.getObjeto().toLowerCase());
				parametros.put("listaColunasPK", t.getNomeColunasPK());
				parametros.put("listaColunas", t.getColunas());
	
				Template templateModelo = TemplateUtil.configurar("", "csharp_hbm_xml.txt");
				TemplateUtil.processar(templateModelo, parametros, diretorioSaida + "/"+classe+".hbm.xml");

				templateModelo = TemplateUtil.configurar("", "csharp_model.txt");
				TemplateUtil.processar(templateModelo, parametros, diretorioSaida + "/"+classe+".cs");

				templateModelo = TemplateUtil.configurar("", "csharp_repository.txt");
				TemplateUtil.processar(templateModelo, parametros, diretorioSaida + "/"+classe+"Repository.cs");
				
				templateModelo = TemplateUtil.configurar("", "csharp_bc.txt");
				TemplateUtil.processar(templateModelo, parametros, diretorioSaida + "/"+classe+"BC.cs");				
			}
		}
		
	}
	
}
