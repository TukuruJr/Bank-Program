package com.java.connectiion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect {
	public  Connection conn;
	public PreparedStatement ps;

	public Connect() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void GetConnection() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ATMPROJECT","root","");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
	}

}
