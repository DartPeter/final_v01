package servlets;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MainServletTest {

	@Ignore("need to add more")
	@Test
	public void test() throws IOException, ServletException {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		
		
		when(req.getParameter("name"))
			.thenReturn("Ivanov");
		
		PipedReader pipedReader = 
				new PipedReader();
		
		PipedWriter pipedWriter = 
				new PipedWriter(pipedReader);

		PrintWriter pw = new PrintWriter(pipedWriter);
		
		when(resp.getWriter())
			.thenReturn(pw);

		
		MainServlet servlet = new MainServlet();
		
		System.out.println("TEST SERVLET 1");
		servlet.doGet(req, resp);
		
		System.out.println("TEST SERVLET 2");
		BufferedReader reader = new BufferedReader(pipedReader);
		
		System.out.println("TEST SERVLET 3");
		String actually = reader.readLine();
		System.out.println("TEST SERVLET 4");
		String expected = "Hi, Ivanov";
		
		Assert.assertEquals(expected, actually);
		
		reader.close();
		pw.close();
		
		System.out.println("TEST SERVLET 9");
		
		
	}

}
