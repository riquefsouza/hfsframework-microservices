package br.com.hfsframework.oauth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	public AutUserController() {
		super(new UserRestClient(), "/private/autUser/listAutUser", "/private/autUser/editAutUser", "AutUser");
	}

	private void carregarRoles(ModelAndView mv, User bean, boolean bEdit) {
		List<Role> listRolesSelected;
		List<Role> listRoles = roleClient.getAll();
		
		if (bEdit) { 
			listRolesSelected = bean.getRoles();
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
		
		if (mv.isPresent()) {
			roleClient.init(this.authServerURL, this.accesToken);
			carregarRoles(mv.get(), bean, true);
			mv.get().addObject("listSourceRoles", this.dualListRole.getSource());
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
			
			if (mv.isPresent()) {
				roleClient.init(this.authServerURL, this.accesToken);
				carregarRoles(mv.get(), bean, true);
				mv.get().addObject("listSourceRoles", this.dualListRole.getSource());
				//mv.get().addObject("listTargetRoles", this.dualListRole.getTarget());
				mv.get().addObject("user", bean);
			}
		}
		
		return mv.get();
	}
	
}
