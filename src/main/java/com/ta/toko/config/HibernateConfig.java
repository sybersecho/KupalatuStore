package com.ta.toko.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan({ "com.ta.toko.module" }) // scan service
public class HibernateConfig {

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		// looking for domain classes that annotated with hibernate
		sessionFactory.setPackagesToScan(new String[] { "com.ta.toko.entity" });
		sessionFactory.setHibernateProperties(additionalProperties());
		return sessionFactory;
	}

	private Properties additionalProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.format_sql", "true");
		return prop;
	}

	@Bean
	public DataSource basicDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		dataSource.setValidationQuery("SELECT 1;");
		dataSource.setUrl("jdbc:h2:mem:store");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setInitialSize(5);
		// dataSource.set
		// dataSource.setmax
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Bean
	public BeanPostProcessor persistanceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
