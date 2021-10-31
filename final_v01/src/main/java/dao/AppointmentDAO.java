package dao;

import java.util.List;
import entity.Appointment;
import entity.Faculty;
import entity.SysUser;
import jdbc.DBException;

/**
 * 
 * @author peter
 * This is common interface for appointment Dao
 *
 */
public interface AppointmentDAO {
    List<Appointment> getUserAppointments(SysUser su) throws DBException;
    
    void insertUserAppointment(SysUser su, Faculty f) throws DBException;
    
    void removeAppointment(Appointment a) throws DBException;
}
