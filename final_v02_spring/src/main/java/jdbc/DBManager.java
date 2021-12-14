package jdbc;

import dao.AppointmentDAO;
import dao.FacultyDAO;
import dao.MarkDAO;
import dao.ReportDAO;
import dao.SysUserDAO;
import com.my.pet.spring.domain.Appointment;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.Mark;
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
//	private static FacultyDAO facultyDAO = daoFactory.getFacultyDAO();
//	private static AppointmentDAO appointmentDAO = daoFactory.getAppointmentDAO();
	private static MarkDAO markDAO = daoFactory.getMarkDAO();
	private static ReportDAO reportDAO = daoFactory.getReportDAO();
	
	private DBManager() {
		// hide
	}
	
//	public static SysUser getSysUserByLogin(String name) throws DBException {
//		return sysUserDAO.getSysUserByLogin(name);
//	}
	
//	public static void insertSysUser(SysUser sysUser, String... details) throws DBException {
//		sysUserDAO.insertSysUser(sysUser, details);
//	}
	
//	public static void updateSysUser(SysUser sysUser) throws DBException {
//		sysUserDAO.updateSysUser(sysUser);
//	}
	
//	public static void removeSysUser(SysUser sysUser) throws DBException {
//		sysUserDAO.removeSysUser(sysUser);
//	}
	
//	public static void setBlockStat(SysUser sysUser, boolean flag) throws DBException {
//		sysUserDAO.setBlockStat(sysUser, flag);
//	}
	
	public static void setCertificate(SysUser sysUser) throws DBException {
		sysUserDAO.setCertificate(sysUser);
	}
	
	public static String getCertificate(SysUser sysUser) throws DBException {
		return sysUserDAO.getCertificate(sysUser);
	}
	
//	public static List<SysUser> getAllSysUsers() throws DBException {
//		return sysUserDAO.getAllSysUsers();
//	}
	
//	public static Faculty getFacultyByName(String name) throws DBException {
//		return facultyDAO.getFacultyByName(name);
//	}

//	public static void insertFaculty(Faculty faculty) throws DBException {
//		facultyDAO.insertFaculty(faculty);
//	}

//	public static void updateFaculty(Faculty faculty) throws DBException {
////		facultyDAO.updateFaculty(faculty);
//	}

//	public static void removeFaculty(Faculty faculty) throws DBException {
//	public static void deleteFaculty(Integer id) throws DBException {
////		facultyDAO.removeFaculty(id);
//		facultyDAO.deleteFaculty(id);
//	}

//	public static List<Faculty> getAllFaculties() throws DBException {
//		return facultyDAO.getAllFaculties();
//	}

//	public static List<Appointment> getUserAppointments(SysUser su) throws DBException {
//		return appointmentDAO.getUserAppointments(su);
//	}

//	public static void insertUserAppointment(SysUser su, Faculty f) throws DBException {
//		appointmentDAO.insertUserAppointment(su, f);
//	}

//	public static void removeAppointment(Appointment a) throws DBException {
//		appointmentDAO.removeAppointment(a);
//	}

//	public static List<Mark> getUserMarks(SysUser su) throws DBException {
//		return markDAO.getUserMarks(su);
//	}

//	public static void updateUserMarks(List<Mark> lm) throws DBException {
//		markDAO.updateUserMarks(lm);
//	}
	
	public static Map<Integer, String> reportIdToMail(String range) throws DBException {
		return reportDAO.reportIdToMail(range);
	}

	public static List<List<String>> generateReport(Faculty f) throws DBException {
		return reportDAO.generateReport(f);
	}	

}
