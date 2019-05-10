/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseRelatorioImpl.
 */
public class BaseRelatorioImpl implements IBaseRelatorio {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH = "relatorios/";
	
	/** The Constant DEFAULT_EXTENSION. */
	private static final String DEFAULT_EXTENSION = ".jasper";
	
	/** The log. */
	private Logger log;
	
	/** The jasper. */
	private JasperReport jasper;
	
	/** The print. */
	private JasperPrint print;
	
	/** The path. */
	private String path;
		
	/**
	 * Instantiates a new base relatorio impl.
	 *
	 * @param path
	 *            the path
	 */
	public BaseRelatorioImpl(String path) {
		this.log = LoggerFactory.getLogger(BaseRelatorioImpl.class);
		this.path = DEFAULT_PATH + path + DEFAULT_EXTENSION;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.relatorio.IBaseRelatorio#getSource()
	 */
	public Object getSource() {
		try {
			loadReport();
		} catch (Exception e) {
			throw new RelatorioException(log, RelatorioBundle.getString("excecao-carregar-relatorio", this.path), e);
		}

		return this.jasper;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.relatorio.IBaseRelatorio#getReportObject()
	 */
	public JasperPrint getReportObject() {
		return this.print;
	}

	/**
	 * Gets the class loader for resource.
	 *
	 * @param resource
	 *            the resource
	 * @return the class loader for resource
	 */
	private ClassLoader getClassLoaderForResource(final String resource) {
		final String stripped = resource.charAt(0) == '/' ? resource.substring(1) : resource;

		URL url = null;
		ClassLoader result = Thread.currentThread().getContextClassLoader();

		if (result != null) {
			url = result.getResource(stripped);
		}

		if (url == null) {
			result = BaseRelatorioImpl.class.getClassLoader();
			url = BaseRelatorioImpl.class.getClassLoader().getResource(stripped);
		}

		if (url == null) {
			result = null;
		}

		return result;
	}

	/**
	 * Gets the report stream.
	 *
	 * @param relativePath
	 *            the relative path
	 * @return the report stream
	 */
	private InputStream getReportStream(String relativePath) {
		InputStream reportStream;
		try {
			ClassLoader classLoader = getClassLoaderForResource(relativePath);
			reportStream = classLoader.getResourceAsStream(relativePath);
		} catch (Exception cause) {
			reportStream = null;
		}

		if (reportStream == null) {
			throw new RelatorioException(RelatorioBundle.getString("arquivo-nao-encontrado"));
		}

		return reportStream;
	}

	/**
	 * Load JRXML.
	 *
	 * @param relativePath
	 *            the relative path
	 */
	private void loadJRXML(String relativePath) {
		try {
			this.jasper = JasperCompileManager.compileReport(getReportStream(relativePath));
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RelatorioException(
					RelatorioBundle.getString("excecao-compilando-arquivo-jrxml", this.path));
		}
	}

	/**
	 * Load jasper.
	 *
	 * @param relativePath
	 *            the relative path
	 */
	private void loadJasper(String relativePath) {
		try {
			this.jasper = ((JasperReport) JRLoader.loadObject(getReportStream(relativePath)));
		} catch (JRException e) {
			throw new RelatorioException(log,
					RelatorioBundle.getString("excecao-carregando-arquivo-jasper", this.path), e);
		}
	}

	/**
	 * Load report.
	 */
	private final void loadReport() {
		if (this.path == null) {
			throw new RelatorioException(RelatorioBundle.getString("arquivo-nao-encontrado"));
		}

		if (this.jasper == null) {
			if (this.path.endsWith(".jasper")) {
				loadJasper(this.path);
			} else if (this.path.endsWith(".jrxml")) {
				this.log.info(RelatorioBundle.getString("recomenda-usar-jasper"));
				try {
					String jasperPath = this.path.replaceAll(".jrxml", ".jasper");

					loadJasper(jasperPath);
					this.log.info(RelatorioBundle.getString("encontrada-versao-compilada", jasperPath));
				} catch (Exception e) {
					this.log.error(RelatorioBundle.getString("nao-encontrada-versao-compilada"));
					loadJRXML(this.path);
				}
			} else {
				throw new RelatorioException(
						RelatorioBundle.getString("excecao-extensao-nao-valida", this.path ));
			}
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.relatorio.IBaseRelatorio#prepare(java.util.Collection, java.util.Map)
	 */
	public void prepare(Iterable<?> dataSource, Map<String, Object> param) {
		this.log.info(RelatorioBundle.getString("preenchendo-relatorio"));
		loadReport();
		try {			
			Collection<?> source = StreamSupport.stream(dataSource.spliterator(), false)
					.collect(Collectors.toList());			
			this.print = JasperFillManager.fillReport(this.jasper, param, new JRBeanCollectionDataSource(source));
		} catch (JRException e) {
			throw new RelatorioException(log, RelatorioBundle.getString("problema-preenchendo-relatorio"), e);
		}
	}
	
	/**
	 * Juntar alternado.
	 *
	 * @param jp1
	 *            the jp 1
	 * @param jp2
	 *            the jp 2
	 * @return the jasper print
	 */
	private JasperPrint juntarAlternado(JasperPrint jp1, JasperPrint jp2){
		JasperPrint jp = new JasperPrint();
		
		jp.setBookmarks(jp1.getBookmarks());
		jp.setBottomMargin(jp1.getBottomMargin());
		jp.setDefaultStyle(jp1.getDefaultStyle());
		jp.setFormatFactoryClass(jp1.getFormatFactoryClass());
		jp.setLeftMargin(jp1.getLeftMargin());
		jp.setLocaleCode(jp1.getLocaleCode());
		jp.setName(jp1.getName());
		jp.setOrientation(jp1.getOrientationValue());
		jp.setPageHeight(jp1.getPageHeight());
		jp.setPageWidth(jp1.getPageWidth());
		jp.setRightMargin(jp1.getRightMargin());
		jp.setTimeZoneId(jp1.getTimeZoneId());
		jp.setTopMargin(jp1.getTopMargin());
		
		for (int i = 0; i < jp1.getPages().size(); i++) {
			jp.addPage(jp1.getPages().get(i));
			jp.addPage(jp2.getPages().get(i));
		}
		
		return jp;
	}
	
	/**
	 * Checa se é filled.
	 *
	 * @return the boolean
	 */
	public Boolean isFilled() {
		if (this.print != null)
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.relatorio.IBaseRelatorio#export(br.com.hfsframework.base.relatorio.RelatorioTipoEnum)
	 */
	public byte[] export(RelatorioTipoEnum type) {
		if (!isFilled().booleanValue()) {
			throw new RelatorioException(RelatorioBundle.getString("excecao-relatorio-nao-preenchendo"));
		}

		return BaseRelatorioExporter.export(type, this.print).toByteArray();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.relatorio.IBaseRelatorio#export(java.util.Collection, java.util.Map, br.com.hfsframework.base.relatorio.RelatorioTipoEnum)
	 */
	public byte[] export(Iterable<?> dataSource, Map<String, Object> param, RelatorioTipoEnum type) {
		prepare(dataSource, param);
		return export(type);
	}
		
	/**
	 * Export junto alternado.
	 *
	 * @param jp1
	 *            the jp 1
	 * @param jp2
	 *            the jp 2
	 * @param type
	 *            the type
	 * @return the byte[]
	 */
	public byte[] exportJuntoAlternado(JasperPrint jp1, JasperPrint jp2, RelatorioTipoEnum type) {
		this.print = juntarAlternado(jp1, jp2);		
		return export(type);
	}

	/**
	 * Atribui o the path.
	 *
	 * @param path
	 *            o novo the path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.relatorio.IBaseRelatorio#getPath()
	 */
	public String getPath() {
		return this.path;
	}
}
