package br.com.hfsframework.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.AdmPageRestClient;
import br.com.hfsframework.admin.client.AdmProfileRestClient;
import br.com.hfsframework.admin.client.AdmUserRestClient;
import br.com.hfsframework.admin.client.domain.AdmPageDTO;
import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.admin.client.domain.AdmUserDTO;
import br.com.hfsframework.base.BaseDualList;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admProfileView")
public class AdmProfileController extends BaseViewRegisterRestClient<AdmProfileDTO, Long, AdmProfileRestClient> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdmPageRestClient admPageClient;
	
	private BaseDualList<AdmPageDTO> dualListAdmPage;
	
	private List<AdmPageDTO> listAllAdmPages;
	
	@Autowired
	private AdmUserRestClient admUserClient;
	
	private BaseDualList<AdmUserDTO> dualListAdmUser;
	
	private List<AdmUserDTO> listAllAdmUsers;	
	
	public AdmProfileController() {
		super(new AdmProfileRestClient(), 
				"/private/admProfile/listAdmProfile", 
				"/private/admProfile/editAdmProfile", 
				"AdmProfile");
		this.listAllAdmPages = new ArrayList<AdmPageDTO>();
		this.listAllAdmUsers = new ArrayList<AdmUserDTO>();
	}

	private void carregarAdmPages(AdmProfileDTO bean, boolean bEdit) {
		List<AdmPageDTO> listAdmPagesSelected;		 
		List<AdmPageDTO> listAdmPages = admPageClient.getAll();
		listAllAdmPages.addAll(listAdmPages);
		
		if (bEdit) { 
			listAdmPagesSelected = new ArrayList<AdmPageDTO>(bean.getAdmPages());
			listAdmPages.removeAll(listAdmPagesSelected);
		} else {
			listAdmPagesSelected = new ArrayList<AdmPageDTO>();
		}
		
		this.dualListAdmPage = new BaseDualList<AdmPageDTO>(listAdmPages, listAdmPagesSelected);		
	}
	
	private void carregarAdmUsers(AdmProfileDTO bean, boolean bEdit) {
		List<AdmUserDTO> listAdmUsersSelected;		 
		List<AdmUserDTO> listAdmUsers = admUserClient.getAll();
		listAllAdmUsers.addAll(listAdmUsers);
		
		if (bEdit) { 
			listAdmUsersSelected = new ArrayList<AdmUserDTO>(bean.getAdmUsers());
			listAdmUsers.removeAll(listAdmUsersSelected);
		} else {
			listAdmUsersSelected = new ArrayList<AdmUserDTO>();
		}
		
		this.dualListAdmUser = new BaseDualList<AdmUserDTO>(listAdmUsers, listAdmUsersSelected);		
	}	
	
	@PostConstruct
	public void init() {
		//
	}
}
