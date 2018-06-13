package DataBase_Library_Management;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class Total_Sales {

	private JFrame frame;
	private JTextField textField;
	private double sum = 0;

	/**
	 * Launch the application.
	 */
	public void Total_Sales() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Total_Sales window = new Total_Sales();
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
	public Total_Sales() {
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

		String query = "SELECT SUM(Total_Price) FROM USER_INFORMATION WHERE date_of_buy BETWEEN DATE_FORMAT(NOW() - INTERVAL 1 MONTH, '%Y-%m-01')\n"
				+ "AND DATE_FORMAT(NOW() ,'%Y-%m-01')";
		System.out.println(query);
		Statement st = null;
		try {
			st = Password.conn.createStatement();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("ERROR! SQL Exception");
		}
		try {
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				sum = (rs.getDouble("SUM(Total_Price)"));
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("ERROR! SQL Exception");
		}
		textField = new JTextField();
		textField.setBounds(199, 124, 86, 20);
		textField.setText(""+sum);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View_Reports nw = new View_Reports();
				nw.View_Reports();
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBack.setForeground(new Color(199, 21, 133));
		btnBack.setBounds(306, 11, 89, 23);
		frame.getContentPane().add(btnBack);
	}

}
