package servlets.command;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import entity.SysUser;
import jdbc.DBException;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet which attach image to enrolee
 *
 */
public class LoadFileServlet implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Part filePart = request.getPart("imageFile");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
		InputStream fileContent = filePart.getInputStream();
		String address = request.getSession().getServletContext().getRealPath("images");
		
		try {
			Files.copy(fileContent, Paths.get(address, fileName), StandardCopyOption.REPLACE_EXISTING);
			SysUser su = (SysUser) request.getSession().getAttribute("user");
			su.setCertificate(Paths.get(address, fileName).toString());
			DBManager.setCertificate(su);
			Logger.getLogger(LoadFileServlet.class.getName()).log(Level.INFO, "File was successfully attached!");
		} catch (IOException ex) {
			Logger.getLogger(LoadFileServlet.class.getName()).log(Level.ERROR, "File not found!", ex);
		} catch (DBException ex) {
			Logger.getLogger(LoadFileServlet.class.getName()).log(Level.ERROR, "Can't attach file!", ex);
		}
		
		response.sendRedirect("home_user.jsp");
	}

}
