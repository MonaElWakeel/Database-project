package DataBase_Library_Management;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Edit {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField;
	private ResultSet rs;
    private String[] splitStr = new String[8];

	/**
	 * Launch the application.
	 */
	public  void Edit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit window = new Edit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Edit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Account account = new Account();
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		frame.getContentPane().setBackground(new Color(255, 255, 153));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("user name :");
		lblNewLabel.setForeground(new Color(255, 20, 147));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(10, 26, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				account.setUser_name(textField.getText());
			}
		});
		textField.setBounds(97, 23, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password :");
		lblPassword.setForeground(new Color(255, 20, 147));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPassword.setBounds(10, 70, 77, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("last name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setForeground(new Color(255, 20, 147));
		lblNewLabel_1.setBounds(10, 107, 86, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("first name :");
		lblNewLabel_2.setForeground(new Color(255, 20, 147));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 149, 86, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setLast_name(textField_2.getText());
			}
		});
		textField_2.setBounds(97, 104, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setFirst_name(textField_3.getText());
			}
		});
		textField_3.setBounds(97, 146, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("email :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_3.setForeground(new Color(255, 20, 147));
		lblNewLabel_3.setBounds(10, 188, 66, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setEmail(textField_4.getText());
			}
		});
		textField_4.setBounds(97, 185, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("phone :");
		lblNewLabel_4.setForeground(new Color(255, 20, 147));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_4.setBounds(224, 26, 66, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setPhone(textField_5.getText());
			}
		});
		textField_5.setBounds(331, 23, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("shipping address :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setForeground(new Color(255, 20, 147));
		lblNewLabel_5.setBounds(224, 70, 108, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setAddress(textField_6.getText());
			}
		});
		textField_6.setBounds(331, 67, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("sign up as :");
		lblNewLabel_6.setForeground(new Color(255, 20, 147));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(224, 107, 66, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"manager", "user"}));
		comboBox.setBounds(331, 104, 86, 20);
		frame.getContentPane().add(comboBox);
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 67, 86, 20);
		frame.getContentPane().add(passwordField);
		String query = "SELECT * FROM USER_INFORMATION WHERE UserName = \"" + Welcome.user + "\"";	
		Statement stm;
		try {
			stm = Password.conn.createStatement();
			rs = stm.executeQuery(query);
			rs.next();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			System.out.println("error "+e2.getMessage());
		}
         try {
			textField.setText(rs.getString("UserName"));
			textField_2.setText(rs.getString("LastName"));
	        textField_3.setText(rs.getString("FirstName"));
	        textField_4.setText(rs.getString("Email"));
	        textField_5.setText(rs.getString("UserPhone"));
	        textField_6.setText(rs.getString("ShippingAddress"));
	        System.out.println(rs.getString("ManagerOrUser"));
	        comboBox.setSelectedItem(rs.getString("ManagerOrUser"));
		} catch (SQLException e2) {
			System.out.println("error "+e2.getMessage());
		}
 		JButton btnSignUp = new JButton("edit");
 		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
 		btnSignUp.setForeground(new Color(255, 20, 147));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String command = "update USER_INFORMATION set UserName = " + Sign_Up.get_value(textField.getText())
						+ " , UserPassword = " + Sign_Up.get_value(String.valueOf(passwordField.getPassword()))
						+ ",LastName =" + Sign_Up.get_value(textField_2.getText()) + " , FirstName = "
						+ Sign_Up.get_value(textField_3.getText()) + " , Email = "
						+ Sign_Up.get_value(textField_4.getText()) + " , UserPhone = "
						+ Sign_Up.get_value(textField_5.getText()) + ", ShippingAddress = "
						+ Sign_Up.get_value(textField_6.getText()) + " , ManagerOrUser =  "
						+ Sign_Up.get_value((String)comboBox.getSelectedItem())+" where  UserName = \""+Welcome.user+"\"";
				try {					
					PreparedStatement create = Password.conn.prepareStatement(command);
					create.executeUpdate();
					User nw=new User();
					nw.User();
					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("ERROR "+e1.getMessage());
				}
				
			}
			
		});
		btnSignUp.setBounds(312, 212, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
		 /**********************back button*********************/
		JButton btnSignUp1 = new JButton("Back");
		btnSignUp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User nw = new User();
				nw.User();
				frame.dispose();
			}
		});
		btnSignUp1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp1.setForeground(new Color(199, 21, 133));
		btnSignUp1.setBounds(365, 1, 80, 20);
		frame.getContentPane().add(btnSignUp1);
		
	}

}
