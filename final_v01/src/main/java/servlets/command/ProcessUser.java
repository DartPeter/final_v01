package servlets.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.SysUser;
import jdbc.DBException;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet for process block/unblock user
 *
 */
public class ProcessUser implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Logger.getLogger(ProcessUser.class.getName()).log(Level.INFO, "Trying to process user");
        String method = request.getParameter("method");
        boolean flag;
        if ("block".equals(method)) {
            flag = true;
        } else {
            flag = false;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        try {
        	DBManager.setBlockStat(sysUser, flag);
        }  catch (DBException ex) {
        	Logger.getLogger(ProcessFaculty.class.getName()).log(Level.ERROR, "Can't (un)block user", ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage_users.jsp");
        dispatcher.forward(request, response);
    }

}
