package br.com.hfsframework.base.view.report;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.hfsframework.base.report.ReportGroupVO;

public interface IBaseViewReport extends Serializable {

	List<ReportGroupVO> getListReportType();

	String export(HttpServletResponse response, String reportType, String forceDownload, List<String> params);

	String cancel();
}
