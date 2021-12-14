package com.my.pet.spring.assembler;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.pet.spring.domain.Appointment;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.Subject;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.dto.AppointmentDto;
import com.my.pet.spring.dto.MarkDto;
import com.my.pet.spring.dto.SysUserDto;

import dao.FacultyDAO;
import dao.SubjectDAO;

@Service
public class SysUserAssemblerImpl implements SysUserAssembler {
	
	@Autowired
	private FacultyDAO facultyDAO;
	@Autowired
	private SubjectDAO subjectDAO;

	@Override
	public SysUser assemble(SysUserDto dto) {
		SysUser sysUser = new SysUser();
		sysUser.setId(dto.getId());
		sysUser.setLogin(dto.getLogin());
		sysUser.setFullName(dto.getFullName());
		sysUser.setEmail(dto.getEmail());
		sysUser.setPass(dto.getPass());
		sysUser.setUserType(dto.getUserType());
		sysUser.setBlocked(dto.isBlocked());
		// TODO certificate
		
		// Appointments
		Map<Integer, Faculty> facultyById = getFaculties(dto);
		for (AppointmentDto appointmentDto : dto.getAppointments()) {
			Appointment appointment = assemble(appointmentDto);
			appointment.setSysUser(sysUser);
			Faculty faculty =  facultyById.get(appointmentDto.getFacultyId());
			appointment.setFaculty(faculty);
			sysUser.getAppointments().add(appointment);
		}
		
		//Marks
		Map<Integer, Subject> subjectById = getSubjects();
		for (MarkDto marktDto : dto.getMarks()) {
			Mark mark = assemble(marktDto);
			mark.setSysUser(sysUser);
			Subject subject = subjectById.get(marktDto.getSubjId());
			mark.setSubject(subject);
			sysUser.getMarks().add(mark);
		}
		
		return sysUser;
	}
	
	private Map<Integer, Faculty> getFaculties(SysUserDto dto) {
		List<Integer> facultyIds = dto.getAppointments().stream()
				.map(AppointmentDto::getFacultyId).collect(Collectors.toList());
		List<Faculty> facultyList = facultyDAO.getByIds(facultyIds);
		return facultyList.stream().collect(Collectors.toMap(Faculty::getId, Function.identity()));
	}
	
	private Map<Integer, Subject> getSubjects() {
		List<Subject> subjectList = subjectDAO.getAllSubjects();
		return subjectList.stream().collect(Collectors.toMap(Subject::getId, Function.identity()));
	}

	@Override
	public SysUserDto assemble(SysUser entity) {
		if (entity == null) {
			return null;
		}
		SysUserDto dto = new SysUserDto();
		dto.setId(entity.getId());
		dto.setLogin(entity.getLogin());
		dto.setFullName(entity.getFullName());
		dto.setEmail(entity.getEmail());
		dto.setPass(entity.getPass());
		dto.setUserType(entity.getUserType());
		dto.setBlocked(entity.isBlocked());
		// TO DO certificate
		
		//Appointments
		List<AppointmentDto> appointments = entity.getAppointments()
				.stream()
				.map(this::assemble)
				.sorted(Comparator.comparing(AppointmentDto::getFacultyId))
				.collect(Collectors.toList());
		dto.setAppointments(appointments);
		
		//Marks
		List<MarkDto> marks = entity.getMarks()
				.stream()
				.map(this::assemble)
				.sorted(Comparator.comparing(MarkDto::getSubjId))
				.collect(Collectors.toList());
		dto.setMarks(marks);
		
		
		return dto;
	}
	
	private Appointment assemble(AppointmentDto dto) {
		Appointment appointment = new Appointment();
		appointment.setId(dto.getId());
		return appointment;
	}
	
	private AppointmentDto assemble(Appointment appointment) {
		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setId(appointment.getId());
		appointmentDto.setFacultyId(appointment.getFaculty().getId());
		appointmentDto.setFacultyName(appointment.getFaculty().getName());
		return appointmentDto;
	}
	
	private Mark assemble(MarkDto dto) {
		Mark mark = new Mark();
		mark.setId(dto.getId());
		mark.setValue(dto.getValue());
		return mark;
	}
	
	private MarkDto assemble(Mark mark) {
		MarkDto markDto = new MarkDto();
		markDto.setId(mark.getId());
		markDto.setValue(mark.getValue());
		markDto.setSysUserId(mark.getSysUser().getId());
		markDto.setSubjId(mark.getSubject().getId());
		markDto.setSubjectName(mark.getSubject().getName());
		return markDto;
	}

}
