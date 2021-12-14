package com.my.pet.spring.assembler;

import org.springframework.stereotype.Service;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.dto.FacultyDto;

@Service
public class FacultyAssemblerImpl implements FacultyAssembler {

	@Override
	public Faculty assemble(FacultyDto dto) {
		Faculty faculty = new Faculty();
		faculty.setId(dto.getId());
		faculty.setName(dto.getName());
		faculty.setBudgetPlaces(dto.getBudgetPlaces());
		faculty.setTotalPlaces(dto.getTotalPlaces());
		return faculty;
	}

	@Override
	public FacultyDto assemble(Faculty entity) {
		FacultyDto dto = new FacultyDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setBudgetPlaces(entity.getBudgetPlaces());
		dto.setTotalPlaces(entity.getTotalPlaces());
		return dto;
	}

}
