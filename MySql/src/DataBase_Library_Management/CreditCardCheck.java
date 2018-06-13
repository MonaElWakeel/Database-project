package DataBase_Library_Management;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class CreditCardCheck {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField creditCardField;
	boolean enter = false;
	private ArrayList<String> booksISBN;
	private ArrayList<Float> book_price;
	private ArrayList<Integer> Copies;
	/**
	 * Launch the application.
	 */
	public void CreditCardCheck() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardCheck window = new CreditCardCheck();
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
	public CreditCardCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 153));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("expire date : ");
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(31, 69, 102, 20);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(53, 95, 176, 20);
		frame.getContentPane().add(lblNewLabel_3);
		textField = new JTextField();
		textField.setBounds(143, 66, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("credit card number :");
		lblNewLabel_1.setForeground(new Color(199, 21, 133));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(31, 132, 102, 17);
		frame.getContentPane().add(lblNewLabel_1);

		creditCardField = new JPasswordField();
		creditCardField.setBounds(143, 129, 85, 20);
		frame.getContentPane().add(creditCardField);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(53, 157, 173, 20);
		frame.getContentPane().add(lblNewLabel_2);
		JButton btnLogIn = new JButton("check");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnLogIn.setForeground(new Color(199, 21, 133));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (String.valueOf(creditCardField.getPassword()).length() == 4) {
					enter = true;
					int j = 0;
					for (int i = 0; i < String.valueOf(creditCardField.getPassword()).length(); i++) {
						char x = String.valueOf(creditCardField.getPassword()).charAt(i);
						if (!Character.isDigit(x)) {
							lblNewLabel_2.setText("credit card password is wrong");
							j = 1;
							break;

						}
					}
					if (j == 0) {
						lblNewLabel_2.setText("");
					}
				} else {
					enter = false;
					lblNewLabel_2.setText("credit card password is wrong");
				}

				if (enter == true) {
					String date1 = String.valueOf(textField.getText());
					Date date2 = null;
					try {
						date2 = dateFormat.parse(date1);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error in format of date");
					}
					try {
						if (date2.after(date)) {
							frame.dispose();
							booksISBN = new ArrayList<>();
							book_price = new ArrayList<>();
							Copies = new ArrayList<>();
							String query_2 = "SELECT * FROM USER_CART WHERE UserName = \""+Welcome.user+"\"";
							Statement st = null;
							try {
								st = Password.conn.createStatement();
							} catch (SQLException e2) {
								System.out.println("ERROR! SQL Exception");
							}
							try {
								ResultSet rs = st.executeQuery(query_2);
								
								while (rs.next()) {
								String ISBN = rs.getString("USER_CART.ISBN");
								booksISBN.add(ISBN);
								int NO = Integer.parseInt(rs.getString("USER_CART.NO_OF_COPIES"));
								Copies.add(NO);
								String query_3 = "UPDATE BOOK SET Actual_Quantity = Actual_Quantity - "+NO +", Total_No_Of_Book_Buy = Total_No_Of_Book_Buy + "+NO+" WHERE ISBN = \""+ISBN+"\";";
								String query = "DELETE FROM USER_CART WHERE ISBN = \"" + ISBN+ "\";";
								try {
									PreparedStatement create = Password.conn.prepareStatement(query);
									create.executeUpdate();
								} catch (SQLException e1) {
									System.out.println("error:"+e1.getMessage());
								}
								PreparedStatement create_1 = Password.conn.prepareStatement(query_3);
								create_1.executeUpdate();
								}	
							} catch (SQLException e1) {
								System.out.println("ERROR! SQL Exception222222");
							}
							String query_4;
							Statement command = null;

							for (int j = 0; j < booksISBN.size(); j++) {
								query_4 = "SELECT Selling_Price FROM BOOK WHERE ISBN = \"" + booksISBN.get(j) + "\";";
								command = null;
								try {
									command = Password.conn.createStatement();
								} catch (SQLException e1) {
									System.out.println("error:" + e1.getMessage());
								}
								try {
									ResultSet result = command.executeQuery(query_4);
									while (result.next()) {
										book_price.add(result.getFloat(1));
									}
								} catch (SQLException e1) {
									System.out.println("error:" + e1.getMessage());
								}
							}
							float total_user_price = 0;
							for (int x = 0; x < booksISBN.size(); x++) {
								total_user_price = total_user_price + book_price.get(x) * Copies.get(x);
							}
							String query_check = "SELECT UserName FROM USER_INFORMATION WHERE UserName = \"" + Welcome.user + "\";";
							command = null;
							try {
								command = Password.conn.createStatement();
							} catch (SQLException e1) {
								System.out.println("error : " + e1.getMessage());
							}
							try {
								ResultSet result = command.executeQuery(query_check);
								String last_query ;
								command = null;
								if (!result.next()) {
									LocalDate localDate = LocalDate.now();
									last_query = "UPDATE USER_INFORMATION SET Total_Price = " + total_user_price + ", date_of_buy = "+ 
											 DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "WHERE UserName = \""
											 + Welcome.user+"\";";
									try {
										PreparedStatement create = Password.conn.prepareStatement(last_query);
										create.executeUpdate();
									} catch (SQLException e1) {
										System.out.println("error : "+e1.getMessage());
									}
								} else {
									last_query = "UPDATE USER_INFORMATION SET Total_Price = " + total_user_price + "WHERE UserName = \""
											+ Welcome.user + "\";";
									try {
										PreparedStatement create = Password.conn.prepareStatement(last_query);
										create.executeUpdate();
									} catch (SQLException e1) {
										System.out.println("error : "+e1.getMessage());
									}
								}
							} catch (SQLException e1) {
								System.out.println("error : " + e1.getMessage());
							}
							Log_Out nw = new Log_Out();
							nw.Log_Out();

						} else {

							lblNewLabel_3.setText("Date is expire");
						}
					} catch (Exception e1) {
						lblNewLabel_3.setText("Error in format of date");
					}
				} else {
				}
			}
		});
		btnLogIn.setBounds(143, 213, 89, 23);
		frame.getContentPane().add(btnLogIn);
		/********************** back button *********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ShoppingCart nw = new ShoppingCart();
				} catch (SQLException e) {
					System.out.println("Error!");
				}
	
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);

	}
}
