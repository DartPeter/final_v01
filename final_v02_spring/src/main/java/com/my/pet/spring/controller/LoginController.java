package com.my.pet.spring.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import jdbc.DBManager;

@Controller
public class LoginController {
	
	private static final String HOME_USER = "home_user";
	private static final String HOME_ADMIN = "home_admin";
	private static final String ADMIN = "admin";
	
	@Autowired 
	AuthenticationManager authManager;

	@PostMapping (value = "/loginProc")
	public ModelAndView login(HttpServletRequest request) {
		
		LoggerFactory.getLogger(LoginController.class).info("Trying to login");
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerFactory.getLogger(LoginController.class).error(e.getMessage());
		}
        
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        String destPage = "login";
        String message = "";
        
        // Without this authentication doesn't work
        try {
	        UsernamePasswordAuthenticationToken authReq
		      = new UsernamePasswordAuthenticationToken(login, password);
		    Authentication auth = authManager.authenticate(authReq);
		    SecurityContext sc = SecurityContextHolder.getContext();
		    sc.setAuthentication(auth);
		    HttpSession session1 = request.getSession(true);
		    session1.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
        } catch (AuthenticationException e) {
        	LoggerFactory.getLogger(LoginController.class).error(e.getMessage());
		}
        
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            message = "messages.login.invalid.login.password";
        } else {
        	SysUser su = null;
        	try {
        		su = DBManager.getSysUserByLogin(login);
        	} catch (DBException ex) {
        		LoggerFactory.getLogger(LoginController.class).info(ex.getMessage());
        	}
            if (su == null) {
                message = "messages.login.invalid.user";
                LoggerFactory.getLogger(LoginController.class).info("Invalid user.");
            } else if (su.isBlocked()) {
                message = "messages.login.user.is.blocked";
                LoggerFactory.getLogger(LoginController.class).info("Blocked user tried to login.");
            } else if (!password.equals(su.getPass())) {
            	message = "messages.login.invalid.login.password";
            	LoggerFactory.getLogger(LoginController.class).info("Wrong password was entered.");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", su);
                session.setAttribute("userId", su.getId());
                if (su.getUserType().equals(ADMIN)) {
                    destPage = HOME_ADMIN;
                } else {
                    destPage = HOME_USER;
                }
                LoggerFactory.getLogger(LoginController.class).info("User successfully logged.");
            }
        }
        
        request.setAttribute("message", message);
        return new ModelAndView(destPage);
		
	}
	
}
