package dao;

import java.util.List;
import java.util.Map;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.exception.DBException;

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
