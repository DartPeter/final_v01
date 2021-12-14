package jdbc;

import dao.ReportDAO;
import dao.SysUserDAO;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author peter
 * Created to simplify woork with db
 *
 */
public class DBManager {
	
	private static DAOFactory daoFactory = DAOFactory.getDAOFactory();
	private static SysUserDAO sysUserDAO = daoFactory.getSysUserDAO();
	private static ReportDAO reportDAO = daoFactory.getReportDAO();
	
	private DBManager() {
		// hide
	}
	
	public static void setCertificate(SysUser sysUser) throws DBException {
		sysUserDAO.setCertificate(sysUser);
	}
	
	public static String getCertificate(SysUser sysUser) throws DBException {
		return sysUserDAO.getCertificate(sysUser);
	}
	
	public static Map<Integer, String> reportIdToMail(String range) throws DBException {
		return reportDAO.reportIdToMail(range);
	}

	public static List<List<String>> generateReport(Faculty f) throws DBException {
		return reportDAO.generateReport(f);
	}	

}
