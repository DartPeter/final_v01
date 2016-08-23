package record.data.exch;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testpack.DBTests;
import testpack.Record;

/**
 * Servlet implementation class LoadRecs
 */
@WebServlet("/LoadRecs")
public class LoadRecs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadRecs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		StringBuilder answer = new StringBuilder();
		answer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		answer.append("<records>");
		List<Record> list = DBTests.readAllRecords();
		for(Record item : list) {
			answer.append("<r>");
			answer.append("<id>");
			answer.append(item.id);
			answer.append("</id>");
			answer.append("<name>");
			answer.append(item.name);
			answer.append("</name>");
			answer.append("<stat>");
			answer.append(item.status);
			answer.append("</stat>");
			answer.append("<prid>");
			answer.append(item.pr_id);
			answer.append("</prid>");
			answer.append("</r>");
//			System.out.println(item);
		}
		answer.append("</records>");
		System.out.println(answer);
		response.getWriter().write(answer.toString());//append
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
