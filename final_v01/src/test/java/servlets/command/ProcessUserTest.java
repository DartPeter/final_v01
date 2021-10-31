package servlets.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.Test.None;

public class ProcessUserTest {

	@Test(expected = None.class)
	public void test() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("method")).thenReturn("block");
		when(req.getParameter("id")).thenReturn("1");
		when(req.getRequestDispatcher("manage_users.jsp")).thenReturn(new RequestDispatcher() {
			@Override
			public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
			@Override
			public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
				// empty
			}
		});
		ProcessUser processUser = new ProcessUser();
		processUser.execute(req, resp);
	}

}
