package jdbc;

import dao.AppointmentDAO;
import dao.FacultyDAO;
import dao.MarkDAO;
import dao.ReportDAO;
import dao.SysUserDAO;
import dao.postgres.PostgresReportDAO;
import dao.postgres.PostgresSysUserDAO;

public class PostgresDAOFactory extends DAOFactory {

	@Override
	public SysUserDAO getSysUserDAO() {
		return PostgresSysUserDAO.getInstance();
	}

	@Override
	public FacultyDAO getFacultyDAO() {
//		return PostgresFacultyDAO.getInstance();
		return null;
	}

	@Override
	public AppointmentDAO getAppointmentDAO() {
//		return PostgressAppointmetDAO.getInstance();
		return null;
	}

	@Override
	public MarkDAO getMarkDAO() {
//		return PostgresMarkDAO.getInstance();
		return null;
	}

	@Override
	public ReportDAO getReportDAO() {
		return PostgresReportDAO.getInstance();
	}

}
