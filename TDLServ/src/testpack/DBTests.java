package testpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DBTests {

	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String ADDRESS = "jdbc:mysql://localhost:3306/sample";
	private static String USER = "root";
	private static String PWD = "password";
	
	// lists
	
	public static int getMaxID() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int num = -1;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "select max(id) from projects";
			rs = stmt.executeQuery(sql);
			rs.next();
			num = rs.getInt(1);
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public static void addList(String ln) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "insert into projects(name) values('" + ln + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<TDLClass> readAllProjects() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<TDLClass> list = new LinkedList<>();
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "select * from projects order by id";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				TDLClass rec = new TDLClass(name, id);
				list.add(rec);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void renameList(int id, String nn) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "update projects set name = '" + nn + "' where id = " + id;
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delList(int id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "delete from projects where id = " + id;
			stmt.executeUpdate(sql);
			stmt.close();
			
			stmt = conn.createStatement();
			sql = "delete from tasks where project_id = " + id;
			stmt.executeUpdate(sql);			
			stmt.close();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// records
	
	public static int getMaxIDRec() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int num = -1;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "select max(id) from tasks";
			rs = stmt.executeQuery(sql);
			rs.next();
			num = rs.getInt(1);
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public static void addRec(String name, int pr_id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "insert into tasks(name, status, project_id) "
					+ "values ('" + name + "', " + false + ", " + pr_id + ")";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delRec(int id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "delete from tasks where id = " + id;
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void renRec(int id, String name) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "update tasks set name = '" + name +"' where id = " + id;
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void chStat(int id, String st) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "update tasks set status = " + st +" where id = " + id;
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void swapRecs(int id1, int id2) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			
			stmt = conn.createStatement();
			String sql = "select name, status from tasks where id=" + id1;
			rs = stmt.executeQuery(sql);
			rs.next();
			String n1 = rs.getString(1);
			boolean s1 = rs.getBoolean(2);
			rs.close();
			stmt.close();
			
			stmt = conn.createStatement();
			sql = "select name, status from tasks where id=" + id2;
			rs = stmt.executeQuery(sql);
			rs.next();
			String n2 = rs.getString(1);
			boolean s2 = rs.getBoolean(2);
			rs.close();
			stmt.close();
			
			stmt = conn.createStatement();
			sql = "update tasks set name='" + n1 + "', status=" + s1 + " where id=" + id2;
			stmt.executeUpdate(sql);
			stmt.close();
			
			stmt = conn.createStatement();
			sql = "update tasks set name='" + n2 + "', status=" + s2 + " where id=" + id1;
			stmt.executeUpdate(sql);
			stmt.close();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Record> readAllRecords() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Record> list = new LinkedList<>();
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PWD);
			stmt = conn.createStatement();
			String sql = "select * from tasks order by id";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				boolean stat = rs.getBoolean(3);
				int prid = rs.getInt(4);
				Record rec = new Record(id, name, stat, prid);
				list.add(rec);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		List<Record> lr = readAllRecords();
		for(Record r : lr) {
			System.out.println(r);
		}
	}

}
