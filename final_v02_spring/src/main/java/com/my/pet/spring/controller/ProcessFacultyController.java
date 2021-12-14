package com.my.pet.spring.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.FacultyService;

import jdbc.DBManager;

@Controller
public class ProcessFacultyController {
	
	private static final String PRG_KEY = "facultyOperOK";
	
	private Logger logger = LoggerFactory.getLogger(ProcessFacultyController.class);
	
	@Autowired
	FacultyService facultyService;
	
	@RequestMapping(value = "/processFaculty")
	public ModelAndView processFaculty(@ModelAttribute ("faculty") FacultyDto faculty, HttpServletRequest request) {
		logger.info("Trying to process faculty");
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // prg check
//        String requestPRGValue = request.getParameter("key_aef");
//        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
//        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
//        	Logger.getLogger(Login.class.getName()).log(Level.INFO, "PRG Check triggered");
//        	RequestDispatcher dispatcher = request.getRequestDispatcher("add_edit_faculty.jsp");
//            dispatcher.forward(request, response);
//            return;
//        }
//        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        String errors = "";
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException nfe) {
        	//empty
        }
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            errors = "messages.aef.name.not.empty";
        }
        int budgetPlaces = -1;
        int totalPlaces = -1;
        try {
            budgetPlaces = Integer.parseInt(request.getParameter("budgetPlaces"));
        } catch (NumberFormatException nfe) {
            errors = "messages.aef.budget.number";
            logger.error("Budget Places must be number!", nfe);
        }
        try {
            totalPlaces = Integer.parseInt(request.getParameter("totalPlaces"));
        } catch (NumberFormatException nfe) {
            errors = "messages.aef.total.number";
            logger.error("Total Places must be number!", nfe);
        }
        if (totalPlaces < budgetPlaces) {
        	errors = "messages.total.must.be.greater";
        }
        //Faculty faculty = null;
        String mode = request.getParameter("mode");
        if (errors.isEmpty()) {
            faculty = new FacultyDto(id, name, budgetPlaces, totalPlaces);
            switch (mode) {
            case "new":
            	try {
//            		DBManager.insertFaculty(faculty);
            		faculty.setId(null);
            		faculty = facultyService.insertFaculty(faculty);
            		logger.info("Faculty was successfully inserted.");
//            		throw new DBException("", null);
            	} catch (DBException ex) {
            		errors = "messages.aef.cant.insert";
            		logger.error(ex.getMessage());
            	}
                if (faculty.getId() == null || faculty.getId() == -1) {
                    errors = "messages.aef.wrong";
                }
                break;
            case "edit":
            	try {
            		
//            		DBManager.updateFaculty(faculty);
            		faculty = facultyService.updateFaculty(faculty);
            		
            		logger.info("Faculty was successfully updated.");
//            		throw new DBException("", null);
            	} catch (DBException ex) {
            		errors = "messages.aef.cant.update";
            		logger.error(ex.getMessage());
            	}
                break;
            case "del":
            	try {
//            		DBManager.removeFaculty(faculty);
            		facultyService.deleteFaculty(faculty.getId());
            		logger.info("Faculty was successfully removed.");
            		throw new DBException("", null);
            	} catch (DBException ex) {
            		errors = "messages.aef.cant.remove";
            		logger.error(errors, ex);
            	}
                break;
            default:
                break;
            }
            if (errors.isEmpty()) {
            	if ("new".equals(mode)) {
            		errors = "messages.aef.add.succes";
            	}
            	if ("edit".equals(mode)) {
            		errors = "messages.aef.edit.succes";
            	}
            }
        }
        
        request.setAttribute("errors", errors);
        request.setAttribute("faculty", faculty);
        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("del".equals(mode) ? "view_faculties.jsp" : "add_edit_faculty.jsp");
//        dispatcher.forward(request, response);
        
        return new ModelAndView("del".equals(mode) ? "view_faculties" : "add_edit_faculty");
	}
	
}
