package br.com.hfsframework.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.service.IRoleService;
import br.com.hfsframework.oauth.service.IUserService;

@Service
@Transactional
public class SignupService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	public User addUser(User user) {
		Optional<Role> role = roleService.findByName("USER");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String senha = passwordEncoder.encode(user.getPassword());
		user.setPassword(senha);
		user.setRoles(Sets.newHashSet(role.get()));

		return userService.add(user).get();
	}

	@PostConstruct
	private void setupDefaultUser() {
		Role role = new Role("ADMIN");
		Optional<Role> savedRole = roleService.add(role);
		Log.info(savedRole.get().toString());

		Role role2 = new Role("USER");
		Optional<Role> savedRole2 = roleService.add(role2);
		Log.info(savedRole2.get().toString());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String senha = passwordEncoder.encode("admin");
		User user = new User("admin", senha, "admin@admin.com.br", "http://localhost:8080/urlAdminPhoto",
				Sets.newHashSet(savedRole.get()));
		Optional<User> savedUser = userService.add(user);
		Log.info(savedUser.get().toString());

		String senha2 = passwordEncoder.encode("user");
		User user2 = new User("user", senha2, "user@user.com.br", "http://localhost:8080/urlUserPhoto",
				Sets.newHashSet(savedRole2.get()));
		Optional<User> savedUser2 = userService.add(user2);
		Log.info(savedUser2.get().toString());
	}

}
