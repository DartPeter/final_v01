package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ContextListenerLanguage implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
    	// no op
    }
	
	@Override
    public void contextInitialized(ServletContextEvent event) {
    	// obtain file name with locales descriptions
    	ServletContext context = event.getServletContext();
    	String localesFileName = context.getInitParameter("locales");
    	
    	// obtain real path on server
    	String localesFileRealPath = context.getRealPath(localesFileName);
    	
    	// load descriptions
    	Properties locales = new Properties();
    	try (InputStream is = new FileInputStream(localesFileRealPath)) {
			locales.load(is);
		} catch (IOException ex) {
			Logger.getLogger(ContextListenerLanguage.class.getName()).log(Level.ERROR, "Can't init context", ex);
		}
    	
    	// save descriptions to servlet context
    	context.setAttribute("locales", locales);
    }
	
}