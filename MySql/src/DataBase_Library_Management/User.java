package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class User {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public void User() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User window = new User();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string 
	 */
	public User() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 153));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("     User Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setBounds(144, 11, 126, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit nw = new Edit();
				nw.Edit();
				frame.dispose();
			}
		});
		btnEdit.setForeground(new Color(199, 21, 133));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEdit.setBounds(119, 63, 188, 34);
		frame.getContentPane().add(btnEdit);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Search_User nw=new Search_User();
				nw.Search_User();
				frame.dispose();
			}
		});
		btnSearch.setForeground(new Color(199, 21, 133));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSearch.setBounds(119, 109, 188, 34);
		frame.getContentPane().add(btnSearch);
		
		JButton btnManageShoppingCart = new JButton("Manage Shopping Cart");
		btnManageShoppingCart.setForeground(new Color(199, 21, 133));
		btnManageShoppingCart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnManageShoppingCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ShoppingCart nw = new ShoppingCart();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error!");
				}
				frame.dispose();
			}
		});
		btnManageShoppingCart.setBounds(119, 163, 188, 34);
		frame.getContentPane().add(btnManageShoppingCart);
		
		 /**********************back button*********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login nw = new Login();
				nw.Login();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);
	}
}
