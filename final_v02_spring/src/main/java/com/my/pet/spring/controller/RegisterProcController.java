package com.my.pet.spring.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.SysUserService;

import jdbc.DBManager;

@Controller
public class RegisterProcController {
	
	@Autowired
	private SysUserService sysUserService;
	
//	private static final String PRG_KEY = "createUserOK";
	
	private Logger logger = LoggerFactory.getLogger(RegisterProcController.class);

	@RequestMapping("/registerProc")
	public ModelAndView registerUser (HttpServletRequest request) {
		logger.info("Trying to register user");
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
        final String destPage = "register";
        
//        String requestPRGValue = request.getParameter("key_reg");
//        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
//        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
//        	logger.info("PRG Check triggered");
//        	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//            dispatcher.forward(request, response);
//            return;
//        }
//        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        SysUserDto su = new SysUserDto(request.getParameter("full_name"), 
                request.getParameter("user_type"), 
                request.getParameter("login"), 
                request.getParameter("password"), 
                request.getParameter("email"));
        
        String[] details = null;
        String city = request.getParameter("city");
        if (city != null) {
            details = new String[] {
                    city,
                    request.getParameter("region"),
                    request.getParameter("inst_name")
            };
        }
        try {
//        	DBManager.insertSysUser(su, details);
        	su = sysUserService.insertSysUser(su, details);
        } catch (DBException ex) {
        	logger.error(ex.getMessage());
        }
        String message;
        if (su.getId() != null && su.getId() > 0) {
            message = "messages.register.ok";
            logger.error("User was registered succesfully");
        } else {
            message = "messages.register.not.ok";
        }
        request.setAttribute("message", message);
        
        return new ModelAndView(destPage);
	}
}
