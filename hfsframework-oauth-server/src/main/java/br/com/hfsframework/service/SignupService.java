package br.com.hfsframework.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hfsframework.oauth.domain.Role;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IUserRepository;

@Service
@Transactional
public class SignupService {

	@Autowired
	private IUserRepository userRepository;

	public User addUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String senha = passwordEncoder.encode(user.getPassword());
		user.setPassword(senha);
		
		return userRepository.save(user);
	}

	@PostConstruct
	private void setupDefaultUser() {
		//if (userRepository.count() == 0) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String senha = passwordEncoder.encode("admin");
			User user = new User("admin", senha, "admin@admin.com.br", "http://localhost:8080/urlAdminPhoto", 
					Arrays.asList(new Role("ADMIN")));
			//User savedUser = userRepository.save(user);
			userRepository.save(user);

			String senha2 = passwordEncoder.encode("user");
			User user2 = new User("user", senha2, "user@user.com.br", "http://localhost:8080/urlUserPhoto", 
					Arrays.asList(new Role("USER")));
			//User savedUser2 = userRepository.save(user2);
			userRepository.save(user2);
			
		//}
	}

}
