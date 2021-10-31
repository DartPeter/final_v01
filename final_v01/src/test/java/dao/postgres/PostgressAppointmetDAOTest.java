package dao.postgres;

import static jdbc.SQLConstant.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Appointment;
import entity.Faculty;
import entity.SysUser;
import jdbc.DBException;

public class PostgressAppointmetDAOTest {
    
    private static PostgresSysUserDAO psudao;
    private static PostgresFacultyDAO psfdao;
    private static PostgressAppointmetDAO psadao;
    
    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        psudao = PostgresSysUserDAO.getInstance();
        psfdao = PostgresFacultyDAO.getInstance();
        psadao = PostgressAppointmetDAO.getInstance();
        try (Connection con = psudao.getConnection();
                Statement statement = con.createStatement();) {
            String sql = SQL_CREATE_USER_TABLE + SQL_CREATE_ENROLEE_DETAILS + SQL_CREATE_FACULTY_TABLE + SQL_CREATE_APPOINTMENT_TABLE;
            statement.executeUpdate(sql);
        }
    }
    
    @After
    public void clear() throws SQLException {
        try (Connection con = psudao.getConnection();
             Statement statement = con.createStatement();
        ) {
            String sql = "DELETE FROM appointment;" + 
        "DELETE FROM faculty;" + "DELETE FROM enrolee_details;" + 
        "DELETE FROM sys_user;";
            statement.executeUpdate(sql);
        }
    }

    @Test
    public void testGetUserAppointments() throws DBException {
        SysUser su = new SysUser("qwe", "enrolee", "qwe", "qwe", "qwe");
        psudao.insertSysUser(su);
        Faculty f = new Faculty(0, "fac", 11, 22);
        psfdao.insertFaculty(f);
        psfdao.insertFaculty(new Faculty(0, "qwe", 11, 22));
        List<Appointment> list = psadao.getUserAppointments(su);
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }
    
    @Test
    public void testInsertRemove() throws DBException {
        SysUser su = new SysUser("qwe", "enrolee", "qwe", "qwe", "qwe");
        psudao.insertSysUser(su);
        Faculty f = new Faculty(0, "fac", 11, 22);
        psfdao.insertFaculty(f);
        List<Appointment> list = psadao.getUserAppointments(su);
        Assert.assertEquals(0, list.get(0).getId());
        psadao.insertUserAppointment(su, f);
        list = psadao.getUserAppointments(su);
        Assert.assertNotEquals(0, list.get(0).getId());
        psadao.removeAppointment(list.get(0));
        list = psadao.getUserAppointments(su);
        Assert.assertEquals(0, list.get(0).getId());
    }
    
    @Test
    public void testDummySetGetConstructor() {
    	Appointment app = new Appointment();
    	app.setId(0);
    	app.setfId(0);
    	app.setuId(0);
    	app.setFacultyName("0");
    	Assert.assertArrayEquals(
    			new int[]{0, 0, 0, 0}, 
    			new int[]{app.getId(), app.getfId(), app.getuId(), Integer.parseInt(app.getFacultyName())});
    }

}
