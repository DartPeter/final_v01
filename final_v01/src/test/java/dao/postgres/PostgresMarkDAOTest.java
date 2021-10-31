package dao.postgres;

import static jdbc.SQLConstant.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import entity.Mark;
import entity.SysUser;
import jdbc.DBException;

public class PostgresMarkDAOTest {
    
    private static PostgresMarkDAO psmdao;
    private static PostgresSysUserDAO psudao;
    
    
    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        psmdao = PostgresMarkDAO.getInstance();
        psudao = PostgresSysUserDAO.getInstance();
        
        try (Connection con = psudao.getConnection();
                Statement statement = con.createStatement();) {
            String sql = SQL_CREATE_USER_TABLE + SQL_CREATE_ENROLEE_DETAILS + SQL_CREATE_SUBJECT_TABLE + SQL_CREATE_MARK_TABLE +
                    "INSERT INTO subject(name, stype) VALUES ('алгебра і початки аналізу', 'certificate');\r\n" + 
                    "INSERT INTO subject(name, stype) VALUES ('геометрія', 'certificate');"
                    ;
            statement.executeUpdate(sql);
        }
    }
    
    @After
    public void clear() throws SQLException {
        try (Connection con = psudao.getConnection();
             Statement statement = con.createStatement();
        ) {
            String sql = "DELETE FROM mark;" + 
			"DELETE FROM enrolee_details;" + 
	        "DELETE FROM sys_user;";
            statement.executeUpdate(sql);
        }
    }
    
    private SysUser createSU(String login) {
        return new SysUser("name", "admin", login, "pass1", "email1");
     }

    @Test
    public void testGetMarks() throws DBException {
        SysUser su = createSU("qwe");
        psudao.insertSysUser(su);
        Assert.assertEquals(2, psmdao.getUserMarks(su).size());
    }
    
    @Ignore("because h2db don't support") 
    @Test
    public void testUpdateMarks() throws DBException {
        SysUser su = createSU("qwe");
        psudao.insertSysUser(su);
        Assert.assertEquals(2, psmdao.getUserMarks(su).size());
        List<Mark> lm = new ArrayList<>();
        psmdao.updateUserMarks(lm);
    }
    
    @Test
    public void testDummySetGetConstructor() {
    	Mark m = new Mark();
    	m.setId(0);
    	m.setUserId(0);
    	m.setSubjId(0);
    	m.setValue(0);
    	m.setSubjName("0");
    	Assert.assertArrayEquals(
    			new int[]{0, 0, 0, 0, 0}, 
    			new int[]{m.getId(), m.getUserId(), m.getSubjId(), m.getValue(), Integer.parseInt(m.getSubjName())});
    }

}
