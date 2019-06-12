package br.com.hfsframework.oauth.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.oauth.client.domain.UserDTO;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IUserRepository;
import br.com.hfsframework.util.exceptions.TransactionException;

@Service
public class UserService extends BaseBusinessService<UserDTO, User, Long, IUserRepository>
	implements IUserService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	@Cacheable(value = "user.byId", key = "#id", unless = "#result != null")
	public Optional<UserDTO> getDTO(Long id) {
		return super.getDTO(id);
	}

	@Cacheable(value = "user.byUsername", key = "#username", unless = "#username != null)")
	public Optional<UserDTO> findByUsername(String username) {
		return Optional.of(getRepositorio().findByUsername(username).get().toDTO());
	}

	@Override
	@Transactional
	@Caching(evict = { @CacheEvict(value = "user.byUsername", allEntries = true),
			@CacheEvict(value = "user.byId", key = "#result.id", condition = "#result != null") }, 
	put = { @CachePut(value = "user.byId", key = "#result.id", unless = "#result != null") })
	public Optional<User> update(User user) throws TransactionException {
		return super.update(user);
	}

	@Override
	@Transactional
	@Caching(evict = { @CacheEvict(value = "user.byId", key = "#user.id"),
			@CacheEvict(value = "user.byUsername", key = "#user.username") })
	public void delete(User user) throws TransactionException {
		super.delete(user);
	}
	
	public Optional<UserDTO> findByEmail(String email) {		
		return Optional.of(getRepositorio().findByEmail(email).get().toDTO());
	}

}
