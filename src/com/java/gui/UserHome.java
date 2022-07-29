package com.java.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.java.connectiion.Connect;
import com.java.models.Statement;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;

public class UserHome implements ActionListener{

	public JFrame frmMenu;
	private JButton btnDeposit,btnTransfer,btnWithdraw,btnStat,btnExit,btnSend;
	private JTextField bal;
	private JLabel lblAccountBalance;
	private JTextField searchAcc;
	private JTextField name;
	private JTextField email;
	private JTextField mobile;
	private JTextField money;
	private Connection connect ;
	private String accholder;
	private double holder_bal;
	private Connect cn;
	private PreparedStatement pst;
	private double balance,recipient;
	private JTextField holder;
	private ResultSet rs;

	
	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public UserHome() throws ClassNotFoundException {
		initialize();
		cn = new Connect();
		cn.GetConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException {
		frmMenu = new JFrame();
		frmMenu.setResizable(false);
		frmMenu.getContentPane().setBackground(new Color(85, 107, 47));
		frmMenu.setTitle("Menu");
		frmMenu.setBounds(100, 100, 719, 432);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JLabel lblAccountOperations = new JLabel("ACCOUNT OPERATIONS");
		lblAccountOperations.setForeground(new Color(0, 255, 255));
		lblAccountOperations.setBounds(282, 12, 256, 15);
		frmMenu.getContentPane().add(lblAccountOperations);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "OPERATIONS", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(218, 112, 214)));
		panel.setBackground(new Color(139, 69, 19));
		panel.setBounds(24, 49, 279, 333);
		frmMenu.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setEnabled(false);
		btnDeposit.setForeground(new Color(255, 255, 0));
		btnDeposit.setBackground(new Color(0, 0, 139));
		btnDeposit.setBounds(12, 23, 241, 38);
		btnDeposit.addActionListener(this);
		panel.add(btnDeposit);
		
		btnWithdraw = new JButton("WITHDRAW");
		btnWithdraw.setEnabled(false);
		btnWithdraw.setForeground(Color.YELLOW);
		btnWithdraw.setBackground(new Color(0, 0, 139));
		btnWithdraw.setBounds(12, 81, 241, 38);
		btnWithdraw.addActionListener(this);
		panel.add(btnWithdraw);
		
		btnStat = new JButton("LAST TRANSACTION");
		btnStat.setEnabled(false);
		btnStat.setForeground(Color.YELLOW);
		btnStat.setBackground(new Color(0, 0, 139));
		btnStat.setBounds(12, 205, 241, 38);
		btnStat.addActionListener(this);
		panel.add(btnStat);
		
	    btnTransfer = new JButton("TRANSFER FUNDS");
	    btnTransfer.setEnabled(false);
		btnTransfer.setForeground(Color.YELLOW);
		btnTransfer.setBackground(new Color(0, 0, 139));
		btnTransfer.setBounds(12, 144, 241, 38);
		btnTransfer.addActionListener(this);
		panel.add(btnTransfer);
		
		btnExit = new JButton("EXIT");
		btnExit.setForeground(Color.YELLOW);
		btnExit.setBackground(new Color(0, 0, 139));
		btnExit.setBounds(12, 268, 241, 38);
		btnExit.addActionListener(this);
		panel.add(btnExit);
		
		JLabel label = new JLabel("$$$$$");
		label.setForeground(new Color(255, 255, 0));
		label.setBounds(556, 12, 70, 15);
		frmMenu.getContentPane().add(label);
		
		bal = new JTextField();
		bal.setText("0.00");
		bal.setForeground(new Color(0, 0, 0));
		bal.setEditable(false);
		bal.setBounds(461, 375, 233, 19);
		frmMenu.getContentPane().add(bal);
		bal.setColumns(10);
		
		lblAccountBalance = new JLabel("ACCOUNT BALANCE");
		lblAccountBalance.setForeground(new Color(255, 255, 0));
		lblAccountBalance.setBounds(502, 359, 159, 15);
		frmMenu.getContentPane().add(lblAccountBalance);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "TRANSFER CASH", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(319, 92, 388, 255);
		frmMenu.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		searchAcc = new JTextField();
		searchAcc.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		searchAcc.setBackground(new Color(255, 255, 255));
		searchAcc.setEnabled(false);
		searchAcc.setBounds(170, 24, 206, 29);
		searchAcc.addActionListener(this);
		panel_1.add(searchAcc);
		searchAcc.setColumns(10);
		
		JLabel lblRecipientsAccountNo = new JLabel("RECIPIENTS ACCOUNT NO");
		lblRecipientsAccountNo.setForeground(new Color(255, 51, 153));
		lblRecipientsAccountNo.setFont(new Font("Dialog", Font.BOLD, 10));
		lblRecipientsAccountNo.setBounds(12, 32, 152, 15);
		panel_1.add(lblRecipientsAccountNo);
		
		JLabel lblName = new JLabel("HOLDER'S NAME");
		lblName.setFont(new Font("Dialog", Font.BOLD, 10));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(12, 59, 117, 30);
		panel_1.add(lblName);
		
		name = new JTextField();
		name.setEditable(false);
		name.setColumns(10);
		name.setBounds(123, 65, 253, 29);
		panel_1.add(name);
		
		JLabel lblEmail = new JLabel("ACCOUNT");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEmail.setBounds(12, 101, 117, 30);
		panel_1.add(lblEmail);
		
		email = new JTextField();
		email.setEditable(false);
		email.setColumns(10);
		email.setBounds(123, 106, 253, 29);
		panel_1.add(email);
		
		JLabel lblMobile = new JLabel("HOLDER'S MOBILE");
		lblMobile.setForeground(Color.BLACK);
		lblMobile.setFont(new Font("Dialog", Font.BOLD, 10));
		lblMobile.setBounds(12, 148, 107, 30);
		panel_1.add(lblMobile);
		
		mobile = new JTextField();
		mobile.setEditable(false);
		mobile.setColumns(10);
		mobile.setBounds(123, 147, 253, 29);
		panel_1.add(mobile);
		
		JLabel lblAmountToTransfer = new JLabel("AMOUNT TO TRANSFER");
		lblAmountToTransfer.setForeground(new Color(255, 0, 153));
		lblAmountToTransfer.setFont(new Font("Dialog", Font.BOLD, 10));
		lblAmountToTransfer.setBounds(25, 188, 152, 15);
		panel_1.add(lblAmountToTransfer);
		
		money = new JTextField();
		money.setEditable(false);
		money.setColumns(10);
		money.setBounds(178, 180, 198, 29);
		panel_1.add(money);
		
	    btnSend = new JButton("MAKE TRANSFER");
		btnSend.setEnabled(false);
		btnSend.setForeground(Color.RED);
		btnSend.setBackground(Color.GREEN);
		btnSend.setBounds(216, 218, 160, 25);
		btnSend.addActionListener(this);
		panel_1.add(btnSend);
		
		JLabel lblTransferMoneyTo = new JLabel("TRANSFER MONEY TO ONOTHER ACCOUNT  FRANCODE BANK");
		lblTransferMoneyTo.setFont(new Font("Dialog", Font.BOLD, 11));
		lblTransferMoneyTo.setForeground(Color.RED);
		lblTransferMoneyTo.setBounds(319, 77, 388, 14);
		frmMenu.getContentPane().add(lblTransferMoneyTo);
		
		holder = new JTextField();
		holder.setFont(new Font("Dialog", Font.BOLD, 12));
		holder.setForeground(Color.CYAN);
		holder.setBackground(Color.BLACK);
		holder.setText("Card Here....");
		holder.setBounds(339, 39, 355, 19);
		holder.addActionListener(this);
		frmMenu.getContentPane().add(holder);
		holder.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		Object button = ev.getSource();
		if(button.equals(holder)) {
         try {
			if( SearchAcc(Integer.parseInt(holder.getText()))) {
				bal.setText(rs.getDouble(4)+"");
				btnDeposit.setEnabled(true);
				btnTransfer.setEnabled(true);
				btnTransfer.setEnabled(true);
				btnStat.setEnabled(true);
				btnWithdraw.setEnabled(true);
			}else {
				JOptionPane.showMessageDialog(frmMenu, "Account not Found!!","warning",JOptionPane.WARNING_MESSAGE);
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if(button.equals(btnDeposit)) {
			double amt = Double.parseDouble(JOptionPane.showInputDialog("Emount to Deposit"));
			try {
				Deposit(amt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		else if(button.equals(btnWithdraw)) {
			double amt = Double.parseDouble(JOptionPane.showInputDialog("Emount to Withdraw"));
			try {
				Withdraw(amt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		else if(button.equals(btnTransfer)) {
			searchAcc.setEnabled(true);
		}
		
		
		else if(button.equals(btnStat)) {
			try {
				int acc = Integer.parseInt(holder.getText()); 
				ShowReciep(acc);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		
		else if(button.equals(searchAcc)) {
           try {
			SearchAccount(Integer.parseInt(searchAcc.getText()));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           }
		

		else if(button.equals(btnSend)) {
			try {
				Transfer(Double.parseDouble(money.getText()));
			} catch (NumberFormatException | HeadlessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void SearchAccount(int parseInt) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("select * from Users where account = ?");
		pst.setInt(1, parseInt);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
	    btnSend.setEnabled(true);
		name.setText(rs.getString(1));
		email.setText(rs.getInt(3)+"");
		recipient = rs.getDouble(4);
		mobile.setText(rs.getString(5));
		money.setEditable(true);
		
		}else {
			JOptionPane.showMessageDialog(null, "Account not Found","warning",JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	private boolean Deposit(double parseDouble) throws SQLException {
		// TODO Auto-generated method stub
	  if(parseDouble<5) {
		  JOptionPane.showMessageDialog(frmMenu, "Minimun of USD 5 Allowed!","warning",JOptionPane.WARNING_MESSAGE);
		  return false;
	  }else {
		  double 	initbal = Double.parseDouble(bal.getText());
		   balance = (parseDouble+initbal);
		    
		   //update balance in the database
		   if( UpdateBalance(balance, Integer.parseInt(holder.getText()))) {
			   JOptionPane.showMessageDialog(frmMenu, "Deposit Success!!"+"\n"+"New Balance "+balance);
			   bal.setText(balance+"");
			   
			   //add the statement
			   AddStatement(Integer.parseInt(holder.getText()),LocalDate.now().toString(),"DEPOSIT ",parseDouble);
		   }else {
			   JOptionPane.showMessageDialog(frmMenu, "Problem Encountered Try Later","warning",JOptionPane.WARNING_MESSAGE);
			   
		   }
		   return true;
	  }
		
	}
	
	
	private boolean Withdraw(double amount) throws NumberFormatException, HeadlessException, SQLException {
		// TODO Auto-generated method stub
		double curr_bal = Double.parseDouble(bal.getText());
		   
		  if(amount>curr_bal) {
			  JOptionPane.showMessageDialog(frmMenu, "Low Balance !"+"\n"+"Available Balance "+bal.getText(),"warning",JOptionPane.WARNING_MESSAGE);
			  return false;
		  }else {
			   balance = (curr_bal-amount);
			    
			   //update balance in the database
			   if( UpdateBalance(balance, Integer.parseInt(holder.getText()))) {
				   JOptionPane.showMessageDialog(frmMenu, "Withdrawal Success!!"+"\n"+"New Balance "+balance);
				   bal.setText(balance+"");
				   
				   
				   //add the statement
				   AddStatement(Integer.parseInt(holder.getText()),LocalDate.now().toString(),"WITHDRAWAL ",amount);
			   }else {
				   JOptionPane.showMessageDialog(frmMenu, "Problem Encountered Try Later","warning",JOptionPane.WARNING_MESSAGE);
				   
			   }
			   return true;
		  }
	}
	
	
	private boolean Transfer(double amount) throws NumberFormatException, HeadlessException, SQLException, InterruptedException {
		double curr_bal = Double.parseDouble(bal.getText());
	   
	  if(amount>curr_bal) {
		  JOptionPane.showMessageDialog(frmMenu, "Low Balance for Transfer!"+"\n"+"Available Balance "+bal.getText(),"warning",JOptionPane.WARNING_MESSAGE);
		  return false;
	  }else {
		   balance = (curr_bal-amount);
		 
		    	 //update balance in the database
				   if( UpdateBalance(balance, Integer.parseInt(holder.getText()))) {
					   //sleep for two seconds
					     Thread tr =   new Thread();
					      tr.sleep(2000);

							  JOptionPane.showMessageDialog(frmMenu, "Transfer Success!!"+"\n"+"New Balance "+balance);
							   bal.setText(balance+"");
							   
							   //add the statement
							   AddStatement(Integer.parseInt(holder.getText()),LocalDate.now().toString(),"Transfer to "+searchAcc.getText(),amount);
					   //update Recipients Balance
					   recipient = recipient+amount;
				       UpdateBalance(recipient,Integer.parseInt(searchAcc.getText()));
				       
					   //add the statement
					   AddStatement(Integer.parseInt(searchAcc.getText()),LocalDate.now().toString(),"Recieved from "+holder.getText(),amount);
				   }else {
					   JOptionPane.showMessageDialog(frmMenu, "Problem Encountered Try Later","warning",JOptionPane.WARNING_MESSAGE);
					   
				   }
		   return true;
	  }
	}
	
	
	private boolean SearchAcc(int parseInt) throws SQLException {
		// TODO Auto-generated method stub
		pst = cn.conn.prepareStatement("select * from Users where account = ?");
		pst.setInt(1, parseInt);
	    rs =pst.executeQuery();
		if(rs.next()) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean UpdateBalance(Double bal, int acc) throws SQLException {
		
		    pst = cn.conn.prepareStatement("update Users set balance = ? where account = ?");
			pst.setDouble(1, bal);
			pst.setInt(2, acc);
			int st = pst.executeUpdate();
			if(st>0) {
				// update success
				return true;
			}else {
				return false;
			}
	}
	
	private void AddStatement(int acc, String date,String type,double amount) throws SQLException {
		Statement stm = new Statement(acc, date, type, amount);
		pst = cn.conn.prepareStatement("insert into statements values(?,?,?,?,?)");
		pst.setString(1, null);
		pst.setInt(2, stm.getAccount());
		pst.setString(3, stm.getDate());
		pst.setString(4, stm.getType());
		pst.setDouble(5, stm.getAmount());
		pst.execute();
	}
	
	private void ShowReciep(int acc) throws SQLException {
		Statement stm = null;
		 String state = "";
		HashMap<Integer,Statement> map = new HashMap<Integer,Statement>();
		pst = cn.conn.prepareStatement("select * from statements where account = ? order by date");
		pst.setInt(1, acc);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			int key = rs.getInt(1);
			int account = rs.getInt(2);
			String date = rs.getString(3);
			String type = rs.getString(4);
			double amount = rs.getDouble(5);
			stm = new Statement(account,date,type,amount);
			
			//add map
			map.put(key, stm);
		}
		 for(int i: map.keySet()) {
			 Statement statement = map.get(i);
			System.out.println(statement.toString());
		 }
	}
		
	}
