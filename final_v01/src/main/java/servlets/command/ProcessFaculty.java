package servlets.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.Faculty;
import jdbc.DBException;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet for process CRUD faculty
 *
 */
public class ProcessFaculty implements Action {
    
    private static final String PRG_KEY = "facultyOperOK";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Logger.getLogger(ProcessFaculty.class.getName()).log(Level.INFO, "Trying to process faculty");
        request.setCharacterEncoding("UTF-8");
        
        String requestPRGValue = request.getParameter("key_aef");
        Object sessionPRGValue = request.getSession().getAttribute(PRG_KEY);
        if (sessionPRGValue != null && sessionPRGValue.equals(requestPRGValue)) {
        	Logger.getLogger(Login.class.getName()).log(Level.INFO, "PRG Check triggered");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("add_edit_faculty.jsp");
            dispatcher.forward(request, response);
            return;
        }
        request.getSession().setAttribute(PRG_KEY, requestPRGValue);
        
        String errors = "";
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException nfe) {
        	//empty
        }
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            errors = "messages.aef.name.not.empty";
        }
        int budgetPlaces = -1;
        int totalPlaces = -1;
        try {
            budgetPlaces = Integer.parseInt(request.getParameter("budgetPlaces"));
        } catch (NumberFormatException nfe) {
            errors = "messages.aef.budget.number";
            Logger.getLogger(ProcessFaculty.class.getName()).log(Level.ERROR, "Budget Places must be number!", nfe);
        }
        try {
            totalPlaces = Integer.parseInt(request.getParameter("totalPlaces"));
        } catch (NumberFormatException nfe) {
            errors = "messages.aef.total.number";
            Logger.getLogger(ProcessFaculty.class.getName()).log(Level.ERROR, "Total Places must be number!", nfe);
        }
        if (totalPlaces < budgetPlaces) {
        	errors = "messages.total.must.be.greater";
        }
        Faculty faculty = null;
        String mode = request.getParameter("mode");
        if (errors.isEmpty()) {
            faculty = new Faculty(id, name, budgetPlaces, totalPlaces);
            switch (mode) {
            case "new":
            	try {
            		DBManager.insertFaculty(faculty);
            		Logger.getLogger(ProcessFaculty.class.getName()).log(Level.INFO, "Faculty was successfully inserted.");
            	} catch (DBException ex) {
            		errors = "messages.aef.cant.insert";
            		Logger.getLogger(ProcessFaculty.class.getName()).log(Level.ERROR, ex.getMessage());
            	}
                if (faculty.getId() == -1) {
                    errors = "messages.aef.wrong";
                }
                break;
            case "edit":
            	try {
            		DBManager.updateFaculty(faculty);
            		Logger.getLogger(ProcessFaculty.class.getName()).log(Level.INFO, "Faculty was successfully updated.");
            	} catch (DBException ex) {
            		errors = "messages.aef.cant.update";
            		Logger.getLogger(ProcessFaculty.class.getName()).log(Level.ERROR, ex.getMessage());
            	}
                break;
            case "del":
            	try {
            		DBManager.removeFaculty(faculty);
            		Logger.getLogger(ProcessFaculty.class.getName()).log(Level.INFO, "Faculty was successfully removed.");
            	} catch (DBException ex) {
            		errors = "messages.aef.cant.remove";
            		Logger.getLogger(ProcessFaculty.class.getName()).log(Level.ERROR, errors, ex);
            	}
                break;
            default:
                break;
            }
            if (errors.isEmpty()) {
            	if ("new".equals(mode)) {
            		errors = "messages.aef.add.succes";
            	}
            	if ("edit".equals(mode)) {
            		errors = "messages.aef.edit.succes";
            	}
            }
        }
        
        request.setAttribute("errors", errors);
        request.setAttribute("faculty", faculty);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("del".equals(mode) ? "view_faculties.jsp" : "add_edit_faculty.jsp");
        dispatcher.forward(request, response);
    }

}
