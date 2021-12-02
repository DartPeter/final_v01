package dao.postgres;

import static jdbc.SQLConstant.*;

import dao.AbstractDAO;
import dao.FacultyDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.pet.spring.domain.Faculty;
import com.my.pet.spring.exception.DBException;

/**
 * 
 * @author peter
 * Postgresql implementation of faculty Dao
 *
 */
public class PostgresFacultyDAO extends AbstractDAO implements FacultyDAO {
    private static PostgresFacultyDAO instance;
    
    private PostgresFacultyDAO() {
        // empty
    }

    public static synchronized PostgresFacultyDAO getInstance() {
        if (instance == null) {
            instance = new PostgresFacultyDAO();
        }
        return instance;
    }

    @Override
    public Faculty getFacultyByName(String name) throws DBException {
        Faculty faculty = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_FACULTY);) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                faculty = new Faculty(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("budget_places"), rs.getInt("total_places"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot get faculty!", ex);
        }
        return faculty;
    }

    @Override
    public void insertFaculty(Faculty faculty) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT_NEW_FACULTY, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, faculty.getName());
            ps.setInt(2, faculty.getBudgetPlaces());
            ps.setInt(3, faculty.getTotalPlaces());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                faculty.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
        	if (ex.getMessage().contains("faculty_name_key")) {
        		Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.INFO, "Dublicate name.");
        	} else {
                Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.ERROR, null, ex);
        	}
            throw new DBException("Cannot insert faculty!", ex);
        }

    }

    @Override
    public void updateFaculty(Faculty faculty) throws DBException {
        try (Connection con  = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_FACULTY)) {
            ps.setString(1, faculty.getName());
            ps.setInt(2, faculty.getBudgetPlaces());
            ps.setInt(3, faculty.getTotalPlaces());
            ps.setInt(4, faculty.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        	if (ex.getMessage().contains("faculty_name_key")) {
        		Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.INFO, "Dublicate name.");
        	} else {
                Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.ERROR, null, ex);
        	}
            throw new DBException("Cannot update faculty!", ex);
        }
    }

    @Override
    public void removeFaculty(Faculty faculty) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE_FACULTY);) {
            ps.setInt(1, faculty.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot remove faculty!", ex);
        }
    }

    @Override
    public List<Faculty> getAllFaculties() throws DBException {
        List<Faculty> faculties = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_FACULTIES);) {
            while (rs.next()) {
                Faculty faculty = new Faculty(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("budget_places"), rs.getInt("total_places"));
                faculties.add(faculty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot obtain all faculties!", ex);
        }
        return faculties;
    }
}
