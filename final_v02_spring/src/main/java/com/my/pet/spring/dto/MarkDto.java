package com.my.pet.spring.dto;

public class MarkDto {

	private Integer id;
	private Integer sysUserId;
	private Integer subjId;
	private Integer value;
	private String subjectName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Integer getSubjId() {
		return subjId;
	}
	public void setSubjId(Integer markId) {
		this.subjId = markId;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
