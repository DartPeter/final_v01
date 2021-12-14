package com.my.pet.spring.repository;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.exception.DBException;
import dao.FacultyDAO; 

@Repository
public class FacultyDaoImpl implements FacultyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Faculty getFacultyByName(String name) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Faculty getFacultyById(Integer facultyId) throws DBException {
		Session session = sessionFactory.getCurrentSession();
		Faculty faculty = session.get(Faculty.class, facultyId);
		return Objects.requireNonNull(faculty, "Tourist not found by id: " + facultyId);
	}

	@Override
	@Transactional
	public Faculty insertFaculty(Faculty faculty) throws DBException {
		sessionFactory.getCurrentSession().save(faculty);
		return faculty;
	}

	@Override
	public void deleteFaculty(Integer id) throws DBException {
		Faculty faculty = getFacultyById(id);
		sessionFactory.getCurrentSession().delete(faculty);
	}

	@Override
	@Transactional
	public List<Faculty> getAllFaculties() throws DBException {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Faculty ORDER BY id", Faculty.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Faculty> getByIds(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		List<Faculty> result = session.byMultipleIds(Faculty.class).multiLoad(ids);
		return result;
	}

}
