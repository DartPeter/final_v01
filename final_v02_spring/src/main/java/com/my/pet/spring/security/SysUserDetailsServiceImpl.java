package com.my.pet.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.SysUserService;
import com.my.pet.spring.service.SysUserServiceImpl;

import jdbc.DBManager;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private SysUserService sysUserService;// = new SysUserServiceImpl();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserDto sysUser = null;
		try {
//			sysUser = DBManager.getSysUserByLogin(username);
//			SysUserDto dto = sysUserService.getSysUserByLogin(username);
			sysUser = sysUserService.getSysUserByLogin(username);
//			boolean op = 1 + 1 == 2;
		} catch (DBException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Could not find user");
		}
		if (sysUser == null) return null;
		return new SysUserDetails(sysUser);
	}

}
