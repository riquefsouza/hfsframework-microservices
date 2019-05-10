/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// TODO: Auto-generated Javadoc
/**
 * The Class RelatorioRender.
 */
@Component
public class RelatorioRender implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	//@Autowired
	//private Logger log;
	private static final Logger log = LoggerFactory.getLogger(RelatorioRender.class);

	/* The context. */
	//@Inject
	//private FacesContext context;

	/**
	 * Render.
	 *
	 * @param conteudo
	 *            the conteudo
	 * @param tipoRelatorio
	 *            the tipo relatorio
	 * @param nomeArquivo
	 *            the nome arquivo
	 * @param forcarDownload
	 *            the forcar download
	 */
	public void render(final byte[] conteudo, final RelatorioTipoEnum tipoRelatorio, String nomeArquivo,
			boolean forcarDownload) {
		//HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpServletResponse response = attr.getResponse();		

		log.info("Renderizando para o arquivo " + nomeArquivo + ".");

		if (tipoRelatorio.equals(RelatorioTipoEnum.HTML)){
			nomeArquivo = nomeArquivo.replaceAll(".html", ".zip");
		}
		
		try {
			response.setContentType(tipoRelatorio.getTipoConteudo());
			response.setContentLength(conteudo.length);

			String forcarDownloadComando = forcarDownload ? "attachment; " : "";
			response.setHeader("Content-Disposition", forcarDownloadComando + "filename=\"" + nomeArquivo + "\"");

			log.info("Escrevendo o arquivo " + nomeArquivo + " no response.");
			response.getOutputStream().write(conteudo, 0, conteudo.length);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			log.warn("Erro na geração do relatório.", e);
			//context.addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
		}
		//context.responseComplete();
	}

	/**
	 * Render.
	 *
	 * @param conteudo
	 *            the conteudo
	 * @param tipoRelatorio
	 *            the tipo relatorio
	 * @param nomeArquivo
	 *            the nome arquivo
	 */
	public void render(final byte[] conteudo, final RelatorioTipoEnum tipoRelatorio, final String nomeArquivo) {
		render(conteudo, tipoRelatorio, nomeArquivo, false);
	}

	/**
	 * Render.
	 *
	 * @param stream
	 *            the stream
	 * @param tipoRelatorio
	 *            the tipo relatorio
	 * @param nomeArquivo
	 *            the nome arquivo
	 * @param forcarDownload
	 *            the forcar download
	 */
	public void render(final InputStream stream, final RelatorioTipoEnum tipoRelatorio, final String nomeArquivo,
			boolean forcarDownload) {
		log.info("Renderizando o arquivo " + nomeArquivo + ".");
		try {
			render(IOUtils.toByteArray(stream), tipoRelatorio, nomeArquivo, forcarDownload);
		} catch (IOException e) {
			log.warn("Erro na geração do relatório.", e);
			//context.addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
		}
	}

	/**
	 * Render.
	 *
	 * @param stream
	 *            the stream
	 * @param tipoRelatorio
	 *            the tipo relatorio
	 * @param nomeArquivo
	 *            the nome arquivo
	 */
	public void render(final InputStream stream, final RelatorioTipoEnum tipoRelatorio, final String nomeArquivo) {
		render(stream, tipoRelatorio, nomeArquivo, false);
	}

	/**
	 * Render.
	 *
	 * @param file
	 *            the file
	 * @param tipoRelatorio
	 *            the tipo relatorio
	 * @param nomeArquivo
	 *            the nome arquivo
	 * @param forcarDownload
	 *            the forcar download
	 */
	public void render(File file, RelatorioTipoEnum tipoRelatorio, String nomeArquivo, boolean forcarDownload) {
		log.info("Renderizando para o arquivo " + nomeArquivo + ".");
		try {
			render(new FileInputStream(file), tipoRelatorio, nomeArquivo, forcarDownload);
		} catch (FileNotFoundException e) {
			log.warn("Erro na geração do relatório.", e);
			//context.addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
		}
	}

	/**
	 * Render.
	 *
	 * @param file
	 *            the file
	 * @param tipoRelatorio
	 *            the tipo relatorio
	 * @param nomeArquivo
	 *            the nome arquivo
	 */
	public void render(File file, RelatorioTipoEnum tipoRelatorio, String nomeArquivo) {
		render(file, tipoRelatorio, nomeArquivo, false);
	}

}
