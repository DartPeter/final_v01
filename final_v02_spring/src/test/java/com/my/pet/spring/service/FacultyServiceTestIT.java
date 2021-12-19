package com.my.pet.spring.service;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.test.config.TestHibernateConfiguration;

@SpringJUnitConfig(TestHibernateConfiguration.class)
class FacultyServiceTestIT {
	
	@Autowired
	FacultyService facultyService;
	
	@Test
	void testGetAllFaculties() throws DBException {
		assertTrue(facultyService.getAllFaculties().size() >= 0);
	}
	

}
