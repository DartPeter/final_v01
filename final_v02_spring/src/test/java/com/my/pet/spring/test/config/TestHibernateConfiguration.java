package com.my.pet.spring.test.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@ComponentScan(basePackages = "com.my.pet.spring")
@ComponentScan(basePackages = {"com.my.pet.spring.service", "com.my.pet.spring.repository", "com.my.pet.spring.assembler"})
@EnableTransactionManagement
public class TestHibernateConfiguration {

//	@Bean
//	public DataSource postgresDataSource() throws SQLException {
//		PGSimpleDataSource dataSource = new PGSimpleDataSource();
//		dataSource.setUrl("jdbc:postgresql://localhost/test");
//		dataSource.setUser("postgres");
//		dataSource.setPassword("p19");
//		return dataSource;
//	}
	
	 @Bean
    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(
//        		postgresDataSource()
        		h2DataSource()
        		);
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
        
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        
        return hibernateProperties;
    }

}
