package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.AdmParameter;
import br.com.hfsframework.admin.service.IAdmParameterService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/parameter")
public class AdmParameterRestController extends BaseRestController<AdmParameter, Long, IAdmParameterService> {

	private static final long serialVersionUID = 1L;

}
