package servlets.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Unknown implements Action {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().println("Unknown command");
    }
    
}
