/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.report;

import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBaseRelatorio.
 */
public abstract interface IBaseReport extends Serializable {

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public abstract Object getSource();

	/**
	 * Gets the report object.
	 *
	 * @return the report object
	 */
	public abstract JasperPrint getReportObject();

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public abstract String getPath();

	/**
	 * Prepare.
	 *
	 * @param paramCollection
	 *            the param collection
	 * @param paramMap
	 *            the param map
	 */
	public abstract void prepare(Iterable<?> paramCollection, Map<String, Object> paramMap);

	/**
	 * Export.
	 *
	 * @param paramCollection
	 *            the param collection
	 * @param paramMap
	 *            the param map
	 * @param paramType
	 *            the param type
	 * @return the byte[]
	 */
	public abstract byte[] export(Iterable<?> paramCollection, Map<String, Object> paramMap,
			ReportTipoEnum paramType);

	/**
	 * Export.
	 *
	 * @param paramType
	 *            the param type
	 * @return the byte[]
	 */
	public abstract byte[] export(ReportTipoEnum paramType);

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
	public abstract byte[] exportJuntoAlternado(JasperPrint jp1, JasperPrint jp2, ReportTipoEnum type);
}