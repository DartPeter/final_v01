package com.my.pet.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.Appointment;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import jdbc.DBManager;

@Controller
public class MyController {
	
	@GetMapping(value = "/qwe")
	public ModelAndView qwe() {
		return new ModelAndView("index");
	}
	
	@GetMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping(value = "/register")
	public ModelAndView registerTest() {
		return new ModelAndView("register");
	}
	
	@GetMapping(value = "/home_admin")
	public ModelAndView homeAdmin() {
		return new ModelAndView("home_admin");
	}
	
	@GetMapping(value = "/view_faculties")
	public ModelAndView viewFaculties(HttpServletRequest request) {
		final int PAGE_SIZE = 10;
	    String spage = request.getParameter("spage");
	    int currentPage = 1;
	    try {
	    	currentPage = Integer.parseInt(spage);
	    } catch(NumberFormatException nfe) {
	    	// do nothing
	    }
	    List<Faculty> list = new ArrayList<>();
		try {
			list = DBManager.getAllFaculties();
		} catch (DBException e) {
			// do nothing
		}
		// sort staff
	    String sortType = request.getParameter("sortType");
		if(sortType == null) {
			sortType = "";
		}
	    switch (sortType) {
		    case "name" :
		    	list.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
		    	break;
		    case "budget" :
		    	list.sort((f1, f2) -> Integer.compare(f1.getBudgetPlaces(), f2.getBudgetPlaces()));
		    	break;
		    case "total" :
		    	list.sort((f1, f2) -> Integer.compare(f1.getTotalPlaces(), f2.getTotalPlaces()));
		    	break;
		   	default :
		   		break;	
	    }
	    List<Faculty> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
	    request.setAttribute("list", list2);
	    int size = list.size();
	    int pagesTotal = size / PAGE_SIZE + (size % PAGE_SIZE == 0 ? 0 : 1);
	    int first = 1;
	    int last = pagesTotal;
	    request.setAttribute("first", first);
	    request.setAttribute("last", last);
	    request.setAttribute("sortType", sortType);
	    request.setAttribute("spage", spage);
		return new ModelAndView("view_faculties");
	}
	
	@GetMapping(value = "/manage_users")
	public ModelAndView manageUsers(HttpServletRequest request) {
		final int PAGE_SIZE = 10;
	    String spage = request.getParameter("page");
	    int currentPage = 1;
	    try {
	    	currentPage = Integer.parseInt(spage);
	    } catch(NumberFormatException nfe) {
	    	// do nothing
	    }
	    request.setAttribute("page", currentPage);
	    List<SysUser> list = new ArrayList<>();
		try {
			list = DBManager.getAllSysUsers();
		} catch (DBException e) {
			// do nothing
		}
	    List<SysUser> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
	    request.setAttribute("list", list2);
	    int size = list.size();
	    int pagesTotal = size / PAGE_SIZE + (size % PAGE_SIZE == 0 ? 0 : 1);
	    int first = 1;
	    int last = pagesTotal;
	    request.setAttribute("first", first);
	    request.setAttribute("last", last);
		return new ModelAndView("manage_users");
	}
	
	@GetMapping(value = "/add_edit_faculty")
	public ModelAndView addEditFaculty() {
		return new ModelAndView("add_edit_faculty");
	}
	
	@GetMapping(value = "/generate_report")
	public ModelAndView generateReport() {
		return new ModelAndView("generate_report");
	}
	
	@GetMapping(value = "/home_user")
	public ModelAndView homeUser() {
		return new ModelAndView("home_user");
	}
	
	@GetMapping(value = "/view_marks")
	public ModelAndView viewMarks(HttpServletRequest request) {
		SysUser su = (SysUser)request.getSession().getAttribute("user");
	    List<Mark> list = new ArrayList<>();
		try {
			if(su != null) {
				list = DBManager.getUserMarks(su);
			}
		} catch (DBException e) {
			LoggerFactory.getLogger(MyController.class).error("can't retrive marks");
		}
	    request.setAttribute("list", list);
		return new ModelAndView("view_marks");
	}
	
	@GetMapping(value = "/manage_appointments")
	public ModelAndView manageAppointments(HttpServletRequest request) {
		final int PAGE_SIZE = 10;
	    String spage = request.getParameter("page");
	    int currentPage = 1;
	    try {
	    	currentPage = Integer.parseInt(spage);
	    } catch(NumberFormatException nfe) {
	    	// do nothing
	    }
	    request.setAttribute("page", currentPage);
		request.getSession().setAttribute("lapp", Integer.toString(currentPage));
	    List<Appointment> list = new ArrayList<>();
		try {
			SysUser sysUser = (SysUser)(request.getSession().getAttribute("user"));
			if (sysUser != null) {
				list = DBManager.getUserAppointments(sysUser);
			}
		} catch (DBException e) {
			// do nothing
		}
	    List<Appointment> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
	    request.setAttribute("list", list2);
	    int size = list.size();
	    int pagesTotal = size / PAGE_SIZE + (size % PAGE_SIZE == 0 ? 0 : 1);
	    int first = 1;
	    int last = pagesTotal;
	    request.setAttribute("first", first);
	    request.setAttribute("last", last);
		return new ModelAndView("manage_appointments");
	}
	
}
