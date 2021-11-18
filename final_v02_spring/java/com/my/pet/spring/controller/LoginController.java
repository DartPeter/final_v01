package com.my.pet.spring.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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

	@PostMapping (value = "login1")
	public ModelAndView login(HttpServletRequest request) {
		
		Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "Trying to login");
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			Logger.getLogger(LoginController.class.getName()).log(Level.ERROR, e.getMessage());
		}
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String destPage = "login";
        String message = "";
        
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            message = "messages.login.invalid.login.password";
        } else {
        	SysUser su = null;
        	try {
        		su = DBManager.getSysUserByLogin(login);
        	} catch (DBException ex) {
        		Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "Error happened.", ex);
        	}
            if (su == null) {
                message = "messages.login.invalid.user";
                Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "Invalid user.");
            } else if (su.isBlocked()) {
                message = "messages.login.user.is.blocked";
                Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "Blocked user tried to login.");
            } else if (!password.equals(su.getPass())) {
            	message = "messages.login.invalid.login.password";
            	Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "Wrong password was entered.");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", su);
                session.setAttribute("userId", su.getId());
                if (su.getUserType().equals(ADMIN)) {
                    destPage = HOME_ADMIN;
                } else {
                    destPage = HOME_USER;
                }
                Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "User successfully logged.");
            }
        }
        
        request.setAttribute("message", message);
        return new ModelAndView(destPage);
		
	}
	
}
