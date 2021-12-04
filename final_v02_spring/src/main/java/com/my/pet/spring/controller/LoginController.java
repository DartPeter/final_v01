package com.my.pet.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import jdbc.DBManager;

@Controller
public class LoginController {
	
	private static final String HOME_USER = "home_user";
	private static final String HOME_ADMIN = "home_admin";
 
	@GetMapping(value = "/loginProc")
	public ModelAndView login(HttpServletRequest request) throws DBException {
		
		SysUser su = DBManager.getSysUserByLogin((String)SecurityContextHolder
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
