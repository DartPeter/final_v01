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

public class LogoutTest {

	@Test(expected = None.class)
	public void testCaseSessionNull() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		Logout logout = new Logout();
		logout.execute(req, resp);
	}
	
	@Test(expected = None.class)
	public void testCaseSessionNotNull() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getSession(false)).thenReturn(new HttpSession() {

			@Override
			public long getCreationTime() {
				return 0;
			}

			@Override
			public String getId() {
				return null;
			}

			@Override
			public long getLastAccessedTime() {
				return 0;
			}

			@Override
			public ServletContext getServletContext() {
				return null;
			}

			@Override
			public void setMaxInactiveInterval(int interval) {
				// empty
			}

			@Override
			public int getMaxInactiveInterval() {
				return 0;
			}

			@Override
			public HttpSessionContext getSessionContext() {
				return null;
			}

			@Override
			public Object getAttribute(String name) {
				return null;
			}

			@Override
			public Object getValue(String name) {
				return null;
			}

			@Override
			public Enumeration<String> getAttributeNames() {
				return null;
			}

			@Override
			public String[] getValueNames() {
				return null;
			}

			@Override
			public void setAttribute(String name, Object value) {
				// empty
			}

			@Override
			public void putValue(String name, Object value) {
				// empty
			}

			@Override
			public void removeAttribute(String name) {
				// empty
			}

			@Override
			public void removeValue(String name) {
				// empty
			}

			@Override
			public void invalidate() {
				// empty
			}

			@Override
			public boolean isNew() {
				return false;
			}
			
		});
		
		when(req.getRequestDispatcher("login.jsp")).thenReturn(new RequestDispatcher() {
			@Override
			public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
			@Override
			public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
		});
		
		Logout logout = new Logout();
		logout.execute(req, resp);
	}

}
