package dao;

import java.util.List;

import com.my.pet.spring.domain.Subject;

public interface SubjectDAO {
	
	Subject create (String name);
	
	List<Subject> getAllSubjects();

}
