package br.com.hfsframework.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewRegisterRestClient;
import br.com.hfsframework.admin.client.AdmParameterCategoryRestClient;
import br.com.hfsframework.admin.client.domain.AdmParameterCategory;

@Controller
@RequestMapping(value = "/private/admParameterCategoryView")
public class AdmParameterCategoryController extends BaseViewRegisterRestClient<AdmParameterCategory, Long, AdmParameterCategoryRestClient> {

	private static final long serialVersionUID = 1L;

	public AdmParameterCategoryController() {
		super(new AdmParameterCategoryRestClient(), "/private/admParameterCategory/listAdmParameterCategory", "/private/admParameterCategory/editAdmParameterCategory", "AdmParameterCategory");
	}
	
}