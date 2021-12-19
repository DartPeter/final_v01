package com.my.pet.spring.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.Subject;
import com.my.pet.spring.dto.AppointmentDto;
import com.my.pet.spring.dto.MarkDto;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.test.config.TestHibernateConfiguration;

import dao.FacultyDAO;
import dao.SubjectDAO;

@SpringJUnitConfig(TestHibernateConfiguration.class)
@Transactional
public class SysUserUpdatesTest {
	
	@Autowired
	private FacultyDAO facultyDAO;
	@Autowired
	private SubjectDAO subjectDAO;
	@Autowired
	private SysUserService sysUserService;
	
	private static final String[] FACULTY_NAMES = {"f1", "f2", "f3"};
	private static final String[] SUBJECT_NAMES = {"s1", "s2", "s3"};
	
	private Map<String, Faculty> faculties = Collections.emptyMap();
	private Map<String, Subject> subjects = Collections.emptyMap();
	
	@BeforeEach
	public void initFaculties() throws DBException {
		List<Faculty> initialList = createFaculties();
		faculties = initialList.stream().collect(Collectors.toMap(Faculty::getName, Function.identity()));
	}
	
	@BeforeEach
	public void initSubjects() throws DBException {
		List<Subject> initialList = createSubjects();
		subjects = initialList.stream().collect(Collectors.toMap(Subject::getName, Function.identity()));
	}
	
	
	@Test
	void testCreateSysUser() throws DBException {
		
		SysUserDto sysUserDto = createSysUserDto("su1");
		SysUserDto createdSysUser = sysUserService.insertSysUser(sysUserDto, null);
		Assertions.assertEquals(sysUserDto.getAppointments().size(), createdSysUser.getAppointments().size());
		Assertions.assertEquals(sysUserDto.getMarks().size(), createdSysUser.getMarks().size());
	}
	
	@Test
	void testGetSysUser() throws DBException {
		String login = "su1";
		SysUserDto sysUserDto = createSysUserDto(login);
		sysUserService.insertSysUser(sysUserDto, null);
		SysUserDto result = sysUserService.getSysUserByLogin(login);
		
		Assertions.assertEquals(sysUserDto.getAppointments().size(), result.getAppointments().size());
		Assertions.assertEquals(sysUserDto.getMarks().size(), result.getMarks().size());
	}
	
	private List<Faculty> createFaculties() throws DBException {
		return Stream.of(FACULTY_NAMES).map(name -> {
			Faculty f = new Faculty();
			f.setName(name);
			f.setBudgetPlaces(1);
			f.setTotalPlaces(2);
			try {
				return facultyDAO.insertFaculty(f);
			} catch (DBException e) {
				return null;
			}
		}).collect(Collectors.toList());
	}
	
	private List<Subject> createSubjects() throws DBException {
		return Stream.of(SUBJECT_NAMES).map(name -> subjectDAO.create(name))
				.collect(Collectors.toList());
	}
	
	private SysUserDto createSysUserDto(String name) {
		SysUserDto sysUserDto = new SysUserDto();
		sysUserDto.setEmail("email");
		sysUserDto.setFullName("fn");
		sysUserDto.setLogin(name);
		sysUserDto.setPass("pass");
		sysUserDto.setUserType("enrolee");
		
		AppointmentDto firstAppointment = new AppointmentDto();
		firstAppointment.setFacultyId(getFacultyId(0));
		sysUserDto.getAppointments().add(firstAppointment);
		
		AppointmentDto secondAppointment = new AppointmentDto();
		secondAppointment.setFacultyId(getFacultyId(1));
		sysUserDto.getAppointments().add(secondAppointment);
		
		MarkDto firstMark = new MarkDto();
		firstMark.setValue(4);
		firstMark.setSubjId(getSubjectId(0));
		sysUserDto.getMarks().add(firstMark);
		
		MarkDto secondMark = new MarkDto();
		secondMark.setValue(5);
		secondMark.setSubjId(getSubjectId(1));
		sysUserDto.getMarks().add(secondMark);
		
		return sysUserDto;
	}
	
	private Integer getFacultyId(int facultyIndex) {
		return faculties.get(FACULTY_NAMES[facultyIndex]).getId();
	}
	
	private Integer getSubjectId(int subjectIndex) {
		return subjects.get(SUBJECT_NAMES[subjectIndex]).getId();
	}
	
	@Test
	void testUpdateSysUserAppointments() throws DBException {
		String login = "login";
		SysUserDto sysUserDto = createSysUserDto(login);
		SysUserDto createdSysUserDto = sysUserService.insertSysUser(sysUserDto, null);
		Assertions.assertEquals(2, createdSysUserDto.getAppointments().size());
		
		AppointmentDto newAppointmentDto = new AppointmentDto();
		newAppointmentDto.setFacultyName(FACULTY_NAMES[2]);
		newAppointmentDto.setFacultyId(getFacultyId(2));
		
		createdSysUserDto.getAppointments().add(newAppointmentDto);
		
		sysUserService.update(createdSysUserDto);
		SysUserDto updatedSysUserDto = sysUserService.getSysUserByLogin(login);
		
		// Assertions
		Assertions.assertEquals(3, updatedSysUserDto.getAppointments().size());
	}
	
	@Test
	void testUpdateSysUserMarks() throws DBException {
		String login = "login";
		SysUserDto sysUserDto = createSysUserDto(login);
		SysUserDto createdSysUserDto = sysUserService.insertSysUser(sysUserDto, null);
		Assertions.assertEquals(2, createdSysUserDto.getMarks().size());
		
		MarkDto newMarkDto = new MarkDto();
		newMarkDto.setSubjectName(SUBJECT_NAMES[2]);
		newMarkDto.setSubjId(getSubjectId(2));
		
		createdSysUserDto.getMarks().add(newMarkDto);
		
		sysUserService.update(createdSysUserDto);
		SysUserDto updatedSysUserDto = sysUserService.getSysUserByLogin(login);
		
		// Assertions
		Assertions.assertEquals(3, updatedSysUserDto.getMarks().size());
	}
}
