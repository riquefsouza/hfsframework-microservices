package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.AdmUser;
import br.com.hfsframework.admin.service.IAdmUserService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/admUser")
public class AdmUserRestController
		extends BaseRestController<AdmUser, Long, IAdmUserService> {

	private static final long serialVersionUID = 1L;

	public AdmUserRestController() {
		super(true);
	}
}
