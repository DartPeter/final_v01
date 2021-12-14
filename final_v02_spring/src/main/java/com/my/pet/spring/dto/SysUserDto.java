package com.my.pet.spring.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.my.pet.spring.domain.Appointment;

public class SysUserDto {

	private Integer id;
    private String fullName;
    private String userType;
    private String login;
    private String pass;
    private String email;
    private boolean isBlocked;
//  private String certificate;
    private List<AppointmentDto> appointments = new ArrayList<>();
    private List<MarkDto> marks = new ArrayList<>();

    public SysUserDto() {
        // empty
    }

    public SysUserDto(String fullName, String userType, String login, String pass, String email) {
        this.fullName = fullName;
        this.userType = userType;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

//    public SysUserDto(int id, String fullName, String userType, String login, String pass, String email) {
//        this(fullName, userType, login, pass, email);
//        this.id = id;
//    }
//    
//    public SysUserDto(int id, String fullName, String userType, String login, String pass, String email, boolean isBlocked) {
//        this(id, fullName, userType, login, pass, email);
//        this.isBlocked = isBlocked;
//    }

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

	public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

	public List<AppointmentDto> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentDto> appointments) {
		this.appointments = appointments;
	}

	public List<MarkDto> getMarks() {
		return marks;
	}

	public void setMarks(List<MarkDto> marks) {
		this.marks = marks;
	}
    
}
