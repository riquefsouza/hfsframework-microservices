package br.com.hfsframework.base.view.report;

import java.io.Serializable;
import java.util.List;

import br.com.hfsframework.base.report.ReportGroupVO;

public interface IBaseViewReport extends Serializable {

	List<ReportGroupVO> getListReportType();
	
	String export(String reportType, String forceDownload);

	String cancel();
}
