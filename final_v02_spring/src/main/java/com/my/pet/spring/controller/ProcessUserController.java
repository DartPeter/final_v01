package com.my.pet.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.SysUserService;

@Controller
public class ProcessUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	private Logger logger = LoggerFactory.getLogger(ProcessUserController.class);

	@RequestMapping("/processUser")
	public ModelAndView processUser (HttpServletRequest request) {
		logger.info("Trying to process user");
        String method = request.getParameter("method");
        boolean flag;
        if ("block".equals(method)) {
            flag = true;
        } else {
            flag = false;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        try {
        	sysUserService.setBlockStat(id, flag);
        }  catch (DBException ex) {
        	logger.error("Can't (un)block user", ex);
        }
        return new ModelAndView("manage_users");
	}
}
