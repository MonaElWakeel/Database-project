package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Password {
	private JFrame frame;
	private JPasswordField passwordField;
	static Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Password window = new Password();
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
	public Password() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(199, 21, 133));

		JLabel lblEnterPassword = new JLabel("  Enter Password ");
		lblEnterPassword.setForeground(new Color(199, 21, 133));
		lblEnterPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String password = String.valueOf(passwordField.getPassword());
					if (getConnection(password)) {
						Welcome nw = new Welcome();
						nw.Welcome();
						frame.dispose();
					} else {

						System.out.println("password is wrong");
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(103)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 220,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(111, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING,
										groupLayout.createSequentialGroup().addGap(151)
												.addComponent(lblEnterPassword, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(156))
								.addGroup(groupLayout.createSequentialGroup().addGap(163)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
										.addGap(165)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(lblEnterPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(127, Short.MAX_VALUE)));

		JLabel lblMySql = new JLabel("My SQL ");
		lblMySql.setForeground(Color.YELLOW);
		lblMySql.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		panel.add(lblMySql);
		frame.getContentPane().setLayout(groupLayout);
	}

	public boolean getConnection(String password) throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/LIBRARY";
			String username = "root";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
