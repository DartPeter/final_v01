package com.my.pet.spring.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public List<Subject> getAllSubjects() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Subject ORDER BY id", Subject.class).getResultList();
	}

}
