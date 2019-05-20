package br.com.hfsframework.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HfsUserDetailsService implements UserDetailsService {
	
	private static final Logger log = LogManager.getLogger(HfsUserDetailsService.class);
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	UserDetails user = null;
    	/*
        Optional<User> user = userRepository.findByUsername(userName);
        if(!user.isPresent()){
			log.error("UserName "+userName+" not found");
            throw new UsernameNotFoundException("UserName "+userName+" not found");
		} 

        return new HfsUserDetails(user.get());
        */

    	/*
		String[] roles = { "USER" };

		user = new User(admUsuario.get().getLogin(), admUsuario.get().getSenha(),
				true, true, true, true,
				AuthorityUtils.createAuthorityList(roles));
				
		try {
			admUsuarioService.autenticar(admUsuario);
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		*/

		String[] roles = { "ROLE_ADMIN", "ROLE_USER" };

		//String csenha = DigestUtils.shaHex(senha);
		String csenha = BCrypt.hashpw("admin", BCrypt.gensalt());
		//$2a$10$y7jArsSYCAJjIudWb6zbkuMQZxNFGePkmYJQM0ChB4slgwtUG9RLy

		log.info("user: " + userName);
		log.info("pass: " + csenha);
		
		user = new User(userName, csenha, true, true, true, true, 
				AuthorityUtils.createAuthorityList(roles));

    	return user;
    }	

}
