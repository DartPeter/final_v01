package dao.postgres;

import static jdbc.SQLConstant.*;

import entity.SysUser;
import jdbc.DBException;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import java.sql.*;

public class PostgresSysUserDAOTest {

    private static PostgresSysUserDAO psudao = PostgresSysUserDAO.getInstance();

    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        
        psudao = PostgresSysUserDAO.getInstance();

        try (Connection con = psudao.getConnection();
             Statement statement = con.createStatement();
        ) {
            String sql = SQL_CREATE_USER_TABLE + SQL_CREATE_ENROLEE_DETAILS;
            statement.executeUpdate(sql);
        }
    }

    @After
    public void clear() throws SQLException {
        try (Connection con = psudao.getConnection();
             Statement statement = con.createStatement();
        ) {
            String sql = "DELETE FROM enrolee_details;" + "DELETE FROM sys_user;";
            statement.executeUpdate(sql);
        }
    }

    private SysUser createSUAdmin(String login) {
       return new SysUser("name", "admin", login, "pass1", "email1");
    }
    
    private SysUser createSUEnrolee(String login) {
    	return new SysUser("name", "enrolee", login, "pass1", "email1");
    }

    @Test
    public void testInsertAdmin() throws DBException {
        SysUser su = createSUAdmin("qwe");
        psudao.insertSysUser(su);
        Assert.assertEquals(1, psudao.getAllSysUsers().size());
    }
    
    @Test
    public void insertEnrolee() throws DBException {
    	SysUser su = createSUAdmin("qwe");
        psudao.insertSysUser(su, "city", "region", "institution");
        Assert.assertEquals(1, psudao.getAllSysUsers().size());
    }

    @Test
    public void testGetByLogin() throws DBException {
        String login = "a a a";
        SysUser su1 = createSUAdmin(login);
        psudao.insertSysUser(su1);
        SysUser su2 = psudao.getSysUserByLogin(login);
        Assert.assertEquals(login, su2.getLogin());
    }
    
    @Test
    public void testGetUnexisted() throws DBException {
    	Assert.assertNull(psudao.getSysUserByLogin("login"));
    }

    @Test
    public void testGetAllSysUsers() throws DBException {
        psudao.insertSysUser(createSUAdmin("su1"));
        psudao.insertSysUser(createSUAdmin("su2"));
        psudao.insertSysUser(createSUAdmin("su3"));
        Assert.assertEquals(3, psudao.getAllSysUsers().size());
    }

    @Test
    public void testRemove() throws DBException {
        SysUser su1 = createSUAdmin("su1");
        psudao.insertSysUser(su1);
        Assert.assertEquals(1, psudao.getAllSysUsers().size());
        psudao.removeSysUser(su1);
        Assert.assertEquals(0, psudao.getAllSysUsers().size());
    }

    @Test
    public void testUpdate() throws DBException {
        SysUser sysUser = createSUAdmin("login1");
        psudao.insertSysUser(sysUser);
        String newLogin = "login2";
        sysUser.setLogin(newLogin);
        psudao.updateSysUser(sysUser);
        Assert.assertEquals(1, psudao.getAllSysUsers().size());
        Assert.assertEquals(newLogin, psudao.getSysUserByLogin(newLogin).getLogin());
    }
    
    @Ignore("sql compatibiility")
    @Test
    public void testSetGetCertificate() throws DBException {
    	SysUser sysUser = createSUEnrolee("login");
    	psudao.insertSysUser(sysUser);
    	sysUser.setCertificate("certificate");
    	psudao.setCertificate(sysUser);
    	Assert.assertNotNull(sysUser);
    }
    
    @Test
    public void testSetBlockStatusAndSettersAndToString() throws DBException {
    	SysUser su = new SysUser();
    	su.setLogin("login");
    	su.setEmail("email");
    	su.setFullName("fn");
    	su.setPass("pass");
    	su.setUserType("admin");
    	su.setBlocked(false);
    	su.setCertificate("certificate");
    	Assert.assertNotNull(su.getCertificate());
    	psudao.insertSysUser(su);
    	psudao.setBlockStat(su, true);
    	SysUser su2 = psudao.getSysUserByLogin("login");
    	Assert.assertNotNull(su2.toString());
    	Assert.assertEquals(true, su2.isBlocked());
    }

}