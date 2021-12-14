package com.my.pet.spring.service;

import java.util.List;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.exception.DBException;

public interface FacultyService {

Faculty getFacultyByName(String name) throws DBException;
    
	FacultyDto insertFaculty(FacultyDto dto) throws DBException;
    
	FacultyDto updateFaculty(FacultyDto faculty) throws DBException;

    void deleteFaculty(Integer id) throws DBException;
    
    List<FacultyDto> getAllFaculties() throws DBException;
	
}
