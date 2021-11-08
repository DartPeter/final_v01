package com.my.pet.spring.controller;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

@Controller
public class RegisterProcController {

	@RequestMapping("/registerProc")
	public ModelAndView doPost(
			@RequestParam String key_reg,
			// ????? session
			@RequestParam String user_type,
			@RequestParam String full_name,
			@RequestParam String login,
			@RequestParam String password,
			@RequestParam String email,
			@RequestParam String city,
			@RequestParam String region,
			@RequestParam String inst_name,
			HttpServletResponse response) {//HttpServletRequest request
		Logger.getLogger(RegisterProcController.class.getName()).log(Level.INFO, "Trying to register user");
//        request.setCharacterEncoding("UTF-8");
        
        final String destPage = "register.jsp";
        String requestPRGValue = key_reg;
        //request.getParameter("key_reg");
//        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
//        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
//        	Logger.getLogger(RegisterProcController.class.getName()).log(Level.INFO, "PRG Check triggered");
//        	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//            dispatcher.forward(request, response);
//            return;
//        }
//        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        SysUser su = new SysUser(
        		/*request.getParameter("full_name"), 
                request.getParameter("user_type"), 
                request.getParameter("login"), 
                request.getParameter("password"), 
                request.getParameter("email")
                */
        		full_name, user_type, login, password, email
                );
                
        
        String[] details = null;
        String city0 = city;
        //request.getParameter("city");
        if (city0 != null) {
            details = new String[] {
                    city0,
                    //request.getParameter("region"),
                    //request.getParameter("inst_name")
                    region,
                    inst_name
            };
        }
//        try {
//        	DBManager.insertSysUser(su, details);
//        } catch (DBException ex) {
//        	Logger.getLogger(RegisterProcController.class.getName()).log(Level.ERROR, ex.getMessage());
//        }
        String message;
        if (su.getId() > 0) {
            message = "messages.register.ok";
            Logger.getLogger(RegisterProcController.class.getName()).log(Level.ERROR, "User was registered succesfully");
        } else {
            message = "messages.register.not.ok";
        }
        //request.setAttribute("message", message);
         
//        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//        dispatcher.forward(request, response);
        return new ModelAndView("index");
	}
	
//	@PostMapping(value = "registerProc")
//	public ModelAndView register(Map<String, Object> model, BindingResult result) {
//		if (result.hasErrors()) {
//	        return new ModelAndView("error");
//	    }
//		return new ModelAndView("index");
//	}
}
