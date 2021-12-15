package com.hiber.entity;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class CreditCard extends BillingDetails {
	
	String cardNumber;
	int expYear;
	int expMonth;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	public int getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(cardNumber, other.cardNumber) && expMonth == other.expMonth && expYear == other.expYear;
	}
	
	
	
}
