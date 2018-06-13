package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private boolean enter = true;
	private boolean enter_2=false;
	private JPasswordField passwordField;
	private String password = null;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public void Login() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 153));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("user name :    ");
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(31, 69, 102, 14);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(53, 95, 176, 14);
		frame.getContentPane().add(lblNewLabel_3);
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					enter = true;
					String command = "SELECT * FROM USER_INFORMATION WHERE UserName = '" + textField.getText() + "'";
					Statement st = Password.conn.createStatement();
					rs = st.executeQuery(command);
					if (!rs.next()) {
						lblNewLabel_3.setText("username doesn't exists");
						enter = false;
					} else {
						
						lblNewLabel_3.setText("");
						password = rs.getString("UserPassword");
					}

				} catch (Exception e1) {
					System.out.println("error " + e1.getMessage());
				}
			}
		});
		textField.setBounds(143, 66, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(" password :");
		lblNewLabel_1.setForeground(new Color(199, 21, 133));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(31, 132, 102, 17);
		frame.getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(143, 129, 85, 20);
		frame.getContentPane().add(passwordField);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(53, 157, 173, 14);
		frame.getContentPane().add(lblNewLabel_2);
		JButton btnLogIn = new JButton("log in");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnLogIn.setForeground(new Color(199, 21, 133));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enter) {
					enter_2 = false;
					if (String.valueOf(passwordField.getPassword()).equals(password)) {
						lblNewLabel_2.setText("");
						frame.dispose();
					} else {
						lblNewLabel_2.setText("password is wrong");
						enter_2=true;
					}
				if(!enter_2){
				try {
					String type = rs.getString("ManagerOrUser");
					if (type.equals("manager")) {
						Library nw = new Library();
						Welcome.user = rs.getString("UserName");
						/*String query ="update USER_INFORMATION set Status_Of_User = 'true' where UserName = '"+	Welcome.user +"'";
						PreparedStatement create = Password.conn.prepareStatement(query);
						create.executeUpdate();*/
						nw.Library();
						frame.dispose();
					} else {
						User nw = new User();
						Welcome.user = rs.getString("UserName");
						nw.User();
						frame.dispose();
					}
				} catch (SQLException e1) {
					System.out.println("error " + e1.getMessage());
				}
				
				}
				}
			}
		});
		btnLogIn.setBounds(143, 213, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
        /**********************back button*********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Welcome nw = new Welcome();
				nw.Welcome();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);

	}
}
