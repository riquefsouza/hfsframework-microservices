package br.com.hfsframework.oauth.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.hfsframework.base.report.ReportGroupVO;
import br.com.hfsframework.base.report.ReportTypeEnum;
import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.base.view.report.ReportParamsDTO;
import br.com.hfsframework.oauth.client.RoleRestClient;

@Controller
@RequestMapping(value = "/private/roleView")
public class AutRoleController extends BaseViewController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AutRoleReportController report;

	private String listPage;

	private String editPage;

	private String authServerURL;
	
	private String accesToken;
	
	private RoleRestClient roleRestClient;

	public AutRoleController() {
		log = LoggerFactory.getLogger(AutRoleController.class);
		
		this.listPage = "/private/autRole/listAutRole";
		this.editPage = "/private/autRole/editAutRole";		
	}

	@GetMapping("/list")
	public ModelAndView list() {        
		return getPage(getListPage());
	}

	private ModelAndView getPage(String pagina) {
		ModelAndView mv = new ModelAndView(pagina);

		if (getPrincipal().isPresent()) {
			this.authServerURL = this.getPrincipal().get().getUrlAuthorizationServer();
			this.accesToken = this.getPrincipal().get().getAccessToken().getValue();
			this.roleRestClient = new RoleRestClient(authServerURL, accesToken);
		}		
		
		mv.addObject("urlAuthServer", authServerURL);
		mv.addObject("authToken", accesToken);
		return mv;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		return getPage(getEditPage());
	}

	@GetMapping("/edit")
	public ModelAndView edit() {
		return getPage(getEditPage());
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
	public String exportar(HttpServletResponse response,
			@RequestParam(name = "reportType", required = true, defaultValue = "PDF") String reportType,
			@RequestParam(name = "forceDownload", required = true, defaultValue = "false") String forceDownload,
			@RequestParam(name = "params", required = false) List<String> params) {
		ReportParamsDTO reportParamsDTO = new ReportParamsDTO();
		reportParamsDTO.setReportName("AutRole");
		reportParamsDTO.setReportType(reportType);
		reportParamsDTO.setForceDownload(forceDownload);
		reportParamsDTO.setParams(params);
				
		Optional<byte[]> report = roleRestClient.report(reportParamsDTO);
		
		if (report.isPresent()) {
			this.render(response, report.get(), reportParamsDTO);
		}
		
		return "";
	}
	
	public void render(HttpServletResponse response, final byte[] conteudo, ReportParamsDTO reportParamsDTO) {
		ReportTypeEnum reportType = ReportTypeEnum.valueOf(reportParamsDTO.getReportType());
		String nameReport = reportParamsDTO.getReportName() + "." + reportType.name().toLowerCase();
		boolean forceDownload = Boolean.parseBoolean(reportParamsDTO.getForceDownload());
		
		log.info("Renderizando para o arquivo " + nameReport + ".");

		if (reportType.equals(ReportTypeEnum.HTML)){
			nameReport = nameReport.replaceAll(".html", ".zip");
		}
		
		try {
			response.setContentType(reportType.getContentType());
			response.setContentLength(conteudo.length);

			String forcarDownloadComando = forceDownload ? "attachment; " : "";
			response.setHeader("Content-Disposition", forcarDownloadComando + "filename=\"" + nameReport + "\"");

			log.info("Escrevendo o arquivo " + nameReport + " no response.");
			response.getOutputStream().write(conteudo, 0, conteudo.length);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			log.warn("Erro na geração do relatório.", e);
		}
	}	
	
	@ModelAttribute("listReportType")
	public List<ReportGroupVO> getListReportType() {
		return report.getListReportType();
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

	public String cancel() {
		return getDesktopPage();
	}

}
