package jdbc;

import dao.AppointmentDAO;
import dao.FacultyDAO;
import dao.MarkDAO;
import dao.ReportDAO;
import dao.SysUserDAO;
import java.util.ResourceBundle;

/**
 * 
 * @author peter
 * Abstract Factory for Dao
 *
 */
public abstract class DAOFactory {
	
	public abstract SysUserDAO getSysUserDAO();
	
	public abstract FacultyDAO getFacultyDAO();
	
	public abstract AppointmentDAO getAppointmentDAO();
	
	public abstract MarkDAO getMarkDAO();
	
	public abstract ReportDAO getReportDAO();
	
	public static DAOFactory getDAOFactory() {
		// temporary method before know config how
		try {
		ResourceBundle properties = ResourceBundle.getBundle("application");
        String url = properties.getString("url");
        if ((url == null || url.trim().isEmpty()) 
        		|| (url.contains("postgres")) 
        		|| (url.contains(":h2:"))) { 
        	return new PostgresDAOFactory();
        }
		} catch (Exception e) {
			// TO DO
			System.out.println("REMEMBER !");
		}
        return new PostgresDAOFactory();
	}

}
