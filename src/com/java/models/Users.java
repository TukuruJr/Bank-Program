package com.java.models;

public class Users {
	
	private String fullname,mobile;
	private double balance;
	private int idno,pin,account;


	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Users(String fullname, String mobile, double balance, int idno, int pin, int account) {
		super();
		this.fullname = fullname;
		this.mobile = mobile;
		this.balance = balance;
		this.idno = idno;
		this.pin = pin;
		this.account = account;
	}

	

	public String getFullname() {
		return fullname;
	}

	public String getMobile() {
		return mobile;
	}

	public double getBalance() {
		return balance;
	}

	public int getIdno() {
		return idno;
	}

	public int getPin() {
		return pin;
	}

	public int getAccount() {
		return account;
	}


	public void setIdno(int idno) {
		this.idno = idno;
	}


	public void setPin(int pin) {
		this.pin = pin;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public void setAccount(int account) {
		this.account = account;
	}

}
