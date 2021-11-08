package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.postgresql.ds.PGSimpleDataSource;

/**
 * 
 * @author peter
 * This is common class for entities in db.
 *
 */
public abstract class AbstractDAO {

    private static DataSource dataSource;

    private static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                ResourceBundle properties = ResourceBundle.getBundle("application");
                String url = properties.getString("url");
                if (url == null || url.trim().isEmpty()) {
                    throw new IllegalArgumentException("No url found!");
                } else if (url.contains("postgres")) {
                    dataSource = new PGSimpleDataSource();
                    ((PGSimpleDataSource) dataSource).setURL(url);
                } else if (url.contains(":h2:")) {
                    try {
                        Class.forName(properties.getString("driver"));
                    } catch (ClassNotFoundException ex) {
                    	Logger.getLogger(AbstractDAO.class.getName()).log(Level.ERROR, "Can't create datasource", ex);
                    }
                    dataSource = JdbcConnectionPool.create(url, properties.getString("user"), properties.getString("password"));
                } else {
                    throw new IllegalArgumentException("No config found!");
                }
            } catch (Exception ex) {
            	Logger.getLogger(AbstractDAO.class.getName()).log(Level.ERROR, "Can't create datasource", ex);
            }
        }
        return dataSource;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
        	if (getDataSource() != null) {
        		connection = dataSource.getConnection();
        	}
        } catch (SQLException ex) {
        	Logger.getLogger(AbstractDAO.class.getName()).log(Level.ERROR, "Can't create connection", ex);
        }
        return connection;
    }

}
