package com.my.pet.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import jdbc.DBManager;

@Controller
public class UpdateMarkController {
	
	private static final String PRG_KEY = "updateMarksOK";
	
	@PostMapping(value = "updateMark")
	public ModelAndView updateMarks(HttpServletRequest request) {
		
		Logger.getLogger(UpdateMarkController.class.getName()).log(Level.INFO, "Trying to update mark");
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			Logger.getLogger(UpdateMarkController.class.getName()).log(Level.ERROR, e.getMessage());
		}
        final String destPage = "view_marks";//".jsp";
        
//        String requestPRGValue = request.getParameter("key_vm");
//        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
//        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
//        	Logger.getLogger(Login.class.getName()).log(Level.INFO, "PRG Check triggered");
//        	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//            dispatcher.forward(request, response);
//            return;
//        }
//        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        List<Mark> lm = new ArrayList<>();
        SysUser su = (SysUser) (request.getSession().getAttribute("user"));
        su.getId();
        for (String s : request.getParameterMap().keySet()) {
        	if (s.startsWith("id")) {
	            int subjId = Integer.parseInt(s.substring(2));
	            int value = Integer.parseInt(request.getParameterMap().get(s)[0]);
	            Mark m = new Mark();
	            m.setUserId(su.getId());
	            m.setSubjId(subjId);
	            m.setValue(value);
	            lm.add(m);
        	}
        }
        try {
        	DBManager.updateUserMarks(lm);
        } catch (DBException ex) {
        	Logger.getLogger(UpdateMarkController.class.getName()).log(Level.ERROR, "Can't update mark", ex);
        }
        
//        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//        dispatcher.forward(request, response);
		
		return new ModelAndView(destPage);
	}

}
