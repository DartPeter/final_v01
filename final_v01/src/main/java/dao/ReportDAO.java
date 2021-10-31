package dao;

import java.util.List;
import java.util.Map;
import entity.Faculty;
import jdbc.DBException;

/**
 * 
 * @author peter
 * This is common interface for report Dao
 *
 */
public interface ReportDAO {
    
	List<List<String>> generateReport(Faculty f)  throws DBException;
	
	Map<Integer, String> reportIdToMail(String range) throws DBException;

}
