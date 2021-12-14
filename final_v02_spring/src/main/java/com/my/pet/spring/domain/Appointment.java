package com.my.pet.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author peter
 * Appointment entity
 *
 */
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private int uId;
//    private int fId;
//    private String facultyName;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private SysUser sysUser;
	@ManyToOne
	@JoinColumn(name = "f_id")
	private Faculty faculty;
    
//    public Appointment() {
//        //empty
//    }

//    public Appointment(int id, int uId, int fId, String facultyName) {
//        this.id = id;
//        this.uId = uId;
//        this.fId = fId;
//        this.facultyName = facultyName;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

//    public int getuId() {
//        return uId;
//    }
//
//    public void setuId(int uId) {
//        this.uId = uId;
//    }
//
//    public int getfId() {
//        return fId;
//    }
//
//    public void setfId(int fId) {
//        this.fId = fId;
//    }
//
//    public String getFacultyName() {
//        return facultyName;
//    }
//
//    public void setFacultyName(String facultyName) {
//        this.facultyName = facultyName;
//    }
    
    
    
    
    
}
