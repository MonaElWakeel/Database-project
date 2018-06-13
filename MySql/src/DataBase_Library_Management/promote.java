package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class promote {

	private JFrame frame;
	private JTextField textField;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public  void promote() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					promote window = new promote();
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
	public promote() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 102));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("enter user name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(255, 0, 153));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(49, 126, 140, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(199, 124, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPromoteUser = new JLabel("Promote User");
		lblPromoteUser.setForeground(new Color(255, 0, 153));
		lblPromoteUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPromoteUser.setBounds(168, 11, 105, 14);
		frame.getContentPane().add(lblPromoteUser);
		
		JButton btnPromote = new JButton("promote");
		btnPromote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(!textField.getText().equals("")){
				String query = "SELECT * FROM USER_INFORMATION WHERE UserName = \"" + textField.getText() + "\"";
				Statement stm;
				try {
					stm = Password.conn.createStatement();
					rs = stm.executeQuery(query);
					if(rs.next()){
						if(rs.getString("ManagerOrUser").equals("manager")){
							System.out.println("this user is already a manager");
						}
						else{
						String command = "update USER_INFORMATION set ManagerOrUser = \"" + "manager" + "\" where UserName = \""+textField.getText() + "\"";
						PreparedStatement create_1 = Password.conn.prepareStatement(command);
						create_1.executeUpdate();
						Library nw = new Library();
						nw.Library();
						frame.dispose();
					}}
					else{
						System.out.println("error user not found");
					}
				} catch (SQLException e2) {
					
					System.out.println("error"+e2.getMessage());
				}
				}else{
					System.out.println("error enter user name");
					
				}
			}
		});
		btnPromote.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnPromote.setForeground(new Color(255, 0, 153));
		btnPromote.setBounds(311, 208, 89, 23);
		frame.getContentPane().add(btnPromote);
		
		/**********************back button*********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Library nw = new Library();
				nw.Library();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);
	}
}
