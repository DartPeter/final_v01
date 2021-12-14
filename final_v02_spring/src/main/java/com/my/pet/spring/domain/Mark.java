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
 * Mark entity
 *
 */
@Entity
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private int userId;
//    private int subjId;
    private Integer value;
//    private String subjName;
    @ManyToOne
	@JoinColumn(name = "user_id")
	private SysUser sysUser;
    @ManyToOne
	@JoinColumn(name = "subj_id")
    private Subject subject;
    
    public Mark() {
        // empty
    }
    
//    public Mark(int id, int userId, int subjId, int value, String subjName) {
//        this.id = id;
//        this.userId = userId;
//        this.subjId = subjId;
//        this.value = value;
//        this.subjName = subjName;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getSubjId() {
//        return subjId;
//    }
//
//    public void setSubjId(int subjId) {
//        this.subjId = subjId;
//    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

//    public String getSubjName() {
//        return subjName;
//    }
//
//    public void setSubjName(String subjName) {
//        this.subjName = subjName;
//    }

}
