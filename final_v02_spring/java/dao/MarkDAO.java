package dao;

import java.util.List;
import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

/**
 * 
 * @author peter
 * This is common interface for mark Dao
 *
 */
public interface MarkDAO {
    
    List<Mark> getUserMarks(SysUser su) throws DBException;
    
    void updateUserMarks(List<Mark> ln) throws DBException;

}
