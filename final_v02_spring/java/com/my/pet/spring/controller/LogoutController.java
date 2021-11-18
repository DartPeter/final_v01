package com.my.pet.spring.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	
	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		Logger.getLogger(LogoutController.class.getName()).log(Level.INFO, "Trying to logout");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }

        return new ModelAndView("login");
	}

}
