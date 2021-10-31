package servlets.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.Appointment;
import entity.Faculty;
import entity.SysUser;
import jdbc.DBException;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet for process user appointment/disappointment to faculty
 *
 */
public class UpdateAppointment implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Logger.getLogger(UpdateAppointment.class.getName()).log(Level.INFO, "Trying to update appointment");
        boolean isApply = Boolean.parseBoolean(request.getParameter("apply"));
        if (isApply) {
            SysUser su = (SysUser) (request.getSession().getAttribute("user")); 
            int fid = Integer.parseInt(request.getParameter("fid"));
            Faculty f = new Faculty();
            f.setId(fid);
            try {
            	DBManager.insertUserAppointment(su, f);
            } catch (DBException ex) {
            	Logger.getLogger(UpdateAppointment.class.getName()).log(Level.ERROR, "Can't update appointment", ex);
            }
        } else {
            int aid = Integer.parseInt(request.getParameter("aid"));
            Appointment a = new Appointment();
            a.setId(aid);
            try {
            	DBManager.removeAppointment(a);
            } catch (DBException ex) {
            	Logger.getLogger(UpdateAppointment.class.getName()).log(Level.ERROR, "Can't update appointment", ex);
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage_appointments.jsp");
        dispatcher.forward(request, response);
    }

}
