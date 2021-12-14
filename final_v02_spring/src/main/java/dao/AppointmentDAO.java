package dao;

import java.util.List;
import com.my.pet.spring.domain.Appointment;
import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

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
