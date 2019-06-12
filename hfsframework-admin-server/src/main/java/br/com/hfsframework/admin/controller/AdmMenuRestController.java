package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.client.domain.AdmMenuDTO;
import br.com.hfsframework.admin.domain.AdmMenu;
import br.com.hfsframework.admin.service.IAdmMenuService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/admMenu")
public class AdmMenuRestController
		extends BaseRestController<AdmMenuDTO, AdmMenu, Long, IAdmMenuService> {

	private static final long serialVersionUID = 1L;

	public AdmMenuRestController() {
		super(true);
	}
}
