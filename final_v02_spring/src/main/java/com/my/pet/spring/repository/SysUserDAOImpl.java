package com.my.pet.spring.repository;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.pet.spring.domain.SysUser;
import com.my.pet.spring.exception.DBException;

import dao.SysUserDAO;

@Repository
public class SysUserDAOImpl implements SysUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SysUser getSysUserByLogin(String name) throws DBException {
		Session session = sessionFactory.getCurrentSession();
		List list = session.getNamedQuery("userByLogin").setParameter("login1", name).getResultList();
		if (!list.isEmpty()) {
			return (SysUser) list.get(0);
		} else {
			return new SysUser();
		}
	}
	
	@Override
	public SysUser getSysUserById(Integer id) throws DBException {
		Session session = sessionFactory.getCurrentSession();
		SysUser sysUser = session.get(SysUser.class, id);
		return Objects.requireNonNull(sysUser, "User not found by id: " + id);
	}

	@Override
	@Transactional
	public void insertSysUser(SysUser sysUser, String... details) throws DBException {
		// TODO process details later
		sessionFactory.getCurrentSession().save(sysUser);
	}

	@Override
	public void setBlockStat(SysUser sysUser, boolean flag) throws DBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCertificate(SysUser sysUser) throws DBException {
		// TODO Auto-generated method stub
	}

	@Override
	public String getCertificate(SysUser sysUser) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<SysUser> getAllSysUsers() throws DBException {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM SysUser ORDER BY id", SysUser.class).getResultList();
	}

}
