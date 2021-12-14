package com.my.pet.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.SysUserService;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserDto sysUser = null;
		try {
			sysUser = sysUserService.getSysUserByLogin(username);
		} catch (DBException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Could not find user");
		}
		if (sysUser == null) return null;
		return new SysUserDetails(sysUser);
	}

}
