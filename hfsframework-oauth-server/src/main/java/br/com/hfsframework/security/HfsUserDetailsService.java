package br.com.hfsframework.security;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hfsframework.domain.User;
import br.com.hfsframework.repository.IUserRepository;

@Service
public class HfsUserDetailsService implements UserDetailsService {
	
	private static final Logger log = LogManager.getLogger(HfsUserDetailsService.class);
	
	@Autowired
	private IUserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);
        if(!user.isPresent()){
			log.error("UserName "+userName+" not found");
            throw new UsernameNotFoundException("UserName "+userName+" not found");
		} 

        return new HfsUserDetails(user.get());
    }	

}
