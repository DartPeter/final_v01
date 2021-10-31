package servlets.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.SysUser;
import jdbc.DBException;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet processes user login.
 *
 */
public class Login implements Action {
    
    private static final String HOME_USER_JSP = "home_user.jsp";
	private static final String HOME_ADMIN_JSP = "home_admin.jsp";
	private static final String ADMIN = "admin";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Logger.getLogger(Login.class.getName()).log(Level.INFO, "Trying to login");
        request.setCharacterEncoding("UTF-8");
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String destPage = "login.jsp";
        String message = "";
        
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            message = "messages.login.invalid.login.password";
        } else {
        	SysUser su = null;
        	try {
        		su = DBManager.getSysUserByLogin(login);
        	} catch (DBException ex) {
        		Logger.getLogger(Login.class.getName()).log(Level.INFO, "Error happened.", ex);
        	}
            if (su == null) {
                message = "messages.login.invalid.user";
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "Invalid user.");
            } else if (su.isBlocked()) {
                message = "messages.login.user.is.blocked";
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "Blocked user tried to login.");
            } else if (!password.equals(su.getPass())) {
            	message = "messages.login.invalid.login.password";
            	Logger.getLogger(Login.class.getName()).log(Level.INFO, "Wrong password was entered.");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", su);
                session.setAttribute("userId", su.getId());
                if (su.getUserType().equals(ADMIN)) {
                    destPage = HOME_ADMIN_JSP;
                } else {
                    destPage = HOME_USER_JSP;
                }
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "User successfully logged.");
            }
        }
        
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

}
