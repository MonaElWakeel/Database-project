package DataBase_Library_Management;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

public class Log_Out {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void Log_Out() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log_Out window = new Log_Out();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Log_Out() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnSignUp1 = new JButton("Log out");
		btnSignUp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String query = "DELETE FROM USER_CART WHERE UserName = '" + Welcome.user + "';";
				System.out.println(query);
				Statement command = null;
				try {
					command = Password.conn.createStatement();
				} catch (SQLException e1) {
					
					//e1.printStackTrace();
					System.out.println("ERROR " + e1.getMessage());
				}
				try {
					PreparedStatement create = Password.conn.prepareStatement(query);
					create.executeUpdate();
				} catch (SQLException e1) {
					System.out.println("ERROR " + e1.getMessage());
				}
				
				Welcome user_page = new Welcome();
				user_page.Welcome();
				frame.dispose();
			}
		});
		String query ="update USER_INFORMATION set date_of_buy = NOW()  where UserName = '"+	Welcome.user +"'";
		PreparedStatement create = Password.conn.prepareStatement(query);
		create.executeUpdate();
		btnSignUp1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp1.setForeground(new Color(199, 21, 133));
		btnSignUp1.setBounds(365, 1, 80, 20);
		frame.getContentPane().add(btnSignUp1);
		

	}
	

}
