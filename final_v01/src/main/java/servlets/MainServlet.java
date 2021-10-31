package servlets;

import static java.util.Map.entry;

import java.io.*;
import java.util.Date;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import servlets.command.Action;
import servlets.command.LoadFileServlet;
import servlets.command.LoadImage;
import servlets.command.Login;
import servlets.command.Logout;
import servlets.command.ProcessFaculty;
import servlets.command.ProcessUser;
import servlets.command.RegisterProc;
import servlets.command.SendEmails;
import servlets.command.TestAJAX;
import servlets.command.UpdateAppointment;
import servlets.command.UpdateMark;

@WebServlet("/")
@MultipartConfig
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1329863885392906984L;
	
	private final Map<String, Action> actionMap = Map.ofEntries(
			entry("login", new Login()), 
			entry("logout", new Logout()), 
			entry("registerProc", new RegisterProc()), 
			entry("processFaculty", new ProcessFaculty()), 
			entry("processUser", new ProcessUser()), 
			entry("updateMark", new UpdateMark()), 
			entry("updateAppointment", new UpdateAppointment()),
			entry("loadFile", new LoadFileServlet()),
			entry("loadImage", new LoadImage()),
			entry("sendEmails", new SendEmails()),
			entry("testAJAX", new TestAJAX()));

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		testProcess(req, resp);
	}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        testProcess(req, resp);
    }
	
    public void testProcess(HttpServletRequest req, HttpServletResponse resp) {
    	Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Entering Main Servlet");
        String actionKey = null;
        try {
        actionKey = req.getServletPath().substring(1);
        } catch (Exception ex) {
        	Logger.getLogger(MainServlet.class.getName()).log(Level.ERROR, null, ex);
        }
        
        if (actionKey != null) {
            Action action = actionMap.getOrDefault(actionKey, new Login());
            try {
                if (action != null) {
                    action.execute(req, resp);
                }
            } catch (Exception ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.ERROR, null, ex);
            }
        }
    }
    
    public void requestAnalyzer(HttpServletRequest request, HttpServletResponse response) {
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(MainServlet.class.getName()).log(Level.ERROR, null, ex);
		}
        // Getting servlet request URL
        String url = request.getRequestURL().toString();

        // Below we extract information about the request object path
        // information.
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int portNumber = request.getServerPort();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String query = request.getQueryString();
        String method = request.getMethod();

        final String BR = "<br/>";

        response.setContentType("text/html");
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, new Date());
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Url: " + url + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Scheme: " + scheme + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Server Name: " + serverName + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Port: " + portNumber + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Context Path: " + contextPath + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Servlet Path: " + servletPath + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Path Info: " + pathInfo + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Query: " + query + BR);
        Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "Method: " + method + BR);
    }
	
}