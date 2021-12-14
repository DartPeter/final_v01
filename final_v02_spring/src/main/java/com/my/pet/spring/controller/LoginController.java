package com.my.pet.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.SysUserService;

@Controller
public class LoginController {
	
	@Autowired
	SysUserService sysUserService;
	
	private static final String HOME_USER = "home_user";
	private static final String HOME_ADMIN = "home_admin";
 
	@GetMapping(value = "/loginProc")
	public ModelAndView login(HttpServletRequest request) throws DBException {
		
		SysUserDto su = 
		sysUserService.getSysUserByLogin((String)SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		HttpSession session = request.getSession();
        session.setAttribute("user", su);
        session.setAttribute("userId", su.getId());
        
        String redirectView = SecurityContextHolder.getContext()
    	      .getAuthentication()
    	      .getAuthorities()
    	      .iterator()
    	      .next()
    	      .getAuthority()
    	      .equals("ROLE_ADMIN") ? HOME_ADMIN : HOME_USER;
    	return new ModelAndView(redirectView);
		
	}
	
}
