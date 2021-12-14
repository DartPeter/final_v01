package com.my.pet.spring.hibernate;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.hibernate.SessionFactory;
import org.postgresql.ds.PGSimpleDataSource;

@Configuration
@ComponentScan(basePackages = "com.my.pet.spring")
@EnableTransactionManagement
public class HibernateConfiguration {
	
	@Bean
	public DataSource postgresDataSource() throws SQLException {
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost/test");
		dataSource.setUser("postgres");
		dataSource.setPassword("p19");
		return dataSource;
	}
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(postgresDataSource());
        sessionFactory.setPackagesToScan("com.my.pet.spring" );
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
	
	@Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
	
	private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return hibernateProperties;
    }

}
