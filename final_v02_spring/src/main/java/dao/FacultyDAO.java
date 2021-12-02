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
    
    void insertFaculty(Faculty faculty) throws DBException;
    
    void updateFaculty(Faculty faculty) throws DBException;
    
    void removeFaculty(Faculty faculty) throws DBException;
    
    List<Faculty> getAllFaculties() throws DBException;

}
