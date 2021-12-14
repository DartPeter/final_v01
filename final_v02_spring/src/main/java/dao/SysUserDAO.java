package dao;

import java.util.List;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

/**
 * 
 * @author peter
 * This is common interface for system user Dao
 *
 */
public interface SysUserDAO {

    SysUser getSysUserByLogin(String name) throws DBException;
    
    default SysUser getSysUserById(Integer id) throws DBException {
    	return null;
    }
    
    void insertSysUser(SysUser sysUser, String ... details)  throws DBException;
    
    void setBlockStat(SysUser sysUser, boolean flag) throws DBException;
    
    void setCertificate(SysUser sysUser) throws DBException;
    
    String getCertificate(SysUser sysUser) throws DBException;
    
    List<SysUser> getAllSysUsers() throws DBException;

}
