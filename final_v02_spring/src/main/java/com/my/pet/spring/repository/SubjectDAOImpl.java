package com.my.pet.spring.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.domain.Subject;

import dao.SubjectDAO;

@Repository
public class SubjectDAOImpl implements SubjectDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Subject create(String name) {
		Subject subject = new Subject();
		subject.setName(name);
		sessionFactory.getCurrentSession().save(subject);
		return subject;
	}

	@Cacheable(value = "subjectsCache")
	@Override
	public List<Subject> getAllSubjects() {
		LoggerFactory.getLogger(SubjectDAOImpl.class).info("TST11 Retriving all subjects from db");
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Subject ORDER BY id", Subject.class).getResultList();
	}

}
