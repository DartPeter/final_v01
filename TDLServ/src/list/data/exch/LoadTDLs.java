package list.data.exch;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testpack.DBTests;
import testpack.TDLClass;

/**
 * Servlet implementation class LoadTDLs
 */
@WebServlet("/LoadTDLs")
public class LoadTDLs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadTDLs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/xml;charset=UTF-8");
		StringBuilder answer = new StringBuilder();
		answer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		answer.append("<projects>");
		List<TDLClass> list = DBTests.readAllProjects();
		for(TDLClass item : list) {
			answer.append("<pr>");
			answer.append("<id>");
			answer.append(item.id);
			answer.append("</id>");
			answer.append("<name>");
			answer.append(item.name);
			answer.append("</name>");
			answer.append("</pr>");
		}
		answer.append("</projects>");
		response.getWriter().write(answer.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
