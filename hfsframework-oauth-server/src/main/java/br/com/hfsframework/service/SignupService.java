package br.com.hfsframework.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hfsframework.domain.User;
import br.com.hfsframework.domain.UserRole;
import br.com.hfsframework.repository.IUserRepository;

@Service
@Transactional
public class SignupService {

	//private static final Logger log = LogManager.getLogger(SignupService.class);
	
	@Autowired
	private IUserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

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
			User user = new User("admin", senha,
					Arrays.asList(new UserRole("USER"), new UserRole("ADMIN")));
			//User savedUser = userRepository.save(user);
			userRepository.save(user);
			
		//}
	}

}
