package com.my.pet.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.my.pet.spring.dto.SysUserDto;

public class SysUserDetails implements UserDetails {
	
	private SysUserDto sysUser;
	
	public SysUserDetails(SysUserDto sysUser) {
		this.sysUser = sysUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(
				sysUser.getUserType().equals("admin") ? "ROLE_ADMIN" : "ROLE_USER")); //"ADMIN" : "USER" //"ROLE_ADMIN" : "ROLE_USER"
		return authorities;
	}

	@Override
	public String getPassword() {
		return sysUser.getPass();
	}

	@Override
	public String getUsername() {
		return sysUser.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !sysUser.isBlocked();
	}

}
