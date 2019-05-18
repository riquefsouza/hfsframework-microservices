package br.com.hfsframework.oauth.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.hfsframework.base.BaseBusinessService;
import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.repository.IUserRepository;
import br.com.hfsframework.util.exceptions.TransacaoException;

@Service
public class UserService extends BaseBusinessService<User, Long, IUserRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	@Cacheable(value = "user.byId", key = "#id", unless = "#result != null")
	public Optional<User> get(Long id) {
		return super.get(id);
	}

	@Cacheable(value = "user.byUsername", key = "#username", unless = "#username != null)")
	public Optional<User> findByUsername(String username) {
		return getRepositorio().findByUsername(username);
	}

	@Override
	@Transactional
	@Caching(evict = { @CacheEvict(value = "user.byUsername", allEntries = true),
			@CacheEvict(value = "user.byId", key = "#result.id", condition = "#result != null") }, 
	put = { @CachePut(value = "user.byId", key = "#result.id", unless = "#result != null") })
	public Optional<User> update(User user) throws TransacaoException {
		return super.update(user);
	}

	@Override
	@Transactional
	@Caching(evict = { @CacheEvict(value = "user.byId", key = "#user.id"),
			@CacheEvict(value = "user.byUsername", key = "#user.username") })
	public void delete(User user) throws TransacaoException {
		super.delete(user);
	}
}
