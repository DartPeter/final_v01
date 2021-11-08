package com.my.pet.spring.domain;

/**
 * 
 * @author peter
 * Appointment entity
 *
 */
public class Appointment {
    private int id;
    private int uId;
    private int fId;
    private String facultyName;
    
    public Appointment() {
        //empty
    }

    public Appointment(int id, int uId, int fId, String facultyName) {
        this.id = id;
        this.uId = uId;
        this.fId = fId;
        this.facultyName = facultyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    
    
    
}
