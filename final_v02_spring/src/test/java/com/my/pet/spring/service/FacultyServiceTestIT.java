package com.my.pet.spring.service;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import com.my.pet.spring.exception.DBException;
import dao.postgres.PostgresFacultyDAO;

class FacultyServiceTestIT {
	
	private static PostgresFacultyDAO pfdao = PostgresFacultyDAO.getInstance();
	
	@Test
	void testGetAllFaculties() throws DBException {
		assertTrue(pfdao.getAllFaculties().size() >= 0);
	}
	

}
