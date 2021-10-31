package dao.postgres;

import static jdbc.SQLConstant.*; 

import entity.Faculty;
import jdbc.DBException;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.*;

public class PostgresFacultyDAOTest {

    private static final String URL_CONNECTION = "jdbc:h2:mem:/testloc;user=youruser;password=yourpassword;DB_CLOSE_DELAY=-1;";

    private static PostgresFacultyDAO pfdao;

    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        pfdao = PostgresFacultyDAO.getInstance();

        try (Connection con = DriverManager.getConnection(URL_CONNECTION);
             Statement statement = con.createStatement();
        ) {
            String sql = SQL_CREATE_FACULTY_TABLE;
            statement.executeUpdate(sql);
        }

    }

    @After
    public void clear() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL_CONNECTION);
             Statement statement = con.createStatement();
        ) {
            String sql = "DELETE FROM faculty";
            statement.executeUpdate(sql);
        }
    }

    @Test
    public void testInsert() throws DBException {
        Faculty f1 = new Faculty("tf1");
        pfdao.insertFaculty(f1);
        Assert.assertEquals(1, pfdao.getAllFaculties().size());
    }

    @Test
    public void testGetByName() throws DBException {
        String name = "tf2";
        Faculty f1 = new Faculty(name);
        pfdao.insertFaculty(f1);
        Faculty f2 = pfdao.getFacultyByName(name);
        Assert.assertEquals(name, f2.getName());
    }

    @Test
    public void testGetAllFaculties() throws DBException {
        pfdao.insertFaculty(new Faculty("tf1"));
        pfdao.insertFaculty(new Faculty("tf2"));
        pfdao.insertFaculty(new Faculty("tf3"));
        Assert.assertEquals(3, pfdao.getAllFaculties().size());
    }

    @Test
    public void testRemove() throws DBException {
        Faculty f1 = new Faculty("tf1");
        pfdao.insertFaculty(f1);
        Assert.assertEquals(1, pfdao.getAllFaculties().size());
        pfdao.removeFaculty(f1);
        Assert.assertEquals(0, pfdao.getAllFaculties().size());
    }

    @Test
    public void testUpdate() throws DBException {
        Faculty f1 = new Faculty("tf1");
        pfdao.insertFaculty(f1);
        String newName = "tf2";
        f1.setName(newName);
        pfdao.updateFaculty(f1);
        Assert.assertEquals(1, pfdao.getAllFaculties().size());
        Assert.assertEquals(newName, pfdao.getFacultyByName(newName).getName());
    }
    
    @Test
    public void testConstructorSomeSetters() throws DBException {
    	Faculty f = new Faculty();
    	f.setName("faculty");
    	f.setTotalPlaces(12);
    	f.setBudgetPlaces(7);
    	pfdao.insertFaculty(f);
    	Assert.assertEquals(1, pfdao.getAllFaculties().size());
    }



}