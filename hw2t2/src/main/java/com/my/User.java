package com.my;

public class User {
	
	private String name;
	private String email;
	private double balance;
	
	public User(String name, String email, double balance) {
		super();
		this.name = name;
		this.email = email;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", balance=" + balance + "]";
	}
	
	

}
