package com.my.pet.spring.test.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.my.pet.spring.controller.MyController;
import com.my.pet.spring.repository.FacultyDaoImpl;
import com.my.pet.spring.repository.SysUserDAOImpl;
import com.my.pet.spring.service.FacultyService;
import com.my.pet.spring.service.FacultyServiceImpl;
import com.my.pet.spring.service.SysUserService;
import com.my.pet.spring.service.SysUserServiceImpl;

import dao.FacultyDAO;
import dao.SysUserDAO;

@Configuration
public class TestBeanConfig {
	
	@Bean
	public MyController myController() {
		return new MyController();
	}
	
//	@Bean
//	public FacultyService service() {
//		return new FacultyServiceImpl();
//	}
//	
//	@Bean
//	public FacultyDAO daoImpl() {
//		return new FacultyDaoImpl();
//	}
	

}
