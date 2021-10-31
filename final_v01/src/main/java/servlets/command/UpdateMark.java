package servlets.command;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.Mark;
import entity.SysUser;
import jdbc.DBException;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet which stores user mark
 *
 */
public class UpdateMark implements Action {
    
    private static final String PRG_KEY = "updateMarksOK";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Logger.getLogger(UpdateMark.class.getName()).log(Level.INFO, "Trying to update mark");
        request.setCharacterEncoding("UTF-8");
        
        final String destPage = "view_marks.jsp";
        String requestPRGValue = request.getParameter("key_vm");
        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
        	Logger.getLogger(Login.class.getName()).log(Level.INFO, "PRG Check triggered");
        	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            return;
        }
        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        List<Mark> lm = new ArrayList<>();
        SysUser su = (SysUser) (request.getSession().getAttribute("user"));
        su.getId();
        for (String s : request.getParameterMap().keySet()) {
        	if (s.startsWith("id")) {
	            int subjId = Integer.parseInt(s.substring(2));
	            int value = Integer.parseInt(request.getParameterMap().get(s)[0]);
	            Mark m = new Mark();
	            m.setUserId(su.getId());
	            m.setSubjId(subjId);
	            m.setValue(value);
	            lm.add(m);
        	}
        }
        try {
        	DBManager.updateUserMarks(lm);
        } catch (DBException ex) {
        	Logger.getLogger(UpdateMark.class.getName()).log(Level.ERROR, "Can't update mark", ex);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

}
