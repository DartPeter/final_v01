package com.my.pet.spring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.repository.FacultyDaoImpl;
import com.my.pet.spring.service.FacultyService;
import com.my.pet.spring.service.FacultyServiceImpl;
import com.my.pet.spring.test.config.TestBeanConfig;
import com.my.pet.spring.test.config.TestHibernateConfiguration;

import dao.FacultyDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {TestBeanConfig.class, TestHibernateConfiguration.class})
@WebAppConfiguration

//@ContextConfiguration

//@SpringJUnitConfig(TestHibernateConfiguration.class)
//@WebAppConfiguration
//@Transactional

//@SpringJUnitConfig(TestHibernateConfiguration.class)
//@WebAppConfiguration
class MyControllerTest {
	
	@InjectMocks
	MyController controller = new MyController();
	MockMvc mockMvc;
	
	@Mock
	FacultyService facultyService = new FacultyServiceImpl();
	
//	@Autowired
//	@Mock
//	FacultyService facultyService = new FacultyServiceImpl();
//	FacultyDAO facultyDAO = new FacultyDaoImpl();
	
//	@Bean
//	@Primary
//	FacultyService getFacultyService() throws DBException {
//		FacultyService facultyService = Mockito.mock(FacultyService.class);
//		when(facultyService.getAllFaculties()).thenReturn(new ArrayList<FacultyDto>());
//		return facultyService;
//	}
	
	@BeforeEach
	public void init() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/");
	    viewResolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(controller)
				.setViewResolvers(viewResolver)
				.build();
	}
	
//	@Test
//	void testViewFaculties()  throws Exception {
		
//		Mockito.when(facultyService.getAllFaculties()).thenReturn(new ArrayList<FacultyDto>());
//		Mockito.when(facultyDAO.getAllFaculties()).thenReturn(new ArrayList<Faculty>());
		
//		mockMvc.perform(MockMvcRequestBuilders.get("/view_faculties"))
//		.andExpect(status().isOk());
//	}
	
	@Test
	void testHomeUser()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/home_user"))
		.andExpect(status().isOk());
	}
	
	@Test
	void testViewMarks()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/view_marks"))
		.andExpect(status().isOk());
	}
	
	@Test
	void testManageAppointments()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/manage_appointments"))
		.andExpect(status().isOk());
	}

}
