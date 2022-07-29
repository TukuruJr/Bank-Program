package com.java.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.java.connectiion.Connect;
import com.java.models.Users;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

public class AgentHome implements ActionListener{

	public JFrame frame;
	private JTextField name;
	private JTextField id;
	private JTextField acc;
	private JTextField bal;
	private JTextField pin;
	private JTextField mobile;
	private JTable table;
	private JButton Register,Update;
	Connect cn;
	private JButton btnClear;
    PreparedStatement pst;
    private JLabel lblName_1;
    private JLabel lblName_2;
    private JLabel lblName_3;
    private JLabel lblName_4;
    private JLabel lblName_5;
    private JLabel lblName_6;
	

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public AgentHome() throws ClassNotFoundException, SQLException {
		initialize();
		cn = new Connect();
		cn.GetConnection();
		LoadTable(table);
		
		lblName_1 = new JLabel("NAME");
		lblName_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName_1.setBounds(379, 47, 55, 15);
		frame.getContentPane().add(lblName_1);
		
		lblName_2 = new JLabel("ID");
		lblName_2.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName_2.setBounds(446, 47, 44, 15);
		frame.getContentPane().add(lblName_2);
		
		lblName_3 = new JLabel("ACCOUNT");
		lblName_3.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName_3.setBounds(514, 47, 84, 15);
		frame.getContentPane().add(lblName_3);
		
		lblName_4 = new JLabel("BALANCE");
		lblName_4.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName_4.setBounds(622, 47, 70, 15);
		frame.getContentPane().add(lblName_4);
		
		lblName_5 = new JLabel("PIN");
		lblName_5.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName_5.setBounds(704, 47, 44, 15);
		frame.getContentPane().add(lblName_5);
		
		lblName_6 = new JLabel("MOBILE");
		lblName_6.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName_6.setBounds(748, 46, 55, 15);
		frame.getContentPane().add(lblName_6);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 0));
		frame.setBounds(100, 100, 815, 448);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "USERS OPERATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(23, 12, 338, 398);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(12, 39, 59, 15);
		panel.add(lblName);
		
		name = new JTextField();
		name.setBounds(89, 30, 233, 33);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblIdNo = new JLabel("ID NO:");
		lblIdNo.setBounds(26, 104, 59, 15);
		panel.add(lblIdNo);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(89, 89, 233, 33);
		panel.add(id);
		
		JLabel lblAccount = new JLabel("ACCOUNT");
		lblAccount.setBounds(12, 159, 76, 15);
		panel.add(lblAccount);
		
		acc = new JTextField();
		acc.setFont(new Font("Dialog", Font.BOLD, 10));
		acc.setForeground(new Color(255, 255, 0));
		acc.setBackground(new Color(128, 128, 128));
		acc.setColumns(10);
		acc.setBounds(89, 150, 233, 33);
		panel.add(acc);
		
		JLabel lblBalance = new JLabel("BALANCE");
		lblBalance.setBounds(12, 219, 76, 15);
		panel.add(lblBalance);
		
		bal = new JTextField();
		bal.setText("0.0");
		bal.setColumns(10);
		bal.setBounds(89, 210, 233, 33);
		panel.add(bal);
		
		JLabel lblPin = new JLabel("PIN");
		lblPin.setBounds(26, 274, 59, 15);
		panel.add(lblPin);
		
		pin = new JTextField();
		pin.setColumns(10);
		pin.setBounds(89, 265, 233, 33);
		panel.add(pin);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(89, 319, 233, 33);
		panel.add(mobile);
		
		JLabel lblMobile = new JLabel("MOBILE");
		lblMobile.setBounds(9, 328, 76, 15);
		panel.add(lblMobile);
		
		Register = new JButton("REGISTER");
		Register.setForeground(new Color(255, 255, 255));
		Register.setBackground(new Color(0, 128, 0));
		Register.setBounds(123, 364, 99, 25);
		Register.addActionListener(this);
		panel.add(Register);
		
		Update = new JButton("UPDATE");
		Update.setBackground(new Color(0, 128, 0));
		Update.setForeground(new Color(255, 255, 255));
		Update.setBounds(234, 364, 99, 25);
		Update.addActionListener(this);
		panel.add(Update);
		
		btnClear = new JButton("CLEAR");
		btnClear.setBackground(new Color(128, 0, 0));
		btnClear.setForeground(new Color(0, 255, 255));
		btnClear.setBounds(19, 364, 91, 25);
		btnClear.addActionListener(this);
		panel.add(btnClear);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				name.setText((String)table.getValueAt(row, 0));
				id.setText(table.getValueAt(row, 1)+"");
				acc.setText(table.getValueAt(row, 2)+"");
				bal.setText(table.getValueAt(row, 3)+"");
				pin.setText(table.getValueAt(row, 4)+"");
				mobile.setText((String)table.getValueAt(row, 5));
			}
		});
		table.setBounds(373, 67, 430, 343);
		frame.getContentPane().add(table);
		
		JLabel lblUsersRegistered = new JLabel("USERS REGISTERED");
		lblUsersRegistered.setForeground(new Color(0, 255, 255));
		lblUsersRegistered.setBounds(493, 12, 145, 15);
		frame.getContentPane().add(lblUsersRegistered);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		Users user = new Users(name.getText(),mobile.getText(),Double.parseDouble(bal.getText()),Integer.parseInt(id.getText()),Integer.parseInt(pin.getText()),Integer.parseInt(acc.getText()));
		
		Object button = ev.getSource();
		if(button.equals(Register)) {
			try {
				//check
				if(Check(user.getIdno()).next()) {
					//user exists
					JOptionPane.showMessageDialog(frame, "ID Number Already Registered","warning",JOptionPane.WARNING_MESSAGE);
				}else {
					if(RegisterUser(user.getFullname(), user.getIdno(),user.getAccount(), user.getBalance(), user.getPin(), user.getMobile())) {
						
						//success
						JOptionPane.showMessageDialog(frame, "Registration successful!!");
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(frame, "Unable to Register!!","warning",JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(button.equals(Update)) {
			try {
				UpdateUser(user.getFullname(),user.getAccount(), user.getMobile(), user.getIdno());
				LoadTable(table);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//clear
		else if(button.equals(btnClear)) {
			name.setText("");
			id.setText("");
			mobile.setText("");
			acc.setText("");
			bal.setText("");
			pin.setText("");
		}
	}
	
	

	

	private void LoadTable(JTable table2) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("select * from Users");
		table2.setModel(DbUtils.resultSetToTableModel(pst.executeQuery()));
		
	}

	private boolean RegisterUser(String fullname, int idno, int account, double balance, int pin2, String mobile2) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("insert into Users values(?,?,?,?,?,?)");
		pst.setString(1, fullname);
		pst.setInt(2, idno);
		pst.setInt(3,account);
		pst.setDouble(4, balance);
		pst.setInt(5, pin2);
		pst.setString(6, mobile2);
		int ok = pst.executeUpdate();
		if(ok>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	private boolean UpdateUser(String fullname, int account, String mobile2, int idno) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("update Users set fullname=?,account=?,mobile=? where idno=?");
		pst.setString(1, fullname);
		pst.setInt(2,account);
		pst.setString(3, mobile2);
		pst.setInt(4, idno);
		int ok = pst.executeUpdate();
		if(ok>0) {
			return true;
		}else {
			return false;
		}
	}

	private ResultSet Check(int id) throws SQLException {
		//check
		pst = cn.conn.prepareStatement("select * from Users where idno=?");
		pst.setInt(1, id);
		return pst.executeQuery();
	}

}
