package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.SysUser;

/**
 * 
 * @author peter
 * Filter to restrict access to pages
 *
 */
@WebFilter(filterName = "pageUserFilter", urlPatterns = { "/*" })
public class PageUserFilter implements Filter {

	private static final String[] freePages = { "/login", "/logout", "/registerProc", "/testAJAX", "/login.jsp", "/register.jsp", "/changeLocale.jsp" };
	private static final List<String> freePagesList = new ArrayList<>(Arrays.asList(freePages));
	private static final String[] adminPages = { "/processFaculty", "/processUser", "/sendEmails", "/add_edit_faculty.jsp",
			"/generate_report.jsp", "/home_admin.jsp", "/manage_users.jsp", "/view_faculties.jsp" };
	private static final List<String> adminPagesList = new ArrayList<>(Arrays.asList(adminPages));
	
	static {
		adminPagesList.addAll(freePagesList);
	}
	
	private static final String[] enroleePages = { "/updateMark", "/updateAppointment", "/loadFile", "/loadImage", "/home_user.jsp",
			"/manage_appointments.jsp", "/view_marks.jsp" };
	private static final List<String> enroleePagesList = new ArrayList<>(Arrays.asList(enroleePages));
	
	static {
		enroleePagesList.addAll(freePagesList);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();

		SysUser su = (SysUser) req.getSession().getAttribute("user");
		List<String> allowedPages = freePagesList;
		if (su != null && su.getId() > 0) {
			if ("enrolee".equals(su.getUserType())) {
				allowedPages = enroleePagesList;
			} else if ("admin".equals(su.getUserType())) {
				allowedPages = adminPagesList;
			}
		}
		if (allowedPages.contains(servletPath)) {
			chain.doFilter(request, response);
		} else {
			Logger.getLogger(PageUserFilter.class.getName()).log(Level.WARN, "User trying to access forbidden page!");
			request.getRequestDispatcher("forbidden.jsp").forward(request, response);
		}
	}

}
