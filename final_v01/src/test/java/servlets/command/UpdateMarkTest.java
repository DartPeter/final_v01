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

import entity.SysUser;

public class UpdateMarkTest {

	@Test(expected = None.class)
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
				switch (name) {
				case "user" :
					return new SysUser();	
				}
				return null;
			}
		});
		
		when(req.getRequestDispatcher("view_marks.jsp")).thenReturn(new RequestDispatcher() {
			
			@Override
			public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
			
			@Override
			public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
		});
		
		UpdateMark updateMark = new UpdateMark();
		updateMark.execute(req, resp);
	}

}
