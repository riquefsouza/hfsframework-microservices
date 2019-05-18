package br.com.hfsframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HikariDataSourceConfig {
	
    //@Value("classpath:hikari.properties")
    //private Resource hikariResource;

    @Bean
    public HikariDataSource getDataSource(Environment env) {
		//HikariConfig config = new HikariConfig(hikariResource.getFile().getAbsolutePath());
    	
    	HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("database.connection.url"));
        config.setDriverClassName(env.getProperty("database.connection.driver"));
        config.setUsername(env.getProperty("database.connection.user"));
        config.setPassword(env.getProperty("database.connection.password"));
        
        config.addDataSourceProperty( "cachePrepStmts" , env.getProperty("hikari.cachePrepStmts") );
        config.addDataSourceProperty( "prepStmtCacheSize" , env.getProperty("hikari.prepStmtCacheSize") );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , env.getProperty("hikari.prepStmtCacheSqlLimit") );
        
        config.addDataSourceProperty( "connectionTimeout", env.getProperty("hikari.connectionTimeout"));
        config.addDataSourceProperty( "idleTimeout", env.getProperty("hikari.idleTimeout"));
        config.addDataSourceProperty( "maxLifetime", env.getProperty("hikari.maxLifetime"));     
        
		HikariDataSource dataSource = new HikariDataSource(config);
    	return dataSource;
    }

}
