package dao.postgres;

import static jdbc.SQLConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import dao.AbstractDAO;
import dao.ReportDAO;
import entity.Faculty;
import jdbc.DBException;

/**
 * 
 * @author peter
 * Postgresql implementation of report Dao
 *
 */
public class PostgresReportDAO extends AbstractDAO implements ReportDAO {
    
    private static PostgresReportDAO instance;
    
    private PostgresReportDAO() {
        // empty
    }
    
    public static synchronized PostgresReportDAO getInstance() {
        if (instance == null) {
            instance = new PostgresReportDAO();
        }
        return instance;
    }

    @Override
    public List<List<String>> generateReport(Faculty f) throws DBException {
        List<List<String>> res = new ArrayList<>();
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_GENERATE_REPORT)) {
            ps.setInt(1, f.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	List<String> ls = List.of(rs.getString("user_id"), rs.getString("full_name"), Integer.toString(rs.getInt("sum_total")));
                	res.add(ls);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresReportDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot generate report!", ex);
        }
        return res;
    }

	@Override
	public Map<Integer, String> reportIdToMail(String range) throws DBException {
		Map<Integer, String> map = new HashMap<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_GET_REPORT_ID_TO_MAILS + range);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				map.put(rs.getInt("id"), rs.getString("email"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(PostgresReportDAO.class.getName()).log(Level.ERROR, null, ex);
			throw new DBException("Cannot get ids to emails!", ex);
		}
		return map;
	}
    
    

}