package com.my.pet.spring.service;

import java.util.List;

import com.my.pet.spring.dto.AppointmentDto;
import com.my.pet.spring.dto.MarkDto;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;

public interface SysUserService {

	List<SysUserDto> getAllSysUsers() throws DBException;
	
	SysUserDto insertSysUser(SysUserDto dto, String ... details) throws DBException;
	
	SysUserDto setBlockStat(Integer id, Boolean flag) throws DBException;
	
	SysUserDto getSysUserByLogin(String name) throws DBException;
	
	SysUserDto update(SysUserDto dto) throws DBException;
	
	public List<AppointmentDto> getUserAppointmentStatus(SysUserDto dto) throws DBException;

	List<MarkDto> getUserMarksStatus(SysUserDto dto) throws DBException;
	
}
