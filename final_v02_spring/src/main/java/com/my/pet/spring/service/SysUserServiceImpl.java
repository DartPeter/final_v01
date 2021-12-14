package com.my.pet.spring.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.assembler.SysUserAssembler;
import com.my.pet.spring.domain.Appointment;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.Subject;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.dto.AppointmentDto;
import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.dto.MarkDto;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;

import dao.FacultyDAO;
import dao.SubjectDAO;
import dao.SysUserDAO;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDAO sysUserDAO;
	@Autowired
	private FacultyDAO facultyDAO;
	@Autowired
	private SubjectDAO subjectDAO;
	@Autowired
	private SysUserAssembler sysUserAssembler;

	@Override
	@Transactional
	public List<SysUserDto> getAllSysUsers() throws DBException {
		List<SysUser> sysUsers = sysUserDAO.getAllSysUsers();
		List<SysUserDto> susdtos = sysUsers.stream().map(f -> sysUserAssembler.assemble(f)).collect(Collectors.toList());
		return susdtos;
	}

	@Override
	@Transactional
	public SysUserDto insertSysUser(SysUserDto dto, String... details) throws DBException {
		SysUser sysUser = sysUserAssembler.assemble(dto);
		sysUserDAO.insertSysUser(sysUser, details);
		SysUserDto result = sysUserAssembler.assemble(sysUser);
		return result;
	}

	@Override
	@Transactional
	public SysUserDto setBlockStat(Integer id, Boolean flag) throws DBException {
		SysUser entity = sysUserDAO.getSysUserById(id);
		entity.setBlocked(flag);
		return sysUserAssembler.assemble(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public SysUserDto getSysUserByLogin(String name) throws DBException {
		return sysUserAssembler.assemble(sysUserDAO.getSysUserByLogin(name));
	}
	
	@Override
	@Transactional
	public List<AppointmentDto> getUserAppointmentStatus(SysUserDto dto) throws DBException {
		List<AppointmentDto> active = dto.getAppointments();
		List<Faculty> faculties = facultyDAO.getAllFaculties();
		// TODO primitive solution cause no time
		List<AppointmentDto> all = faculties.stream().map(f -> {
			AppointmentDto appointmentDto = new AppointmentDto();
			appointmentDto.setFacultyId(f.getId());
			appointmentDto.setFacultyName(f.getName());
			return appointmentDto;
		}).collect(Collectors.toList());
		for (AppointmentDto f1 : all) {
			for (AppointmentDto f2 : active) {
				if (f1.getFacultyId().equals(f2.getFacultyId())) {
					f1.setId(f2.getId());
					break;
				}
			}
		}
		return all;
	}
	
	@Override
	@Transactional
	public List<MarkDto> getUserMarksStatus(SysUserDto dto) throws DBException {
		List<MarkDto> active = dto.getMarks();
		List<Subject> subjects = subjectDAO.getAllSubjects();
		// TODO primitive solution cause no time
		List<MarkDto> all = subjects.stream().map(s -> {
			MarkDto markDto = new MarkDto();
			markDto.setSubjId(s.getId());
			markDto.setSubjectName(s.getName());
			return markDto;
		}).collect(Collectors.toList());
		for (MarkDto m1 : all) {
			//TODO small workaround
			if(active.isEmpty()) {
				m1.setSysUserId(dto.getId());
				m1.setValue(0);
			} else 
			for (MarkDto m2 : active) {
				if (m1.getSubjId().equals(m2.getSubjId())) {
					m1.setId(m2.getId());
					m1.setSysUserId(m2.getSysUserId());
					m1.setValue(m2.getValue());
					break;
				}
			}
		}
		return all;
	}
	

	@Override
	@Transactional
	public SysUserDto update(SysUserDto dto) throws DBException {
		SysUser entity = sysUserDAO.getSysUserById(dto.getId());
		SysUser updatedEntity = sysUserAssembler.assemble(dto);
		performUpdate(entity, updatedEntity);
		return sysUserAssembler.assemble(entity);
	}

	private void performUpdate(SysUser persistentEntity, SysUser newEntity) {
		// skip fields updates cause they are fixed
		updateAppointments(persistentEntity, newEntity.getAppointments());
		//may be marks will be added
		updateMarks(persistentEntity, newEntity.getMarks());
	}

	private void updateAppointments(SysUser persistentEntity, List<Appointment> newAppointments) {
		Map<Integer, Appointment> stillExistentAppointment = newAppointments
				.stream()
				.filter(j -> Objects.nonNull(j.getId()))
				.collect(Collectors.toMap(Appointment::getId, Function.identity()));
		
		List<Appointment> appointmentsToAdd = newAppointments
				.stream()
				.filter(j -> Objects.isNull(j.getId()))
				.collect(Collectors.toList());
		
		Iterator<Appointment> persistentIterator = persistentEntity.getAppointments().iterator();
		while(persistentIterator.hasNext()) {
			Appointment persistentAppointment = persistentIterator.next();
			if (stillExistentAppointment.containsKey(persistentAppointment.getId())) {
				// empty
			} else {
				persistentIterator.remove();
				persistentAppointment.setSysUser(null);
			}
		}
		for (Appointment appointmentToAdd : appointmentsToAdd) {
			persistentEntity.getAppointments().add(appointmentToAdd);
			appointmentToAdd.setSysUser(persistentEntity);
		}
	}
	
	private void updateMarks(SysUser persistentEntity, List<Mark> newMarks) {
		Map<Integer, Mark> stillExistentMark = newMarks
				.stream()
				.filter(j -> Objects.nonNull(j.getId()))
				.collect(Collectors.toMap(Mark::getId, Function.identity()));
		
		List<Mark> marksToAdd = newMarks
				.stream()
				.filter(j -> Objects.isNull(j.getId()))
				.collect(Collectors.toList());
		
		Iterator<Mark> persistentIterator = persistentEntity.getMarks().iterator();
		while(persistentIterator.hasNext()) {
			Mark persistentMark = persistentIterator.next();
			if (stillExistentMark.containsKey(persistentMark.getId())) {
				Mark updatedMark = stillExistentMark.get(persistentMark.getId());
				updateMark(persistentMark, updatedMark);
			} else {
				persistentIterator.remove();
				persistentMark.setSysUser(null);
			}
		}
		//TODO workaround
//		int subjId = 1;
//		List<Subject> subjects = subjectDAO.getAllSubjects();
		for (Mark markToAdd : marksToAdd) {
			persistentEntity.getMarks().add(markToAdd);
			markToAdd.setSysUser(persistentEntity);
//			markToAdd.setValue(0);
//			markToAdd.setSubject(subjects.get(subjId++));
		}
		
	}

	private void updateMark(Mark persistentMark, Mark updatedMark) {
		persistentMark.setValue(updatedMark.getValue());
	}

}
