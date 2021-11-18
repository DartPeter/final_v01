package com.my.pet.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.domain.Faculty;

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
	
//	@PostMapping(value = "/login1")
//	public ModelAndView login1() {
//		return new ModelAndView("home_admin");
//	}
	
	@GetMapping(value = "/home_admin")
	public ModelAndView homeAdmin() {
		return new ModelAndView("home_admin");
	}
	
	@GetMapping(value = "/view_faculties")
	public ModelAndView viewFaculties() {
		return new ModelAndView("view_faculties");
	}
	
	@GetMapping(value = "/manage_users")
	public ModelAndView manageUsers() {
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
	public ModelAndView viewMarks() {
		return new ModelAndView("view_marks");
	}
	
	@GetMapping(value = "/manage_appointments")
	public ModelAndView manageAppointments() {
		return new ModelAndView("manage_appointments");
	}
	
	/* EXPERIMENTAL
	@GetMapping(value = "/register_WORKED!!!", params = {"type"})
	public ModelAndView registerTest(
			@ModelAttribute("type") String type, 
			@ModelAttribute("param.type") String ptype, 
			Model model) {
		model.addAttribute("type", type);
		model.addAttribute("param.type", type);
		System.out.println(type);
		System.out.println(ptype);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		mav.addObject("type", type);
		mav.addObject("param.type", type);
		mav.addObject("qwe", "qqqqq");
		return mav;
	}
	@GetMapping(value = "/register22")
	public ModelAndView registerTest22() {
		return new ModelAndView("register");
	}
	@GetMapping(value = "/register.jsp")
	public ModelAndView registerTest4() {
		return new ModelAndView("register");
	}
	*/
	
}
