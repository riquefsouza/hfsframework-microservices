package br.com.hfsframework.config;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
//@ComponentScan(basePackages = "br.com.hfsframework")
public class JCacheConfig {

	@Bean(name = "jcache")
	public JCacheManagerFactoryBean getJCacheManagerFactoryBean() throws Exception {
		JCacheManagerFactoryBean bean = new JCacheManagerFactoryBean();
		//bean.setBeanClassLoader(getClass().getClassLoader());
		//bean.setCacheManagerUri(getClass().getResource("/ehcache-jcache-one.xml").toURI());
		bean.setCacheManagerUri(new ClassPathResource("ehcache.xml").getURI());
		return bean;
	}
	
	@Bean(name = "cacheManager")
	public JCacheCacheManager getJCacheManager() throws Exception {
		JCacheCacheManager bean = new JCacheCacheManager();
		javax.cache.CacheManager cacheManager = getJCacheManagerFactoryBean().getObject();
		bean.setCacheManager(cacheManager);
		return bean;

	}

	@Bean(name = "hfsCache")
	public Cache getHfsJCache() throws Exception {
		String cacheName = "hfscache";
		//Cache<Object, Object> cache = getJCacheManagerFactoryBean().getObject().getCache(cacheName);
		//cache.getCacheManager().enableStatistics(cacheName, true);
		//cache.getCacheManager().enableManagement(cacheName, true);
		Cache cache = getJCacheManager().getCache(cacheName);
		return cache;
	}

}
