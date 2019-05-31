package br.com.hfsframework.oauth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hfsframework.base.BaseDualList;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;
import br.com.hfsframework.oauth.client.RoleRestClient;
import br.com.hfsframework.oauth.client.UserRestClient;
import br.com.hfsframework.oauth.client.domain.Role;
import br.com.hfsframework.oauth.client.domain.User;

@Controller
@RequestMapping(value = "/private/userView")
public class AutUserController extends BaseViewRegisterRestClient<User, Long, UserRestClient> {

	private static final long serialVersionUID = 1L;

	//private static final Logger log = LoggerFactory.getLogger(AutUserController.class);
			
	@Autowired
	private RoleRestClient roleClient;
	
	private BaseDualList<Role> dualListRole;
	
	private List<Role> listAllRoles;
	
	public AutUserController() {
		super(new UserRestClient(), "/private/autUser/listAutUser", "/private/autUser/editAutUser", "AutUser");
		this.listAllRoles = new ArrayList<Role>();
	}
	
	private void carregarRoles(ModelAndView mv, User bean, boolean bEdit) {
		List<Role> listRolesSelected;		 
		List<Role> listRoles = roleClient.getAll();
		listAllRoles.addAll(listRoles);
		
		if (bEdit) { 
			listRolesSelected = new ArrayList<Role>(bean.getRoles());
			listRoles.removeAll(listRolesSelected);
		} else {
			listRolesSelected = new ArrayList<Role>();
		}
		
		this.dualListRole = new BaseDualList<Role>(listRoles, listRolesSelected);		
	}
	
	@Override
	@GetMapping("/add")	
	public ModelAndView add(User bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());
		bean.clear();
		
		//String pwdCrypt = BCrypt.hashpw("123456", BCrypt.gensalt());
		bean.setPassword("null");
		bean.setCurrentPassword("null");
		bean.setNewPassword("null");		
		bean.setConfirmNewPassword("null");
				
		if (mv.isPresent()) {
			roleClient.init(this.authServerURL, this.accesToken);
			carregarRoles(mv.get(), bean, false);
			mv.get().addObject("listSourceRoles", this.dualListRole.getSource());
			mv.get().addObject("bean", bean);
		}
		
		return mv.get();
	}

	@Override
	@GetMapping("/edit")
	public ModelAndView edit(User bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());

		Optional<User> obj = bean.fromJSON();
		if (obj.isPresent()) {
			bean = obj.get();

			bean.setCurrentPassword("null");
			bean.setNewPassword("null");		
			bean.setConfirmNewPassword("null");
			
			if (mv.isPresent()) {
				roleClient.init(this.authServerURL, this.accesToken);
				carregarRoles(mv.get(), bean, true);
				mv.get().addObject("listSourceRoles", this.dualListRole.getSource());
				mv.get().addObject("listTargetRoles", this.dualListRole.getTarget());
				mv.get().addObject("bean", bean);
			}
		}
		
		return mv.get();
	}

	@Override
	@PostMapping
	public ModelAndView save(@Valid @ModelAttribute User bean, 
			BindingResult result, RedirectAttributes attributes) {
	
		Optional<ModelAndView> mv = getPage(this.getListPage());
		
		return this.saveCallableBefore(bean, result, attributes, new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				
				if (mv.isPresent()) {
					
					bean.getRoles().forEach(item -> {					
						listAllRoles.stream()
							.filter(p -> p.getId().equals(item.getId()))
							.findFirst()
							.ifPresent(x -> item.setName(x.getName()));	
					});
					
					mv.get().addObject("bean", bean);
				}
				
				return "";
			}
		});
	}
}
