package br.com.hfsframework.config;

import java.util.Collections;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "br.com.hfsframework.admin.repository")
@EnableTransactionManagement
public class DbConfig {

	@Autowired
	private Environment env;
	
	@Autowired
	private HikariDataSourceConfig hikariDS;
	
    //@Value("classpath:schema.sql")
    //private Resource schemaScript;

    @Value("classpath:data.sql")
    private Resource dataScript;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		//emf.setDataSource(dataSource());
		emf.setDataSource(hikariDS.getDataSource(env));
		emf.setPackagesToScan("br.com.hfsframework.admin.domain");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);

		Properties additionalProperties = additionalProperties();
		
		additionalProperties.put("hibernate.integrator_provider",
                (IntegratorProvider) () -> Collections.singletonList(MetadataExtractorIntegrator.INSTANCE));
		
		emf.setJpaProperties(additionalProperties());

		return emf;
	}

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        //populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("connection.driver"));
		dataSource.setUrl(env.getProperty("connection.url"));
		dataSource.setUsername(env.getProperty("connection.user"));
		dataSource.setPassword(env.getProperty("connection.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1L;
			{
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
				setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
				setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
				
				setProperty("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));				
				setProperty("hibernate.archive.autodetection", env.getProperty("hibernate.archive.autodetection"));
				setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
				setProperty("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
				setProperty("hibernate.transaction.flush_before_completion", env.getProperty("hibernate.transaction.flush_before_completion"));
				setProperty("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
				setProperty("hibernate.enable_lazy_load_no_trans", env.getProperty("hibernate.enable_lazy_load_no_trans"));
				
				setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
				setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
				setProperty("hibernate.javax.cache.missing_cache_strategy", env.getProperty("hibernate.javax.cache.missing_cache_strategy"));
				//setProperty("hibernate.cache.provider_class", env.getProperty("hibernate.cache.provider_class"));
				//setProperty("hibernate.cache.region.factory_class", env.getProperty("hibernate.cache.region.factory_class"));
				//setProperty("net.sf.ehcache.configurationResourceName", env.getProperty("net.sf.ehcache.configurationResourceName"));
				
				/*
				hibernate.cache.provider_class = net.sf.ehcache.hibernate.SingletonEhCacheProvider
				hibernate.cache.region.factory_class = org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory
				hibernate.cache.provider_class = org.hibernate.cache.EhCacheProvider
				hibernate.cache.provider_class = org.hibernate.cache.NoCacheProvider
				hibernate.cache.provider_class = org.hibernate.cache.OSCacheProvider
				net.sf.ehcache.configurationResourceName = /ehcache.xml
				*/
				
				setProperty("hibernate.c3p0.acquire_increment", env.getProperty("hibernate.c3p0.acquire_increment"));
				setProperty("hibernate.c3p0.min_size", env.getProperty("hibernate.c3p0.min_size"));
				setProperty("hibernate.c3p0.max_size", env.getProperty("hibernate.c3p0.max_size"));
				setProperty("hibernate.c3p0.timeout", env.getProperty("hibernate.c3p0.timeout"));
				setProperty("hibernate.c3p0.max_statements", env.getProperty("hibernate.c3p0.max_statements"));
				setProperty("hibernate.c3p0.idle_test_period", env.getProperty("hibernate.c3p0.idle_test_period"));				
			}
		};
	}
}
