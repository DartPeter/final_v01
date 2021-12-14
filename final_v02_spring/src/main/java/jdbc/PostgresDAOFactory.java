package jdbc;

import dao.AppointmentDAO;
import dao.FacultyDAO;
import dao.MarkDAO;
import dao.ReportDAO;
import dao.SysUserDAO;
import dao.postgres.PostgresMarkDAO;
import dao.postgres.PostgresReportDAO;
import dao.postgres.PostgresSysUserDAO;
import dao.postgres.PostgressAppointmetDAO;

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
		return PostgressAppointmetDAO.getInstance();
	}

	@Override
	public MarkDAO getMarkDAO() {
		return PostgresMarkDAO.getInstance();
	}

	@Override
	public ReportDAO getReportDAO() {
		return PostgresReportDAO.getInstance();
	}

}
