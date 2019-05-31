package br.com.hfsframework.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IRoleRepository;
import br.com.hfsframework.oauth.repository.IUserRepository;

@Service
@Transactional
public class SignupService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	public User addUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String senha = passwordEncoder.encode(user.getPassword());
		user.setPassword(senha);
		
		return userRepository.save(user);
	}

	@PostConstruct
	private void setupDefaultUser() {
		//if (userRepository.count() == 0) {
		
			Role role = new Role("ADMIN"); 
			roleRepository.saveAndFlush(role);

			Role role2 = new Role("USER"); 
			roleRepository.saveAndFlush(role2);
		
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String senha = passwordEncoder.encode("admin");
			//User user = new User("admin", senha, "admin@admin.com.br", "http://localhost:8080/urlAdminPhoto");
			User user = new User("admin", senha, "admin@admin.com.br", "http://localhost:8080/urlAdminPhoto", 
					Arrays.asList(role));
			//User savedUser = userRepository.saveAndFlush(user);
			userRepository.saveAndFlush(user);
			
			//Role role = new Role("ADMIN", savedUser.getId(), savedUser); 
			//roleRepository.saveAndFlush(role);

			String senha2 = passwordEncoder.encode("user");
			User user2 = new User("user", senha2, "user@user.com.br", "http://localhost:8080/urlUserPhoto",
					Arrays.asList(role2));
			//User savedUser2 = userRepository.saveAndFlush(user2);
			userRepository.saveAndFlush(user2);

			//Role role2 = new Role("USER", savedUser2.getId(), savedUser2); 
			//roleRepository.saveAndFlush(role2);

			
		//}
	}

}
