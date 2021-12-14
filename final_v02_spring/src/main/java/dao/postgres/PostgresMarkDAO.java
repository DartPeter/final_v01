package dao.postgres;

import static jdbc.SQLConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.pet.spring.domain.Mark;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import dao.AbstractDAO;
import dao.MarkDAO;

/**
 * 
 * @author peter
 * Postgresql implementation of mark Dao
 *
 */
public class PostgresMarkDAO extends AbstractDAO implements MarkDAO {

	private static PostgresMarkDAO instance;

	private PostgresMarkDAO() {
		// empty
	}

	public static synchronized PostgresMarkDAO getInstance() {
		if (instance == null) {
			instance = new PostgresMarkDAO();
		}
		return instance;
	}

	@Override
	public List<Mark> getUserMarks(SysUser su) throws DBException {
//		List<Mark> lm = new ArrayList<>();
//		try (Connection con = getConnection(); 
//				PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL_USER_MARKS);) {
//			ps.setInt(1, su.getId());
//			try (ResultSet rs = ps.executeQuery();) {
//				while (rs.next()) {
//					Mark m = new Mark(rs.getInt("id"), su.getId(), rs.getInt("subj_id"), rs.getInt("value"),
//							rs.getString("name"));
//					lm.add(m);
//				}
//			}
//		} catch (SQLException ex) {
//			Logger.getLogger(PostgresMarkDAO.class.getName()).log(Level.ERROR, null, ex);
//			throw new DBException("Cannot get marks!", ex);
//		}
//		return lm;
		return null;
	}

	@Override
	public void updateUserMarks(List<Mark> marks) throws DBException {
//		try (Connection con = getConnection()) {
//			con.setAutoCommit(false);
//			try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ALL_USER_MARKS)) {
//				for (Mark m : marks) {
//					ps.setInt(1, m.getUserId());
//					ps.setInt(2, m.getSubjId());
//					ps.setInt(3, m.getValue());
//					ps.execute();
//				}
//				con.commit();
//			} catch (SQLException ex) {
//				con.rollback();
//				Logger.getLogger(PostgresMarkDAO.class.getName()).log(Level.ERROR, null, ex);
//				throw new DBException("Cannot update marks!", ex);
//			} finally {
//				con.setAutoCommit(true);
//			}
//		} catch (SQLException ex) {
//			Logger.getLogger(PostgresMarkDAO.class.getName()).log(Level.ERROR, null, ex);
//			throw new DBException("Cannot update marks!", ex);
//		}
	}

}
