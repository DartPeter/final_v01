package com.my.pet.spring.assembler;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.dto.SysUserDto;

public interface SysUserAssembler {
	SysUser assemble (SysUserDto dto);
	SysUserDto assemble (SysUser entity);
}
