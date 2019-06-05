package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.AdmPage;
import br.com.hfsframework.admin.service.IAdmPageService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/admPage")
public class AdmPageRestController
		extends BaseRestController<AdmPage, Long, IAdmPageService> {

	private static final long serialVersionUID = 1L;

}
