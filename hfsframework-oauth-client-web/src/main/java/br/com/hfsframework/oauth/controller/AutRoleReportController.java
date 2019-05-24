package br.com.hfsframework.oauth.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import br.com.hfsframework.base.report.ReportGroupVO;
import br.com.hfsframework.base.view.report.BaseViewReportController;
import br.com.hfsframework.base.view.report.IBaseViewReport;

@Controller
public class AutRoleReportController extends BaseViewReportController implements IBaseViewReport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Boolean forceDownload;
	
	//private IBaseReport report;

	public AutRoleReportController() {
		//super(new Role());
		this.forceDownload = false;
	}
	
	@Override
	public String export(String reportType, String forceDownload) {
		/*
		this.setTipoRelatorio(reportType);
		this.setForceDownload(Boolean.parseBoolean(forceDownload));
		
		Map<String, Object> params = getParametros();
		params.put("PARAMETRO1", "");

		relatorio = new BaseRelatorioImpl("AdmParametroCategoria");
		
		super.exportar(report, getBusinessController().findAll(), params, this.forceDownload);
		*/
		return "";
	}

	@Override
	public List<ReportGroupVO> getListReportType() {
		return super.getListReportType();
	}
	
	public Boolean getForceDownload() {
		return forceDownload;
	}

	public void setForceDownload(Boolean forceDownload) {
		this.forceDownload = forceDownload;
	}
	
	@Override
	public String cancel() {
		return null;
	}

}
