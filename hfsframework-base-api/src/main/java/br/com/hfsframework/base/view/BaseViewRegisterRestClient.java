package br.com.hfsframework.base.view;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	private Boolean forceDownload;
	
	public BaseViewRegisterRestClient(IBaseRestClient<T, I> restClient,
			String listPage, String editPage, String reportName) {
		super();
		this.forceDownload = false;
		
		log = LoggerFactory.getLogger(BaseViewRegisterRestClient.class);
		
		this.restClient = restClient;
		this.listPage = listPage;
		this.editPage = editPage;
		this.reportName = reportName;		
	}

	@GetMapping
	public ModelAndView list() {
		Optional<ModelAndView> mv = getPage(getListPage());
		return mv.get();
	}

	@GetMapping("/add")
	public ModelAndView add(T bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());
		return mv.get();
	}

	@GetMapping("/edit")
	public ModelAndView edit(T bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());
		return mv.get();
	}

	@PostMapping
	public ModelAndView save(@Valid T bean, 
			BindingResult result, RedirectAttributes attributes) {
		Optional<ModelAndView> mv = getPage(this.listPage);
		if (mv.isPresent()) {
			this.restClient.init(authServerURL, accesToken);
		}

		if (result.hasErrors()){
			mv.get().setViewName(this.editPage);
			return mv.get();
		}
		
		try {
			
			if (bean.getId()==null) 
				this.restClient.add(bean);
			else
				this.restClient.updateById(bean);
			
		} catch (RestClientException e) {
			this.showDangerMessage(mv.get(), e);
			return mv.get();
		}
		
		return mv.get();
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
		
		if (getPrincipal().isPresent()) {
			this.restClient.init(authServerURL, accesToken);
			
			ReportParamsDTO reportParamsDTO = new ReportParamsDTO();
			reportParamsDTO.setReportName(reportName.isEmpty() ? "report" : reportName);
			reportParamsDTO.setReportType(reportType);
			reportParamsDTO.setForceDownload(forceDownload);
			reportParamsDTO.setParams(params);
					
			Optional<byte[]> report = this.restClient.report(reportParamsDTO);
			
			if (report.isPresent()) {
				this.export(response, report, reportParamsDTO);
			}
		}
		
		return "";
	}
	
	@ModelAttribute("listReportType")
	public List<ReportGroupVO> getListReportType() {
		return super.getListReportType();
	}

	public String getListPage() {
		return listPage;
	}

	public String getEditPage() {
		return editPage;
	}

	public Boolean getForceDownload() {
		return forceDownload;
	}

	public void setForceDownload(Boolean forceDownload) {
		this.forceDownload = forceDownload;
	}
	
}
