package br.com.hfsframework.oauth.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.hfsframework.base.report.ReportGroupVO;
import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.base.view.BaseViewController;

@Controller
@RequestMapping(value = "/private/roleView")
public class AutRoleController extends BaseViewController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AutRoleReportController report;

	private String listPage;

	private String editPage;

	//private RoleRestClient roleRestClient;
	
	@Value("${oauth2.hfsframework.server}")
	private String authServerURL;

	public AutRoleController() {
		this.listPage = "/private/autRole/listAutRole";
		this.editPage = "/private/autRole/editAutRole";
	}

	@GetMapping("/list")
	public ModelAndView list(HttpServletResponse response) {        
		ModelAndView mv = new ModelAndView(getListPage());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth!=null) {
			Object principal = auth.getPrincipal();
	
			if (principal instanceof BaseOAuth2RestUser) {
				BaseOAuth2RestUser user = (BaseOAuth2RestUser) principal;
				String sToken = user.getAccessToken().getValue();
				//mv.addObject("xauthtoken", sToken);
				//mv.addObject("authServerURL", authServerURL);
				
		        Cookie cookie = new Cookie("X-AUTH-TOKEN", sToken);
		        //1 hour = 60 seconds x 60 minutes
		        cookie.setMaxAge(60 * 60);
		        response.addCookie(cookie);
		        
		        Cookie cookie2 = new Cookie("AUTH-SERVER-URL", authServerURL);

			}
		}
		
		return mv;
	}
	

	@GetMapping("/add")
	public String add() {
		return getEditPage();
	}

	@GetMapping("/edit")
	public String edit() {
		return getEditPage();
	}

	@PostMapping("/save")
	public String salve() {
		return getListPage();
	}

	@GetMapping("/delete")
	public String delete() {
		return getListPage();
	}

	@ResponseBody
	@GetMapping(value = "/export/{reportType}/{forceDownload}")
	public String export(@PathVariable("reportType") String reportType,
			@PathVariable("forceDownload") String forceDownload) {
		return report.export(reportType, forceDownload);
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
