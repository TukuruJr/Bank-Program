package com.java.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.java.connectiion.Connect;
import com.java.models.Agents;
import com.java.models.Users;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminLogin implements ActionListener{

	private JFrame frmAdminLogin;
	private JTextField username;
	private JPasswordField password;
	private JButton btnOk;
	private JComboBox comboBox;
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	Connect cn;
	Users user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
					window.frmAdminLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public AdminLogin() throws ClassNotFoundException {
		initialize();
		cn =new Connect();
		cn.GetConnection();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminLogin = new JFrame();
		frmAdminLogin.setResizable(false);
		frmAdminLogin.getContentPane().setBackground(Color.PINK);
		frmAdminLogin.setTitle("Admin Login");
		frmAdminLogin.setBounds(100, 100, 510, 404);
		frmAdminLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminLogin.getContentPane().setLayout(null);
		
		JLabel lblLoginModule = new JLabel("LOGIN MODULE ");
		lblLoginModule.setForeground(Color.BLACK);
		lblLoginModule.setBounds(223, 12, 160, 15);
		frmAdminLogin.getContentPane().add(lblLoginModule);
		
		JLabel label = new JLabel("$$$$$");
		label.setForeground(new Color(0, 128, 0));
		label.setBounds(383, 12, 70, 15);
		frmAdminLogin.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "LOGIN", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBackground(Color.ORANGE);
		panel.setBounds(143, 154, 259, 212);
		frmAdminLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(95, 35, 108, 15);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD/PIN");
		lblPassword.setBounds(95, 103, 135, 15);
		panel.add(lblPassword);
		
		username = new JTextField();
		username.setFont(new Font("Dialog", Font.BOLD, 12));
		username.setForeground(new Color(0, 255, 0));
		username.setBackground(new Color(128, 128, 128));
		username.setBounds(29, 61, 218, 31);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Dialog", Font.BOLD, 12));
		password.setForeground(new Color(0, 255, 0));
		password.setBackground(new Color(128, 128, 128));
		password.setEchoChar('$');
		password.setBounds(29, 130, 218, 31);
		panel.add(password);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setEnabled(true);
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setBackground(new Color(0, 128, 0));
		btnOk.setBounds(147, 175, 100, 25);
		panel.add(btnOk);
		
		JLabel lblRole = new JLabel("ROLE");
		lblRole.setForeground(new Color(0, 0, 0));
		lblRole.setBounds(107, 99, 70, 15);
		frmAdminLogin.getContentPane().add(lblRole);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()==3) {
					  username.setText("Enter National Id..");
				}
				else if(comboBox.getSelectedIndex()==2) {
					 username.setText("Enter Business name..");
				}
			}
		});
		comboBox.setForeground(new Color(255, 255, 0));
		comboBox.setBackground(new Color(255, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "ADMIN", "AGENT", "USER"}));
		comboBox.setBounds(195, 94, 152, 24);
		frmAdminLogin.getContentPane().add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(btnOk)) {
			Validate();
			 int index = comboBox.getSelectedIndex();
			  switch(index) {
			  case 0:
				  JOptionPane.showMessageDialog(null, "Select a role","warning",JOptionPane.WARNING_MESSAGE);
				  break;
				
			  case 1:
				 try {
					if( LoginAdmin(username.getText(),password.getText())) {
						JOptionPane.showMessageDialog(frmAdminLogin, "Login Success!!");
						frmAdminLogin.setVisible(false);
						AdminHome ad = new AdminHome();
						ad.frame.setVisible(true);
					}else {
						  JOptionPane.showMessageDialog(null, "Failed to login","Invalid Credentials",JOptionPane.ERROR_MESSAGE);
					}
					  break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print(e.getMessage());
					} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			  case 2:
				  try {
					  Agents agent = new Agents();
					  agent.setBusinessname(username.getText());
					  agent.setPassword(password.getText());
				    	
					  //check
					  if(LoginAgent(agent.getBusinessname(),agent.getPassword())) {
						  JOptionPane.showMessageDialog(frmAdminLogin, "Login Success!!");
						  frmAdminLogin.setVisible(false);
						  AgentHome ah = new AgentHome();
						  ah.frame.setVisible(true);
						  
					  }else {
						  JOptionPane.showMessageDialog(null, "Failed to login","Invalid Credentials",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  break;
				  
			  case 3:
				  try {
					  user = new Users();
					  user.setIdno(Integer.parseInt(username.getText()));
					  user.setPin(Integer.parseInt(password.getText()));
				    	
					  //check
					  if(LoginUser(user.getIdno(),user.getPin())) {
						  JOptionPane.showMessageDialog(frmAdminLogin, "Login Success!!");
						  frmAdminLogin.setVisible(false);
						  UserHome uh = new UserHome();
						  uh.frmMenu.setVisible(true);
						  
					  }else {
						  JOptionPane.showMessageDialog(null, "Failed to login","Invalid Credentials",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				  break;
				  
				  default:
					  JOptionPane.showMessageDialog(null, "Unable to Login","warning",JOptionPane.WARNING_MESSAGE);
			  }
			
		}
	}
	


	private void Validate() {
		// TODO Auto-generated method stub
		if(username.getText().isBlank()||password.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "All fields Required","warning",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	}


	public boolean LoginAdmin(String user, String pass) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("select * from Admin where username =? and password =?");
		pst.setString(1,user);
		pst.setString(2,pass);
		rs = pst.executeQuery();
		return rs.next();
	}
	
	

	public boolean LoginAgent(String user, String pass) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("select * from Agents where b_name =? and password =?");
		pst.setString(1,user);
		pst.setString(2,pass);
		rs = pst.executeQuery();
		return rs.next();
	}
	
	private boolean LoginUser(int id, int pin) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("select * from Users where idno =? and pin =?");
		pst.setInt(1,id);
		pst.setInt(2,pin);
		rs = pst.executeQuery();
		if( rs.next()) {
			return true;
		}	else {
			return false;
		}
	}

}
