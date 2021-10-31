package dao.postgres;

import static jdbc.SQLConstant.*;

import dao.AbstractDAO;
import dao.SysUserDAO;
import entity.SysUser;
import jdbc.DBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author peter
 * Postgresql implementation of system user Dao
 *
 */
public class PostgresSysUserDAO extends AbstractDAO implements SysUserDAO {
    private static PostgresSysUserDAO instance;
    
    private PostgresSysUserDAO() {
        // empty
    }

    public static synchronized PostgresSysUserDAO getInstance() {
        if (instance == null) {
            instance = new PostgresSysUserDAO();
        }
        return instance;
    }

    @Override
    public SysUser getSysUserByLogin(String login) throws DBException {
        SysUser sysUser = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_SYS_USER);) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					sysUser = new SysUser(rs.getInt("id"), rs.getString("full_name"), rs.getString("utype"),
							rs.getString("login"), rs.getString("pass"), rs.getString("email"),
							rs.getBoolean("is_blocked"));
				}
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot obtaion user!", ex);
        }
        return sysUser;
    }

	@Override
	public void insertSysUser(SysUser sysUser, String... details) throws DBException {
		try (Connection con = getConnection()) {
			con.setAutoCommit(false);
			try (PreparedStatement ps1 = con.prepareStatement(SQL_INSERT_NEW_SYS_USER, Statement.RETURN_GENERATED_KEYS);
					PreparedStatement ps2 = con.prepareStatement(SQL_INSERT_NEW_USER_DETAILS)) {
				ps1.setString(1, sysUser.getFullName());
				ps1.setString(2, sysUser.getUserType());
				ps1.setString(3, sysUser.getLogin());
				ps1.setString(4, sysUser.getPass());
				ps1.setString(5, sysUser.getEmail());
				ps1.execute();
				try (ResultSet rs = ps1.getGeneratedKeys()) {
					rs.next();
					sysUser.setId(rs.getInt(1));
				}
				if (sysUser.getId() > 0 && details != null && details.length > 0) {
					ps2.setInt(1, sysUser.getId());
					ps2.setString(2, details[0]);
					ps2.setString(3, details[1]);
					ps2.setString(4, details[2]);
					ps2.execute();
				}
				con.commit();
			} catch (SQLException ex) {
				con.rollback();
				if (ex.getMessage().contains("sys_user_login_key")) {
					Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.INFO, "Dublicate name.");
				} else {
					Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
				}
				throw new DBException("Cannot insert user!", ex);
			} finally {
				con.setAutoCommit(true);
			}
		} catch (SQLException ex) {
			Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
			throw new DBException("Cannot insert user's details", ex);
		}
	}

    @Override
    public void updateSysUser(SysUser sysUser) throws DBException {
        try (Connection con  = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_SYS_USER)) {
            ps.setString(1, sysUser.getFullName());
            ps.setString(2, sysUser.getUserType());
            ps.setString(3, sysUser.getLogin());
            ps.setString(4, sysUser.getPass());
            ps.setString(5, sysUser.getEmail());
            ps.setInt(6, sysUser.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot update user!", ex);
        }
    }

    @Override
    public void removeSysUser(SysUser sysUser) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE_SYS_USER);) {
            ps.setInt(1, sysUser.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot remove user!", ex);
        }
    }

    @Override
    public List<SysUser> getAllSysUsers() throws DBException {
        List<SysUser> sysUsers = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_SYS_USERS);) {
            while (rs.next()) {
                SysUser sysUser = new SysUser(
                        rs.getInt("id"), rs.getString("full_name"),
                        rs.getString("utype"), rs.getString("login"),
                        rs.getString("pass"), rs.getString("email"),
                        rs.getBoolean("is_blocked"));
                sysUsers.add(sysUser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFacultyDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot obtaion all users!", ex);
        }
        return sysUsers;
    }

    @Override
    public void setBlockStat(SysUser sysUser, boolean flag) throws DBException {
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SET_BLOCK_STAT);) {
            ps.setBoolean(1, flag);
            ps.setInt(2, sysUser.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot block user!", ex);
        }
    }

	@Override
	public void setCertificate(SysUser sysUser) throws DBException {
		try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SET_CERTIFICATE);) {
			ps.setString(1, sysUser.getCertificate());
            ps.setInt(2, sysUser.getId());
            ps.setInt(3, sysUser.getId());
            ps.setString(4, sysUser.getCertificate());
            ps.setInt(5, sysUser.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot attach certificate!", ex);
        }
	}

	@Override
	public String getCertificate(SysUser sysUser) throws DBException {
		String result = null;
		try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_GET_CERTIFICATE);) {
            ps.setInt(1, sysUser.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	result = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSysUserDAO.class.getName()).log(Level.ERROR, null, ex);
            throw new DBException("Cannot get certificate!", ex);
        }
		return result;
	}
}
