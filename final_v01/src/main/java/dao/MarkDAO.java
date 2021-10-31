package dao;

import java.util.List;
import entity.Mark;
import entity.SysUser;
import jdbc.DBException;

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
