package com.my.pet.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import jdbc.DBManager;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = null;
		try {
			sysUser = DBManager.getSysUserByLogin(username);
		} catch (DBException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Could not find user");
		}
		return new SysUserDetails(sysUser);
	}

}
