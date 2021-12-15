package com.hiber.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Buyer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buyer_seq")
	Long id;
	
	String firstName;
	String lastName;
	
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
	List<BillingDetails> billingDetails = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<BillingDetails> getBillingDetails() {
		return billingDetails;
	}
	public void setBillingDetails(List<BillingDetails> billingDetails) {
		this.billingDetails = billingDetails;
	}
	
}
