package dao;

import entity.SysUser;
import jdbc.DBException;
import java.util.List;

/**
 * 
 * @author peter
 * This is common interface for system user Dao
 *
 */
public interface SysUserDAO {

    SysUser getSysUserByLogin(String name)  throws DBException;
    
    void insertSysUser(SysUser sysUser, String ... details)  throws DBException;
    
    void updateSysUser(SysUser sysUser) throws DBException;
    
    void removeSysUser(SysUser sysUser) throws DBException;
    
    void setBlockStat(SysUser sysUser, boolean flag) throws DBException;
    
    void setCertificate(SysUser sysUser) throws DBException;
    
    String getCertificate(SysUser sysUser) throws DBException;
    
    List<SysUser> getAllSysUsers() throws DBException;

}
