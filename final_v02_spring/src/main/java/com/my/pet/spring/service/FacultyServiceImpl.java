package com.my.pet.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.assembler.FacultyAssembler;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.exception.DBException;

import dao.FacultyDAO;

@Service
public class FacultyServiceImpl implements FacultyService {
	
	@Autowired
	private FacultyDAO facultyDAO;
	@Autowired
	private FacultyAssembler facultyAssembler;

	@Override
	public Faculty getFacultyByName(String name) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacultyDto insertFaculty(FacultyDto dto) throws DBException {
		Faculty faculty = facultyAssembler.assemble(dto);
		facultyDAO.insertFaculty(faculty);
		FacultyDto result = facultyAssembler.assemble(faculty);
		return result;
	}

	@Override
	@Transactional
	public FacultyDto updateFaculty(FacultyDto dto) throws DBException {
		Faculty entity = facultyDAO.getFacultyById(dto.getId());
		Faculty updatedEntity = facultyAssembler.assemble(dto);
		entity.setName(updatedEntity.getName());
		entity.setBudgetPlaces(updatedEntity.getBudgetPlaces());
		entity.setTotalPlaces(updatedEntity.getTotalPlaces());
		return facultyAssembler.assemble(entity);
	}

	@Override
	@Transactional
//	public void removeFaculty(Faculty faculty) throws DBException {
	public void deleteFaculty(Integer id) throws DBException {
		facultyDAO.deleteFaculty(id);
	}

	@Override
	public List<FacultyDto> getAllFaculties() throws DBException {
		List<Faculty> faculties = facultyDAO.getAllFaculties();
		List<FacultyDto> fdtos = faculties.stream().map(f -> facultyAssembler.assemble(f)).collect(Collectors.toList());
		return fdtos;
	}

}
