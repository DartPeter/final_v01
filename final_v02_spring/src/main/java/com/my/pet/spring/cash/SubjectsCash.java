package com.my.pet.spring.cash;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.my.pet.spring.domain.Subject;

import dao.SubjectDAO;

@Component
public class SubjectsCash {
	
	@Autowired
	SubjectDAO subjectDAO;
	
	@Cacheable(value = "subjectsCache")//key = "#name"
	public List<Subject> getSubjects() {
		LoggerFactory.getLogger(SubjectsCash.class).info("Retriving all subjects from db");
		return subjectDAO.getAllSubjects();
	}

}