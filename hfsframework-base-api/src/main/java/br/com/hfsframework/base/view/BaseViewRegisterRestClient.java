package br.com.hfsframework.base.view;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;
import br.com.hfsframework.base.report.ReportGroupVO;
import br.com.hfsframework.base.view.report.BaseViewReportController;
import br.com.hfsframework.base.view.report.IBaseViewReport;
import br.com.hfsframework.base.view.report.ReportParamsDTO;

public abstract class BaseViewRegisterRestClient<T extends BaseEntityRestClient<I>, I extends Serializable,
	R extends BaseRestClient<T, I>> 
	extends BaseViewReportController implements IBaseViewReport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String listPage;

	private String editPage;
	
	private String reportName;
	
	private IBaseRestClient<T, I> restClient;
	
	private Class<T> classEntity;
	
	private Boolean forceDownload;
	
	public BaseViewRegisterRestClient(String listPage, String editPage, String reportName) {
		super();
		this.forceDownload = false;
		
		log = LoggerFactory.getLogger(BaseViewRegisterRestClient.class);
		
		this.listPage = listPage;
		this.editPage = editPage;
		this.reportName = reportName;		
	}

	@GetMapping("/list")
	public ModelAndView list(T restClient) {
		Optional<ModelAndView> mv = getPage(getListPage());
		
		if (mv.isPresent()) {
			this.restClient.init(authServerURL, accesToken, classEntity);
		}
		
		return mv.get();
	}

	@GetMapping("/add")
	public ModelAndView add() {
		Optional<ModelAndView> mv = getPage(getEditPage());
		if (mv.isPresent()) {
			this.restClient.init(authServerURL, accesToken, classEntity);
		}
		
		return mv.get();
	}

	@GetMapping("/edit")
	public ModelAndView edit() {
		Optional<ModelAndView> mv = getPage(getEditPage());
		if (mv.isPresent()) {
			this.restClient.init(authServerURL, accesToken, classEntity);
		}
		
		return mv.get();
	}

	@PostMapping("/save")
	public String save() {
		return getListPage();
	}

	@GetMapping("/delete")
	public String delete() {
		return getListPage();
	}

	@ResponseBody
	@GetMapping(value = "/export")	
	public String export(HttpServletResponse response,
			@RequestParam(name = "reportType", required = true, defaultValue = "PDF") String reportType,
			@RequestParam(name = "forceDownload", required = true, defaultValue = "false") String forceDownload,
			@RequestParam(name = "params", required = false) List<String> params) {
		
		ReportParamsDTO reportParamsDTO = new ReportParamsDTO();
		reportParamsDTO.setReportName(reportName.isEmpty() ? "report" : reportName);
		reportParamsDTO.setReportType(reportType);
		reportParamsDTO.setForceDownload(forceDownload);
		reportParamsDTO.setParams(params);
				
		Optional<byte[]> report = this.restClient.report(reportParamsDTO);
		
		if (report.isPresent()) {
			this.export(response, report, reportParamsDTO);
		}
		
		return "";
	}
	
	@ModelAttribute("listReportType")
	public List<ReportGroupVO> getListReportType() {
		return this.getListReportType();
	}

	public String getListPage() {
		return listPage;
	}

	public String getEditPage() {
		return editPage;
	}

	public String cancelEdition() {
		return getListPage();
	}

	public Boolean getForceDownload() {
		return forceDownload;
	}

	public void setForceDownload(Boolean forceDownload) {
		this.forceDownload = forceDownload;
	}
	
	public String cancel() {
		return getDesktopPage();
	}
	
}
