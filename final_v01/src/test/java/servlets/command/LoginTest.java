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

import org.junit.Assert;
import org.junit.Test;

public class LoginTest {

	@Test
	public void test() throws Exception {
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
				// empty
				return false;
			}
			@Override
			public void invalidate() {
				// empty
			}
			@Override
			public String[] getValueNames() {
				// empty
				return null;
			}
			@Override
			public Object getValue(String name) {
				// empty
				return null;
			}
			@Override
			public HttpSessionContext getSessionContext() {
				// empty
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				// empty
				return null;
			}
			@Override
			public int getMaxInactiveInterval() {
				// empty
				return 0;
			}
			@Override
			public long getLastAccessedTime() {
				// empty
				return 0;
			}
			@Override
			public String getId() {
				// empty
				return null;
			}
			@Override
			public long getCreationTime() {
				// empty
				return 0;
			}
			@Override
			public Enumeration<String> getAttributeNames() {
				// empty
				return null;
			}
			@Override
			public Object getAttribute(String name) {
				// empty
				return null;
			}
		});
		final StringBuffer key = new StringBuffer();
		when(req.getParameter("login")).thenReturn("admin");
		when(req.getParameter("password")).thenReturn("password");
		when(req.getRequestDispatcher("login.jsp")).thenReturn(new RequestDispatcher() {
			@Override
			public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
			@Override
			public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				key.append("1");
			}
		});
		Login ls = new Login();
		ls.execute(req, resp);
		Assert.assertEquals("1", key.toString());
	}

}
