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

import com.my.pet.spring.domain.Appointment;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import dao.AbstractDAO;
import dao.AppointmentDAO;

/**
 * 
 * @author peter
 * Postgresql implementation of appointment Dao
 *
 */
public class PostgressAppointmetDAO extends AbstractDAO implements AppointmentDAO {
    
    private static PostgressAppointmetDAO instance;
    
    private PostgressAppointmetDAO() {
        // empty
    }
    
    public static synchronized PostgressAppointmetDAO getInstance() {
        if (instance == null) {
            instance = new PostgressAppointmetDAO();
        }
        return instance;
    }

    @Override
    public List<Appointment> getUserAppointments(SysUser su) throws DBException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL_USER_APPOINTMENTS);) {
            ps.setInt(1, su.getId());
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
//                    Appointment a = new Appointment(rs.getInt("ua_id"), su.getId(), rs.getInt("f_id"), rs.getString("name"));
//                    appointments.add(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgressAppointmetDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot get appointment!", ex);
        }
        return appointments;
    }

    @Override
    public void insertUserAppointment(SysUser su, Faculty f) throws DBException {
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_NEW_APPOINTMENT);) {
            ps.setInt(1, su.getId());
            ps.setInt(2, f.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgressAppointmetDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot insert appointment!", ex);
        }
    }

    @Override
    public void removeAppointment(Appointment a) throws DBException {
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_DELETE_APPOINTMENT);) {
            ps.setInt(1, a.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgressAppointmetDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot remove appointment!", ex);
        }
    }
    
    

}
