package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Sign_Up {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField;
	boolean enter = false;

	/**
	 * Launch the application.
	 */
	public void Sign_Up() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign_Up window = new Sign_Up();
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
	public Sign_Up() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Account account = new Account();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 153));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("user name :");
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(10, 26, 77, 14);
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
		lblPassword.setForeground(new Color(199, 21, 133));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPassword.setBounds(10, 70, 77, 14);
		frame.getContentPane().add(lblPassword);

		JLabel lblNewLabel_1 = new JLabel("last name :");
		lblNewLabel_1.setForeground(new Color(199, 21, 133));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(10, 107, 77, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("first name :");
		lblNewLabel_2.setForeground(new Color(199, 21, 133));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(10, 149, 77, 14);
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
		lblNewLabel_3.setForeground(new Color(199, 21, 133));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
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
		lblNewLabel_4.setForeground(new Color(199, 21, 133));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4.setBounds(224, 26, 66, 14);
		frame.getContentPane().add(lblNewLabel_4);

		textField_5 = new JTextField();
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setPhone(textField_5.getText());
			}
		});
		textField_5.setBounds(339, 23, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("shipping address :");
		lblNewLabel_5.setForeground(new Color(199, 21, 133));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_5.setBounds(224, 70, 117, 14);
		frame.getContentPane().add(lblNewLabel_5);

		textField_6 = new JTextField();
		textField_6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setAddress(textField_6.getText());
			}
		});
		textField_6.setBounds(339, 67, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("sign up as :");
		lblNewLabel_6.setForeground(new Color(199, 21, 133));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_6.setBounds(224, 107, 86, 14);
		frame.getContentPane().add(lblNewLabel_6);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(199, 21, 133));
		comboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account.setType(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "manager", "user" }));
		comboBox.setBounds(339, 104, 86, 20);
		frame.getContentPane().add(comboBox);
		JButton btnSignUp = new JButton("sign up");
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter = false;
				if (account.getType() == null) {
					account.setType("manager");
				}

				try {

					String comm = "INSERT INTO USER_INFORMATION values (";
					comm = comm + get_value(account.getUser_name()) + ","
							+ get_value(String.valueOf(account.getPassword())) + "," + get_value(account.getLast_name())
							+ "," + get_value(account.getFirst_name()) + "," + get_value(account.getEmail()) + ","
							+ get_value(account.getPhone()) + "," + get_value(account.getAddress()) + ","
							+ get_value(account.getType()) + ",0,null)";
					PreparedStatement create = Password.conn.prepareStatement(comm);
					create.executeUpdate();
					frame.dispose();

				} catch (Exception e1) {
					System.out.println("error " + e1.getMessage());
					enter = true;
				}
				if (!enter) {
					if (account.getType().equals("manager")) {
						Library nw = new Library();
						Welcome.user = account.getUser_name();
						nw.Library();
						frame.dispose();
					} else {
						User nw = new User();
						Welcome.user = account.getUser_name();
						nw.User();
						frame.dispose();
					}

				}

			}
		});
		btnSignUp.setBounds(312, 212, 89, 23);
		frame.getContentPane().add(btnSignUp);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				account.setPassword(passwordField.getPassword());
			}
		});
		passwordField.setBounds(97, 67, 86, 20);
		frame.getContentPane().add(passwordField);

		/********************** back button *********************/
		JButton btnSignUp1 = new JButton("Back");
		btnSignUp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Welcome nw = new Welcome();
				nw.Welcome();
				frame.dispose();
			}
		});
		btnSignUp1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp1.setForeground(new Color(199, 21, 133));
		btnSignUp1.setBounds(365, 1, 80, 20);
		frame.getContentPane().add(btnSignUp1);

	}

	static Object get_value(Object in) {
		if (in == null || in.equals("")) {
			return "null";
		}
		return "\"" + in + "\"";
	}
}
