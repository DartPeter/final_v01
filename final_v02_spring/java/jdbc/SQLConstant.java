package jdbc;

/**
 * 
 * @author peter
 * SQL queries container
 *
 */
public class SQLConstant {
	
	private SQLConstant() {
		//empty
	}
	
    //--problems wit user enum in h2db posgres or
    public static final String SQL_CREATE_USER_TABLE = "CREATE TYPE IF NOT EXISTS user_type AS ENUM ('admin', 'enrolee');" 
    		+ "CREATE TABLE IF NOT EXISTS sys_user\n" 
    		+ "(id SERIAL PRIMARY KEY,\n" 
    		+ "full_name VARCHAR(50) NOT NULL,\n" 
    		+ "utype VARCHAR(10),\n" 
    		+ "login VARCHAR(20) NOT NULL UNIQUE,\n" 
    		+ "pass VARCHAR(20) NOT NULL,\n" 
    		+ "email VARCHAR(20),\n" 
    		+ "is_blocked BOOLEAN DEFAULT FALSE);";
    public static final String SQL_CREATE_ENROLEE_DETAILS = "CREATE TABLE IF NOT EXISTS enrolee_details\r\n "
    		+ "(id SERIAL PRIMARY KEY,\n"
    		+ "user_id INT,\n"
    		+ "city VARCHAR(30) NOT NULL,\n"
    		+ "region VARCHAR(30) NOT NULL,\n"
    		+ "institution_name VARCHAR(80) NOT NULL,\n "
    		+ "certificate VARCHAR(1000),"
    		+ "CONSTRAINT fk_user_ed\n"
    		+ "      FOREIGN KEY(user_id) \n"
    		+ "REFERENCES sys_user(id)\n"
    		+ ");";
    public static final String SQL_CREATE_FACULTY_TABLE = "CREATE TABLE IF NOT EXISTS faculty " 
            + " (id SERIAL PRIMARY KEY," 
            + " name VARCHAR(50) NOT NULL UNIQUE, " 
            + " budget_places INT NOT NULL DEFAULT 0," 
            + " total_places  INT NOT NULL DEFAULT 0);";
    public static final String SQL_CREATE_SUBJECT_TABLE = "CREATE TYPE IF NOT EXISTS subj_type AS ENUM ('state_exam', 'certificate');\r\n" 
    		+ "CREATE TABLE IF NOT EXISTS subject\r\n" 
            + "(\r\n" 
            + "    id SERIAL PRIMARY KEY,\r\n" 
            + "    stype subj_type,\r\n" 
            + "    name VARCHAR(30)\r\n" 
            + ");";
    public static final String SQL_CREATE_MARK_TABLE = "CREATE TABLE IF NOT EXISTS mark\r\n" 
    		+ "(\r\n" 
    		+ "    id SERIAL PRIMARY KEY,\r\n" 
    		+ "    user_id INT,\r\n" 
    		+ "    subj_id INT,\r\n" 
    		+ "    value INT CHECK (value >= 0 AND value <= 12),\r\n" 
    		+ "    CONSTRAINT fk_user_m\r\n" 
    		+ "      FOREIGN KEY(user_id) \r\n" 
    		+ "      REFERENCES sys_user(id),\r\n" 
    		+ "    CONSTRAINT fk_subj_m\r\n" 
    		+ "      FOREIGN KEY(subj_id) \r\n" 
    		+ "      REFERENCES subject(id)\r\n" 
    		+ ");";
    public static final String SQL_CREATE_APPOINTMENT_TABLE = "CREATE TABLE IF NOT EXISTS appointment" 
    		+ "    (id SERIAL PRIMARY KEY,\r\n" 
    		+ "    user_id INT,\r\n" 
    		+ "    f_id INT,\r\n" 
    		+ "    CONSTRAINT fk_user_a\r\n" 
    		+ "      FOREIGN KEY(user_id) \r\n" 
    		+ "      REFERENCES sys_user(id),\r\n" 
    		+ "    CONSTRAINT fk_f_a\r\n" 
    		+ "      FOREIGN KEY(f_id) \r\n" 
    		+ "      REFERENCES faculty(id)\r\n" 
    		+ ");";
    

    public static final String SQL_FIND_ALL_FACULTIES = "SELECT * FROM faculty ORDER BY id";
    public static final String SQL_INSERT_NEW_FACULTY = "INSERT INTO faculty(name, budget_places, total_places) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_FACULTY = "UPDATE faculty SET name = ?, budget_places = ?, total_places = ? WHERE id = ?";
    public static final String SQL_DELETE_FACULTY = "DELETE FROM faculty WHERE id = ?";
    public static final String SQL_SELECT_FACULTY = "SELECT * FROM faculty WHERE name = ?";

    public static final String SQL_FIND_ALL_SYS_USERS = "SELECT * FROM sys_user ORDER BY id";
    public static final String SQL_INSERT_NEW_SYS_USER = "INSERT INTO sys_user(full_name, utype, login, pass, email) VALUES (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_SYS_USER = "UPDATE sys_user SET full_name = ?, utype = ?, login = ?, pass = ?, email = ? WHERE id = ?";
    public static final String SQL_DELETE_SYS_USER = "DELETE FROM sys_user WHERE id = ?";
    public static final String SQL_SELECT_SYS_USER = "SELECT * FROM sys_user WHERE login = ?";
    public static final String SQL_SET_BLOCK_STAT = "UPDATE sys_user SET is_blocked = ? WHERE id = ?";
    
    public static final String SQL_SET_CERTIFICATE = "UPDATE enrolee_details SET certificate = ? where user_id = ?;\r\n"
    		+ "INSERT INTO enrolee_details (user_id, city, region, institution_name, certificate)\r\n"
    		+ "       SELECT ?, '', '', '', ?\r\n"
    		+ "       WHERE NOT EXISTS (SELECT 1 FROM enrolee_details WHERE user_id = ?);";
    public static final String SQL_INSERT_NEW_USER_DETAILS = "INSERT INTO enrolee_details(user_id, city, region, institution_name) values (\r\n" 
    		+ "    ?, ?, ?, ?);";
    public static final String SQL_GET_CERTIFICATE = "select certificate from enrolee_details where user_id = ?";
    
    public static final String SQL_FIND_ALL_USER_MARKS = "select * from subject left outer join (select * from mark where user_id = ?) um on subject.id = um.subj_id";
    public static final String SQL_UPDATE_ALL_USER_MARKS = "INSERT INTO mark (user_id, subj_id, value) VALUES (?, ?, ?) ON CONFLICT (user_id, subj_id) DO UPDATE SET value = EXCLUDED.value";
    
    public static final String SQL_FIND_ALL_USER_APPOINTMENTS = 
            "SELECT faculty.id f_id, ua.id ua_id, name, ua.user_id u_id FROM faculty LEFT OUTER JOIN (SELECT * FROM appointment WHERE user_id = ?) ua ON faculty.id = ua.f_id ORDER BY faculty.id";
    public static final String SQL_INSERT_NEW_APPOINTMENT = "INSERT INTO appointment(user_id, f_id) VALUES (?, ?)";
    public static final String SQL_DELETE_APPOINTMENT = "DELETE FROM appointment WHERE id = ?";
    
    public static final String SQL_GET_REPORT_ID_TO_MAILS = "select id, email from sys_user where id in "; 
    public static final String SQL_GENERATE_REPORT = "SELECT SUM(value) sum_total, user_id, sys_user.full_name \r\n" 
    		+ "FROM (SELECT * FROM mark JOIN subject ON subj_id = subject.id \r\n" 
    		+ "      WHERE stype = 'state_exam') scm \r\n" 
    		+ "JOIN sys_user ON user_id = sys_user.id\r\n" 
    		+ "GROUP BY user_id, sys_user.full_name\r\n" 
    		+ "HAVING user_id IN (SELECT DISTINCT appointment.user_id FROM appointment where f_id = ?)\r\n" 
    		+ "ORDER BY sum_total DESC ";

}
