package com.java.models;

public class Statement {

	private int account;
	private String date,type;
	private double amount;
	
	public Statement(int account, String date, String type, double amount) {
		super();
		this.account = account;
		this.date = date;
		this.type = type;
		this.amount = amount;
	}
	public int getAccount() {
		return account;
	}
	public String getDate() {
		return date;
	}
	public String getType() {
		return type;
	}
	public double getAmount() {
		return amount;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Statement [account=" + this.account + ", date=" +this. date + ", type=" + this.type + ", amount=" + this.amount + "]";
	}
	

}
