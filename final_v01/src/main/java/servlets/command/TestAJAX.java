package servlets.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.SysUser;
import jdbc.DBException;
import jdbc.DBManager;

public class TestAJAX implements Action {

	private static final String PRG_KEY = "createUserOK";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Logger.getLogger(RegisterProc.class.getName()).log(Level.INFO, "Trying to register user");
        request.setCharacterEncoding("UTF-8");
        
        final String destPage = "register.jsp";
        String requestPRGValue = request.getParameter("key_reg");
        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
        	Logger.getLogger(Login.class.getName()).log(Level.INFO, "PRG Check triggered");
        	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            return;
        }
        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        SysUser su = new SysUser(request.getParameter("full_name"), 
                request.getParameter("user_type"), 
                request.getParameter("login"), 
                request.getParameter("password"), 
                request.getParameter("email"));
        
        String[] details = null;
        String city = request.getParameter("city");
        if (city != null) {
            details = new String[] {
                    city,
                    request.getParameter("region"),
                    request.getParameter("inst_name")
            };
        }
        try {
        	DBManager.insertSysUser(su, details);
        } catch (DBException ex) {
        	Logger.getLogger(RegisterProc.class.getName()).log(Level.ERROR, ex.getMessage());
        }
        String message;
        if (su.getId() > 0) {
            message = "messages.register.ok";
            Logger.getLogger(RegisterProc.class.getName()).log(Level.ERROR, "User was registered succesfully");
        } else {
            message = "messages.register.not.ok";
        }
        request.setAttribute("message", message);
         
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

}
