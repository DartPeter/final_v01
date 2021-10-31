package servlets.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author peter
 * Part of comand pattern
 *
 */
public interface Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
 }
