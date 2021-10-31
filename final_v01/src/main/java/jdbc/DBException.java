package jdbc;

/**
 * 
 * @author peter
 * Exception to process in servlets
 *
 */
public class DBException extends Exception {
	
	private static final long serialVersionUID = 362289493814144530L;

	public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}
