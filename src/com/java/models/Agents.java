package com.java.models;

public class Agents {
	
private String businessname,location,owner,password;
private int idno;


	public String getPassword() {
	return password;
}
	
	public Agents() {
		// TODO Auto-generated constructor stub
	}
	
	public Agents(String businessname, String location, String owner, String password, int idno) {
		super();
		this.businessname = businessname;
		this.location = location;
		this.owner = owner;
		this.password = password;
		this.idno = idno;
	}

	

	public String getBusinessname() {
		return businessname;
	}

	public String getLocation() {
		return location;
	}

	public String getOwner() {
		return owner;
	}

	public int getIdno() {
		return idno;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
