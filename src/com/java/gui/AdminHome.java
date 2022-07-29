package com.java.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.java.connectiion.Connect;
import com.java.models.Agents;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminHome implements ActionListener {

	public JFrame frame;
	private JTextField name;
	private JTextField location;
	private JTextField owner;
	private JTextField ownerid;
	private JTextField pass;
	private JTable table;
	private JTable users;
	private JButton Register,Update,Delete;
	Connect cn;
	PreparedStatement pst;
	private JLabel lblLocation_1;
	private JLabel lblLocation_2;
	private JLabel lblLocation_3;
	private JLabel lblLocation_4;
	private JLabel lblLocation_5;
	private JLabel lblLocation_6;
	private JLabel lblSr;
	private JTextField sr;
	private JButton btnClear;


	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public AdminHome() throws ClassNotFoundException, SQLException {
		initialize();
		cn =new Connect();
		cn.GetConnection();
		LoadTable(table,"Agents");
		LoadTable(users,"Users");
		
		lblLocation_1 = new JLabel("location");
		lblLocation_1.setForeground(new Color(175, 238, 238));
		lblLocation_1.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 11));
		lblLocation_1.setBounds(542, 23, 70, 15);
		frame.getContentPane().add(lblLocation_1);
		
		lblLocation_2 = new JLabel("Owner");
		lblLocation_2.setForeground(new Color(175, 238, 238));
		lblLocation_2.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 11));
		lblLocation_2.setBounds(611, 23, 54, 15);
		frame.getContentPane().add(lblLocation_2);
		
		lblLocation_3 = new JLabel("Owner Id");
		lblLocation_3.setForeground(new Color(175, 238, 238));
		lblLocation_3.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 11));
		lblLocation_3.setBounds(668, 23, 70, 15);
		frame.getContentPane().add(lblLocation_3);
		
		lblLocation_4 = new JLabel(" Password");
		lblLocation_4.setForeground(new Color(175, 238, 238));
		lblLocation_4.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 11));
		lblLocation_4.setBounds(736, 23, 70, 15);
		frame.getContentPane().add(lblLocation_4);
		
		lblLocation_5 = new JLabel("SR.");
		lblLocation_5.setForeground(new Color(175, 238, 238));
		lblLocation_5.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 11));
		lblLocation_5.setBounds(409, 22, 29, 15);
		frame.getContentPane().add(lblLocation_5);
		
		lblLocation_6 = new JLabel("B.Name");
		lblLocation_6.setForeground(new Color(175, 238, 238));
		lblLocation_6.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 11));
		lblLocation_6.setBounds(474, 23, 70, 15);
		frame.getContentPane().add(lblLocation_6);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name.setText("");
				location.setText("");
				sr.setText("");
				owner.setText("");
				ownerid.setText("");
				pass.setText("");
			}
		});
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(25, 25, 112));
		btnClear.setBounds(134, 398, 117, 25);
		frame.getContentPane().add(btnClear);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(85, 107, 47));
		frame.setBounds(100, 100, 818, 461);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminHome = new JLabel("ADMIN HOME");
		lblAdminHome.setForeground(new Color(0, 0, 0));
		lblAdminHome.setBounds(337, 12, 190, 15);
		frame.getContentPane().add(lblAdminHome);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "AGENTS OPERATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 215, 0));
		panel.setBounds(12, 39, 374, 342);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buss_Name");
		lblNewLabel.setBounds(12, 44, 108, 15);
		panel.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(138, 32, 224, 29);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(23, 101, 108, 15);
		panel.add(lblLocation);
		
		location = new JTextField();
		location.setColumns(10);
		location.setBounds(138, 94, 224, 29);
		panel.add(location);
		
		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setBounds(23, 154, 108, 15);
		panel.add(lblOwner);
		
		JLabel lblOwnerId = new JLabel("Owner Id");
		lblOwnerId.setBounds(23, 209, 108, 15);
		panel.add(lblOwnerId);
		
		owner = new JTextField();
		owner.setColumns(10);
		owner.setBounds(138, 147, 224, 29);
		panel.add(owner);
		
		ownerid = new JTextField();
		ownerid.setColumns(10);
		ownerid.setBounds(138, 195, 224, 29);
		panel.add(ownerid);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(23, 264, 97, 15);
		panel.add(lblNewLabel_1);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(138, 250, 224, 29);
		panel.add(pass);
		
		Register = new JButton("REGISTER");
		Register.setBounds(12, 301, 117, 25);
		Register.addActionListener(this);
		panel.add(Register);
		
		 Update = new JButton("UPDATE");
		Update.setBounds(138, 301, 117, 25);
		Update.addActionListener(this);
		panel.add(Update);
		
		Delete = new JButton("DELETE");
		Delete.setBounds(268, 301, 94, 25);
		Delete.addActionListener(this);
		panel.add(Delete);
		
		lblSr = new JLabel("SR:");
		lblSr.setBounds(206, 12, 55, 15);
		panel.add(lblSr);
		
		sr = new JTextField();
		sr.setForeground(new Color(0, 0, 0));
		sr.setEditable(false);
		sr.setBounds(260, 10, 114, 19);
		panel.add(sr);
		sr.setColumns(10);
		
		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(0, 128, 128));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				sr.setText(table.getValueAt(row, 0)+"");
				name.setText((String)table.getValueAt(row, 1));
				location.setText((String)table.getValueAt(row, 2));
				owner.setText((String)table.getValueAt(row, 3));
				pass.setText((String)table.getValueAt(row, 5));
				ownerid.setText(table.getValueAt(row, 4)+"");
			}
		});
		table.setBounds(409, 39, 397, 175);
		frame.getContentPane().add(table);
		
		users = new JTable();
		users.setBounds(409, 248, 397, 175);
		frame.getContentPane().add(users);
		
		JLabel lblAgents = new JLabel("AGENTS");
		lblAgents.setForeground(new Color(255, 255, 0));
		lblAgents.setBounds(595, 12, 70, 15);
		frame.getContentPane().add(lblAgents);
		
		JLabel lblUsers = new JLabel("USERS");
		lblUsers.setForeground(Color.YELLOW);
		lblUsers.setBounds(595, 226, 70, 15);
		frame.getContentPane().add(lblUsers);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		Agents agents = new Agents(name.getText(),location.getText(),owner.getText(),pass.getText(),Integer.parseInt(ownerid.getText()));
		
		
		Object button = ev.getSource();
		if(button.equals(Register)) {
			try {
				//check if business is already registered 
				if(Check()) {
					JOptionPane.showMessageDialog(frame, "Id Already Registered!!","error",JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					if(RegisterAgent(null,agents.getBusinessname(),agents.getLocation(), agents.getOwner(),agents.getIdno(),agents.getPassword())) {	
					JOptionPane.showMessageDialog(frame, "Success!!");
					LoadTable(table,"Agents");
					}else {
						JOptionPane.showMessageDialog(frame, "Unable to Register","error",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		else if(button.equals(Delete)) {
			 try { 
				 DeleteAgent(agents.getIdno());
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		
		
		else if(button.equals(Update)) {
			 try {
				 Update(agents.getBusinessname(),agents.getLocation(),agents.getOwner(),agents.getIdno(),agents.getPassword(),Integer.parseInt(sr.getText())); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	



	//check if already registered
	private boolean Check() throws SQLException {
		// TODO Auto-generated method stub
	    pst = cn.conn.prepareStatement("select * from Agents where o_id = ?");
		pst.setInt(1, Integer.parseInt(ownerid.getText()));
		return pst.executeQuery().next();
	}

	
	//register Agent
	private boolean RegisterAgent(String object, String businessname, String location2, String owner2, int idno,
			String password) throws SQLException {
		
		// TODO Auto-generated method stub
		
		cn.ps = cn.conn.prepareStatement("insert into Agents values(?,?,?,?,?,?)");
		cn.ps.setString(1, object);
		cn.ps.setString(2,businessname);
		cn.ps.setString(3,  location2);
		cn.ps.setString(4, owner2);
		cn.ps.setInt(5, idno);
		cn.ps.setString(6,password);
		int ok = cn.ps.executeUpdate();
	     if(ok>0) {
	    	 //success
	    	 return true;
	     }else {
	    	 return false;
	     } 
	}
	
	//update Agent
		private void Update(String businessname, String location2, String owner2, int idno, String password, int parseInt) throws SQLException {
			// TODO Auto-generated method stub
			pst = cn.conn.prepareStatement("update Agents set b_name=?,location=?,o_name=?,o_id=?,password=? where id=?");
			pst.setString(1, businessname);
			pst.setString(2, location2);
			pst.setString(3, owner2);
			pst.setInt(4, idno);
			pst.setString(5, password);
			pst.setInt(6, parseInt);
			pst.execute();
			JOptionPane.showMessageDialog(frame, "Success!!");
			LoadTable(table,"Agents");
			
		}

		//Delete Agent
		private void DeleteAgent(int idno) throws SQLException {
			// TODO Auto-generated method stub
			pst = cn.conn.prepareStatement("delete from Agents where o_id = ?");
			pst.setInt(1, idno);
			int op = JOptionPane.showConfirmDialog(frame, "Clear Record of This Agent?");
			if(op == JOptionPane.OK_OPTION) {
				pst.execute();
				LoadTable(table,"Agents");
			}
			
		}
	
	
	private void LoadTable(JTable table, String who) throws SQLException {
		pst = cn.conn.prepareStatement("select * from "+who);
		table.setModel(DbUtils.resultSetToTableModel(pst.executeQuery()));
	}
}
