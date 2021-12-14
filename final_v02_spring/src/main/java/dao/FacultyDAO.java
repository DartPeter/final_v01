package dao;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.exception.DBException;
import java.util.List;

/**
 * 
 * @author peter
 * This is common interface for faculty Dao
 *
 */
public interface FacultyDAO {

    Faculty getFacultyByName(String name) throws DBException;
    
    Faculty getFacultyById(Integer facultyId) throws DBException;
    
    Faculty insertFaculty(Faculty faculty) throws DBException;
    
    void deleteFaculty(Integer id) throws DBException;
    
    List<Faculty> getAllFaculties() throws DBException;
    
    default List<Faculty> getByIds(List<Integer> ids) {
    	return null;
    }

}
