package custom.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.my.pet.spring.domain.Appointment;

/**
 * 
 * @author peter
 * This class for user appointments tag
 *
 */
public class AppointmentTag extends SimpleTagSupport {

    private Appointment appointment;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void doTag() throws JspException, IOException {
    	
    	ServletContext context = ((PageContext) getJspContext()).getServletContext();
    	String baseName = context.getInitParameter("javax.servlet.jsp.jstl.fmt.localizationContext");
    	String curLocale = (String) ((PageContext) getJspContext()).getSession().getAttribute("currentLocale");
    	if (curLocale == null) {
			curLocale = context.getInitParameter("javax.servlet.jsp.jstl.fmt.locale");
		}
    	
    	String page = (String) ((PageContext) getJspContext()).getSession().getAttribute("lapp");
    	
    	// Temporary workaround until i learn how to fix n19 here
    	ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale("en"));
//		ResourceBundle rb = ResourceBundle.getBundle(baseName, new Locale(curLocale));
		String appoint = rb.getString("manage.appointments.action.appoint");
		String disappoint = rb.getString("manage.appointments.action.disappoint");
		String syes = rb.getString("manage.appointments.yes");
		String sno = rb.getString("manage.appointments.no");
    	
        getJspContext().getOut().println("<td>" + appointment.getfId() + "</td>" + "<td>" + appointment.getFacultyName()
                + "</td>" + "<td>" + (appointment.getId() == 0 ? sno : syes) + "</td>"
                + "<td><a href=\"updateAppointment?fid=" + appointment.getfId() + "&apply=" + (appointment.getId() == 0)
                        + "&aid=" + appointment.getId() + "&page=" + page + "\">" + (appointment.getId() == 0 ? appoint : disappoint) + "</a></td>");
    }

}
