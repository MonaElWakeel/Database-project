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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class View_Reports {

	private JFrame frame;

	private ArrayList<String> Users = new ArrayList<>();
	private ArrayList<String> booksISBN;
	private ArrayList<Float> book_price;
	private ArrayList<Float> total_customer_price = new ArrayList<>();
	private ArrayList<Integer> Copies;

	/**
	 * Launch the application.
	 */
	public void View_Reports() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Reports window = new View_Reports();
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
	public View_Reports() {
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

		JButton btnNewButton = new JButton("View total sales for books in the previous month");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setForeground(new Color(199, 21, 133));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Total_Sales nw = new Total_Sales();
				nw.Total_Sales();
			}
		});
		btnNewButton.setBounds(17, 23, 407, 37);
		frame.getContentPane().add(btnNewButton);

		JButton btnUpdateBook = new JButton("View the top 5 customers");
		btnUpdateBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnUpdateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT UserName FROM USER_INFORMATION;";
				Statement command = null;
				try {
					command = Password.conn.createStatement();
				} catch (SQLException e1) {
					System.out.println("error : " + e1.getMessage());
				}
			    try {
					ResultSet result = command.executeQuery(query);
					while (result.next()) {
						Users.add(result.getString(1));
					}
				} catch (SQLException e1) {
					System.out.println("error : " + e1.getMessage());
				}
				ArrayList<String> UserNames = new ArrayList<>();
				ArrayList<Float> Total_Paper = new ArrayList<>();
				String max_five = "SELECT UserName,Total_Price FROM USER_INFORMATION ORDER BY Total_Price DESC;";
				command = null;
				try {
					command = Password.conn.createStatement();
				} catch (SQLException e1) {
					System.out.println("error: " + e1.getMessage());
				}
				try {
					ResultSet result = command.executeQuery(max_five);
					while (result.next()) {
						UserNames.add(result.getString(1));
						Total_Paper.add(result.getFloat(2));
					}
					System.out.println(Total_Paper.size());
				} catch (SQLException e1) {
					System.out.println("error: " + e1.getMessage());
				}
				new Show_Max_Five_Customers(UserNames, Total_Paper);

			}
		});
		btnUpdateBook.setForeground(new Color(199, 21, 133));
		btnUpdateBook.setBackground(Color.WHITE);
		btnUpdateBook.setBounds(17, 84, 407, 37);
		frame.getContentPane().add(btnUpdateBook);

		JButton btnOrdrs = new JButton("View the top 10 selling books for the last 3 months");
		btnOrdrs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnOrdrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Top_Selling nw = new Top_Selling();
				nw.Top_Selling();
				frame.dispose();

			}
		});
		btnOrdrs.setForeground(new Color(199, 21, 133));
		btnOrdrs.setBackground(Color.WHITE);
		btnOrdrs.setBounds(17, 145, 407, 37);
		frame.getContentPane().add(btnOrdrs);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Library nw = new Library();
				nw.Library();
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBack.setForeground(new Color(199, 21, 133));
		btnBack.setBounds(306, 227, 89, 23);
		frame.getContentPane().add(btnBack);

	}

}
