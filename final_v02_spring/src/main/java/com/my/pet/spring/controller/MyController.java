package com.my.pet.spring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.pet.spring.dto.AppointmentDto;
import com.my.pet.spring.dto.FacultyDto;
import com.my.pet.spring.dto.MarkDto;
import com.my.pet.spring.dto.SysUserDto;
import com.my.pet.spring.exception.DBException;
import com.my.pet.spring.service.FacultyService;
import com.my.pet.spring.service.SysUserService;

@Controller
public class MyController {
	
	private Logger logger = LoggerFactory.getLogger(MyController.class);

	@Autowired
	FacultyService facultyService;
	@Autowired
	SysUserService sysUserService;
	
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
	    List<FacultyDto> list = new ArrayList<>();
		try {
			list = facultyService.getAllFaculties();
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
	    List<FacultyDto> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
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
	    List<SysUserDto> list = new ArrayList<>();
		try {
			list = sysUserService.getAllSysUsers();
		} catch (DBException e) {
			// do nothing
		}
	    List<SysUserDto> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
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
		SysUserDto su = (SysUserDto)request.getSession().getAttribute("user");
	    List<MarkDto> list = new ArrayList<>();
		try {
			if(su != null) {
				list = sysUserService.getUserMarksStatus(su);
				//TODO workaround for new user first time
				if(su.getMarks().isEmpty()) {
					su.setMarks(list);
					sysUserService.update(su);
					su = sysUserService.getSysUserByLogin(su.getLogin());
					request.getSession().setAttribute("user", su);
					list = sysUserService.getUserMarksStatus(su);
				} else {
					list = sysUserService.getUserMarksStatus(su);
				}
			}
		} catch (DBException e) {
			logger.error("can't retrive marks");
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
	    List<AppointmentDto> list = new ArrayList<>();
		try {
			SysUserDto sysUser = (SysUserDto)(request.getSession().getAttribute("user"));
			if (sysUser != null) {
				list = sysUserService.getUserAppointmentStatus(sysUser);
			}
		} catch (DBException e) {
			// do nothing
		}
	    List<AppointmentDto> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
	    request.setAttribute("list", list2);
	    int size = list.size();
	    int pagesTotal = size / PAGE_SIZE + (size % PAGE_SIZE == 0 ? 0 : 1);
	    int first = 1;
	    int last = pagesTotal;
	    request.setAttribute("first", first);
	    request.setAttribute("last", last);
		return new ModelAndView("manage_appointments");
	}
	
	@GetMapping(value = "/updateAppointment")
	public ModelAndView updateAppointment(HttpServletRequest request) {
		logger.info("Trying to update appointment");
        boolean isApply = Boolean.parseBoolean(request.getParameter("apply"));
        SysUserDto su = (SysUserDto) (request.getSession().getAttribute("user"));
        if (isApply) {
            int fid = Integer.parseInt(request.getParameter("fid"));
            FacultyDto f = new FacultyDto();
            f.setId(fid);
        	AppointmentDto a = new AppointmentDto();
        	a.setFacultyId(f.getId());
        	a.setFacultyName(f.getName());
        	su.getAppointments().add(a);
        } else {
            int aid = Integer.parseInt(request.getParameter("aid"));
        	Iterator<AppointmentDto> iterator = su.getAppointments().iterator();
        	while(iterator.hasNext()) {
        		AppointmentDto dto = iterator.next();
        		if(dto.getId().equals(aid)) {
        			iterator.remove();
        		}
        	}
        }
    	try {
			su = sysUserService.update(su);
			su = sysUserService.getSysUserByLogin(su.getLogin());
			
		} catch (DBException e) {
			logger.error(e.getMessage());
		}
    	request.getSession().setAttribute("user", su);
        
        return new ModelAndView("manage_appointments");
	}
	
}
