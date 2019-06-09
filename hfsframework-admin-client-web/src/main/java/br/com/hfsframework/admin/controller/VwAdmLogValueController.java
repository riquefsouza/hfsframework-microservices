package br.com.hfsframework.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.VwAdmLogValueRestClient;
import br.com.hfsframework.admin.client.domain.VwAdmLogValue;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/vwAdmLogValueView")
public class VwAdmLogValueController extends BaseViewRegisterRestClient<VwAdmLogValue, Long, VwAdmLogValueRestClient> {

	private static final long serialVersionUID = 1L;

	public VwAdmLogValueController() {
		super(new VwAdmLogValueRestClient(), 
				"/private/vwAdmLogValue/listVwAdmLogValue", 
				"/private/vwAdmLogValue/editVwAdmLogValue", 
				"VwAdmLogValue");
	}

	@PostConstruct
	public void init() {
		//
	}	
}
