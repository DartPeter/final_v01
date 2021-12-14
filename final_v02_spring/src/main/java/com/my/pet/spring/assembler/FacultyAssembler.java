package com.my.pet.spring.assembler;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.dto.FacultyDto;

public interface FacultyAssembler {
	Faculty assemble (FacultyDto dto);
	FacultyDto assemble (Faculty entity);
}
