package br.com.hfsframework.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.VwAdmLogRestClient;
import br.com.hfsframework.admin.client.domain.VwAdmLog;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/vwAdmLogView")
public class VwAdmLogController extends BaseViewRegisterRestClient<VwAdmLog, Long, VwAdmLogRestClient> {

	private static final long serialVersionUID = 1L;

	public VwAdmLogController() {
		super(new VwAdmLogRestClient(), 
				"/private/vwAdmLog/listVwAdmLog", 
				"/private/vwAdmLog/editVwAdmLog", 
				"VwAdmLog");
	}
	
	@PostConstruct
	public void init() {
		//
	}		
}
