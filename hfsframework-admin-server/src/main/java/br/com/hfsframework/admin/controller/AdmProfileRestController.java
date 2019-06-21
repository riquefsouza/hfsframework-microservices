package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.admin.domain.AdmProfile;
import br.com.hfsframework.admin.service.IAdmProfileService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/admProfile")
public class AdmProfileRestController 
		extends BaseRestController<AdmProfileDTO, AdmProfile, Long, IAdmProfileService> {

	private static final long serialVersionUID = 1L;

	public AdmProfileRestController() {
		super(true);
	}

}
