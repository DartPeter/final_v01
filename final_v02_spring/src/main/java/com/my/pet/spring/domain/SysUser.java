package com.my.pet.spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * @author peter
 * System user entity
 *
 */
@Entity
@Table(name = "sys_user")
@NamedQueries({@NamedQuery(name = "userByLogin",
query = "from SysUser s where s.login=:login1")})
public class SysUser implements Serializable {
	
	private static final long serialVersionUID = -2485244225753180884L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "full_name")
    private String fullName;
	@Column(name = "utype")
    private String userType;
    private String login;
    private String pass;
    private String email;
    @Column(name = "is_blocked")
    private boolean isBlocked;
//  private String certificate;
    
    @OneToMany(mappedBy = "sysUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();
    
    @OneToMany(mappedBy = "sysUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks = new ArrayList<>();

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
//		return certificate;
    	return null;
	}

	public void setCertificate(String certificate) {
//		this.certificate = certificate;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
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
