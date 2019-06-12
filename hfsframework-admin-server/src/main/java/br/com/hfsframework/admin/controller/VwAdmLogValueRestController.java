package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.client.domain.VwAdmLogValueDTO;
import br.com.hfsframework.admin.domain.VwAdmLogValue;
import br.com.hfsframework.admin.service.IVwAdmLogValueService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/vwAdmLogValue")
public class VwAdmLogValueRestController
		extends BaseRestController<VwAdmLogValueDTO, VwAdmLogValue, Long, IVwAdmLogValueService> {

	private static final long serialVersionUID = 1L;

	public VwAdmLogValueRestController() {
		super(true);
	}
}
