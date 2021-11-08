package com.my.pet.spring.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.homework.hw1.Service;

@Controller
public class HomeWork1Controller {
	
	@Autowired
	Service service;
	
	@GetMapping(value = "/hw1t1")
	public ModelAndView execH1T1() {
		try {
			LoggerFactory.getLogger(Service.class).atInfo().log("Start in HomeWork1Controller class handling");
			service.serve();
			LoggerFactory.getLogger(Service.class).atInfo().log("End in HomeWork1Controller class handling");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin(methods = RequestMethod.GET)
	@GetMapping(value = "/hw1t2p1")
	public ModelAndView execH1T2P1() {
		throw new NullPointerException();
	}
	

}
