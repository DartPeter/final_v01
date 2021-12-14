package com.my.pet.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.test.config.TestBeanConfig;
import com.my.pet.spring.test.config.TestHibernateConfiguration;

import junit.framework.Assert;

//v1
@SpringJUnitConfig(TestHibernateConfiguration.class)
@WebAppConfiguration
//v2
//@DataJpa...
//v3
//@RunWith(SpringJUnit4ClassRunner.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestBeanConfig.class, TestHibernateConfiguration.class})
@Transactional // this clears db between tests
class FacultyUpdatesTest {

	@Autowired
	private FacultyService facultyService;
	
	private FacultyDto create(String name) throws DBException {
		FacultyDto fdto = new FacultyDto();
		fdto.setName(name);
		fdto.setBudgetPlaces(1);
		fdto.setTotalPlaces(2);
		fdto = facultyService.insertFaculty(fdto);
		return fdto;
	}
	
	@Test
	void test() throws DBException {
		System.out.println(
				"---- start ---- \n" + 
				facultyService.getAllFaculties() + 
				"\n---- ? ---- \n"
				);
		
	}
	
	@Test
	void testCreate() throws DBException {
		create("f1");
		assertEquals(1, facultyService.getAllFaculties().size());
	}
	
	@Test
	void testUpdate() throws DBException {
		FacultyDto f1 = create("f1");
		f1.setName("f2");
		f1.setBudgetPlaces(3);
		f1.setTotalPlaces(4);
		facultyService.updateFaculty(f1);
		FacultyDto res = facultyService.getAllFaculties().get(0);
		assertEquals("f2", res.getName());
		assertEquals(3, res.getBudgetPlaces());
		assertEquals(4, res.getTotalPlaces());
	}
	
	@Test
	void testDelete() throws DBException {
		FacultyDto f = create("f1");
		assertEquals(1, facultyService.getAllFaculties().size());
		facultyService.deleteFaculty(f.getId());
		assertEquals(0, facultyService.getAllFaculties().size());
	}

}
