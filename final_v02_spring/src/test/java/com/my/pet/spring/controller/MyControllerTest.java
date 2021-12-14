package com.my.pet.spring.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.my.pet.spring.service.FacultyService;
import com.my.pet.spring.service.FacultyServiceImpl;
import com.my.pet.spring.test.config.TestBeanConfig;
import com.my.pet.spring.test.config.TestHibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {TestBeanConfig.class, TestHibernateConfiguration.class})
@WebAppConfiguration
class MyControllerTest {
	
	@InjectMocks
	MyController controller = new MyController();
	MockMvc mockMvc;
	
	@Mock
	FacultyService facultyService = new FacultyServiceImpl();
	
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
