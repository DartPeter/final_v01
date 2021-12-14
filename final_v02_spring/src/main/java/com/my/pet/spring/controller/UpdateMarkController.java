package com.my.pet.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.dto.MarkDto;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.SysUserService;

import jdbc.DBManager;

@Controller
public class UpdateMarkController {
	
	private Logger logger = LoggerFactory.getLogger(UpdateMarkController.class);
	
	@Autowired
	private SysUserService sysUserService;
	
	private static final String PRG_KEY = "updateMarksOK";
	
	@PostMapping(value = "updateMark")
	public ModelAndView updateMarks(HttpServletRequest request) {
		
		logger.info("Trying to update mark");
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
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
        
        List<MarkDto> lm = new ArrayList<>();
        SysUserDto su = (SysUserDto) (request.getSession().getAttribute("user"));
        
        for (String s : request.getParameterMap().keySet()) {
//        	if(s.equals("id")) {
//        		lm.add(new MarkDto());
//        	} else 
    		if (s.startsWith("id")) {
//	            int subjId = Integer.parseInt(s.substring(2));
	            int id =  Integer.parseInt(s.substring(2));
	            int value = Integer.parseInt(request.getParameterMap().get(s)[0]);
	            MarkDto m = new MarkDto();
	            m.setId(id);
	            m.setSysUserId(su.getId());
//	            m.setSubjId(subjId);
	            m.setValue(value);
	            lm.add(m);
        	}
        }
        // TODO little workaround for first time new user
        if (lm.size() != 1) {
        	su.setMarks(lm);
        } else {
//        	su.setMarks((List<MarkDto>) (request.getSession().getAttribute("list")));
        	try {
				su.setMarks(sysUserService.getUserMarksStatus(su));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        try {
//        	DBManager.updateUserMarks(lm);
        	su = sysUserService.update(su);
        	su = sysUserService.getSysUserByLogin(su.getLogin());
        } catch (DBException ex) {
        	logger.error("Can't update mark", ex);
        }
        request.getSession().setAttribute("user", su);
        
//        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//        dispatcher.forward(request, response);
		
		return new ModelAndView(destPage);
	}

}
