package com.my.pet.spring.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.my.pet.spring.test.config.TestBeanConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestBeanConfig.class)
class MyControllerTestIT {
	
	@Autowired
	MyController myController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/");
	    viewResolver.setSuffix(".jsp");
		
		mockMvc = standaloneSetup(myController)
				.setViewResolvers(viewResolver)
				.build();
	}
	
	@Test
	void testViewFaculties()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/view_faculties"))
		.andExpect(status().isOk());
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
