package servlets.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author peter
 * Servlet processes user logout.
 *
 */
public class Logout implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Logger.getLogger(Logout.class.getName()).log(Level.INFO, "Trying to logout");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

}
