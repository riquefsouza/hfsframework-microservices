/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.view;

import java.io.Serializable;
import java.util.List;

import br.com.hfsframework.base.report.ReportGrupoVO;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBaseViewRelatorio.
 */
public interface IBaseViewReport extends Serializable {

	/**
	 * Gets the lista tipo relatorio.
	 *
	 * @return the lista tipo relatorio
	 */
	List<ReportGrupoVO> getListaTipoRelatorio();
	
	/**
	 * Exportar.
	 */
	String exportar(String tipoRelatorio, String forcarDownload);

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	String cancelar();
}
