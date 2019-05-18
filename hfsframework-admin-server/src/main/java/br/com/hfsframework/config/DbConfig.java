package br.com.hfsframework.config;

import java.util.Properties;

import javax.sql.DataSource;

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
	
    @Value("classpath:schema.sql")
    private Resource schemaScript;

    //@Value("classpath:data.sql")
    //private Resource dataScript;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		//emf.setDataSource(dataSource());
		emf.setDataSource(hikariDS.getDataSource(env));
		emf.setPackagesToScan("br.com.hfsframework.admin.domain");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
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
        populator.addScript(schemaScript);
        //populator.addScript(dataScript);
        return populator;
    }
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("database.connection.driver"));
		dataSource.setUrl(env.getProperty("database.connection.url"));
		dataSource.setUsername(env.getProperty("database.connection.user"));
		dataSource.setPassword(env.getProperty("database.connection.password"));
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
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("database.hibernate.schema_update"));
				setProperty("hibernate.dialect", env.getProperty("database.hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("database.hibernate.show_sql"));
				setProperty("hibernate.format_sql", env.getProperty("database.hibernate.format_sql"));
				setProperty("hibernate.use_sql_comments", env.getProperty("database.hibernate.use_sql_comments"));
				
				setProperty("hibernate.id.new_generator_mappings", env.getProperty("database.hibernate.id.new_generator_mappings"));
				setProperty("hibernate.archive.autodetection", env.getProperty("database.hibernate.archive.autodetection"));
				setProperty("hibernate.jdbc.batch_size", env.getProperty("database.hibernate.jdbc.batch_size"));
				setProperty("hibernate.max_fetch_depth", env.getProperty("database.hibernate.max_fetch_depth"));
				setProperty("hibernate.transaction.flush_before_completion", env.getProperty("database.hibernate.transaction.flush_before_completion"));
				setProperty("hibernate.generate_statistics", env.getProperty("database.hibernate.generate_statistics"));
				setProperty("hibernate.enable_lazy_load_no_trans", env.getProperty("database.hibernate.enable_lazy_load_no_trans"));
				
				setProperty("hibernate.cache.use_query_cache", env.getProperty("database.hibernate.cache.use_query_cache"));
				setProperty("hibernate.cache.use_second_level_cache", env.getProperty("database.hibernate.cache.use_second_level_cache"));
				setProperty("hibernate.cache.provider_class", env.getProperty("database.hibernate.cache.provider_class"));
				setProperty("hibernate.cache.region.factory_class", env.getProperty("database.hibernate.cache.region.factory_class"));
				setProperty("net.sf.ehcache.configurationResourceName", env.getProperty("database.net.sf.ehcache.configurationResourceName"));
				
				setProperty("hibernate.c3p0.acquire_increment", env.getProperty("database.hibernate.c3p0.acquire_increment"));
				setProperty("hibernate.c3p0.min_size", env.getProperty("database.hibernate.c3p0.min_size"));
				setProperty("hibernate.c3p0.max_size", env.getProperty("database.hibernate.c3p0.max_size"));
				setProperty("hibernate.c3p0.timeout", env.getProperty("database.hibernate.c3p0.timeout"));
				setProperty("hibernate.c3p0.max_statements", env.getProperty("database.hibernate.c3p0.max_statements"));
				setProperty("hibernate.c3p0.idle_test_period", env.getProperty("database.hibernate.c3p0.idle_test_period"));				
			}
		};
	}
}
