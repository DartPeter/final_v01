package com.hiber.entity;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class BankAccount extends BillingDetails {
	
	String account;
	String bandName;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(account, bandName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(account, other.account) && Objects.equals(bandName, other.bandName);
	}
	
	
}
