package com.my.pet.spring.domain;

import java.io.Serializable;

/**
 * 
 * @author peter
 * System user entity
 *
 */
public class SysUser implements Serializable {
	
	private static final long serialVersionUID = -2485244225753180884L;
	
	private int id;
    private String fullName;
    private String userType;
    private String login;
    private String pass;
    private String email;
    private String certificate;
    private boolean isBlocked;

    public SysUser() {
        // empty
    }

    public SysUser(String fullName, String userType, String login, String pass, String email) {
        this.fullName = fullName;
        this.userType = userType;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public SysUser(int id, String fullName, String userType, String login, String pass, String email) {
        this(fullName, userType, login, pass, email);
        this.id = id;
    }
    
    public SysUser(int id, String fullName, String userType, String login, String pass, String email, boolean isBlocked) {
        this(id, fullName, userType, login, pass, email);
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "SysUser [id=" + id + ", fullName=" + fullName + ", userType=" + userType + ", login=" + login
                + ", pass=" + pass + ", email=" + email + ", isBlocked=" + isBlocked + "]";
    }
    
    
    
    
}
