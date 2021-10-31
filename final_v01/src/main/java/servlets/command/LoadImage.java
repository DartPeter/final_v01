package servlets.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.SysUser;
import jdbc.DBManager;

/**
 * 
 * @author peter
 * Servlet which load image in web
 *
 */
public class LoadImage implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		String path = null;
		SysUser su = (SysUser) request.getSession().getAttribute("user");
		if (su != null) {
			path = DBManager.getCertificate(su);
		}
		if (path != null) {
			try (InputStream is = new FileInputStream(new File(path));
					ServletOutputStream os = response.getOutputStream();) {
				byte[] buf = new byte[8192];
				int length;
				while ((length = is.read(buf)) > 0) {
					os.write(buf, 0, length);
				}
			}
		}
	}
}
