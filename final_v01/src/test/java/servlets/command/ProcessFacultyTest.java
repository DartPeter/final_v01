package servlets.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Test;
import org.junit.Test.None;

public class ProcessFacultyTest {
	
	@Test(expected = None.class)
	public void tesNew() throws Exception {
		testAll("new");
	}
	
	@Test(expected = None.class)
	public void tesEdit() throws Exception {
		testAll("edit");
	}
	
	@Test(expected = None.class)
	public void tesDel() throws Exception {
		testAll("del");
	}
	
	private void testAll(String mode) throws Exception {
		
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		
		when(req.getSession()).thenReturn(new HttpSession() {
			
			@Override
			public void setMaxInactiveInterval(int interval) {
				// empty
			}
			
			@Override
			public void setAttribute(String name, Object value) {
				// empty
			}
			
			@Override
			public void removeValue(String name) {
				// empty
			}
			
			@Override
			public void removeAttribute(String name) {
				// empty
			}
			
			@Override
			public void putValue(String name, Object value) {
				// empty
			}
			
			@Override
			public boolean isNew() {
				return false;
			}
			
			@Override
			public void invalidate() {
				// empty
			}
			
			@Override
			public String[] getValueNames() {
				return null;
			}
			
			@Override
			public Object getValue(String name) {
				return null;
			}
			
			@Override
			public HttpSessionContext getSessionContext() {
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				return null;
			}
			
			@Override
			public int getMaxInactiveInterval() {
				return 0;
			}
			
			@Override
			public long getLastAccessedTime() {
				return 0;
			}
			
			@Override
			public String getId() {
				return null;
			}
			
			@Override
			public long getCreationTime() {
				return 0;
			}
			
			@Override
			public Enumeration<String> getAttributeNames() {
				return null;
			}
			
			@Override
			public Object getAttribute(String name) {
				return null;
			}
		});
		
		when(req.getParameter("id")).thenReturn("1");
		when(req.getParameter("name")).thenReturn("name");
		when(req.getParameter("budgetPlaces")).thenReturn("11");
		when(req.getParameter("totalPlaces")).thenReturn("15");
		when(req.getParameter("mode")).thenReturn(mode);
		
		when(req.getRequestDispatcher("del".equals(mode) ? "view_faculties.jsp" : "add_edit_faculty.jsp")).thenReturn(new RequestDispatcher() {
			@Override
			public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
			@Override
			public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
		});
		ProcessFaculty processFaculty = new ProcessFaculty();
		processFaculty.execute(req, resp);
	}

}
