package DataBase_Library_Management;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Top_Selling {

	private JFrame frame;
	JList<String> list;
	private String query1 = null;
	/**
	 * Launch the application.
	 */
	public void Top_Selling() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Top_Selling window = new Top_Selling();
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
	public Top_Selling() {
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
		
		ArrayList<String> list_search_1 = new ArrayList<String>();
		ArrayList<String> list_search_2 = new ArrayList<String>();
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 51, 311, 200);
		frame.getContentPane().add(scrollpane);
		query1 = "SELECT * FROM BOOK ORDER BY Total_No_Of_Book_Buy DESC LIMIT 10";
		Statement st = null;
		try {
			st = Password.conn.createStatement();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("ERROR! SQL Exception");
		}
		try {
			ResultSet rs = st.executeQuery(query1);
			String str = new String();
			int i = 0;
			int j = 0;
			while (rs.next() && j<=10) {
				list_search_1.add(rs.getString("BOOK.ISBN"));
				str = list_search_1.get(0 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Title"));
				str = str + list_search_1.get(1 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Publisher_Name"));
				str = str + list_search_1.get(2 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Selling_Price"));
				str = str + list_search_1.get(3 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Publication_year"));
				str = str + list_search_1.get(4 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Catagory"));
				str = str + list_search_1.get(5 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Quantity"));
				str = str + list_search_1.get(6 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Actual_Quantity"));
				str = str + list_search_1.get(7 + i) + " ";
				list_search_1.add(rs.getString("BOOK.Total_No_Of_Book_Buy"));
				str = str + list_search_1.get(8 + i);
				list_search_2.add(str);
				str = new String();
				i = i + 9; 
				j++;
			}

			list = new JList(list_search_2.toArray());
			scrollpane.setViewportView(list);
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("ERROR! SQL Exception");
		}
		/********************** back button *********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View_Reports nw = new View_Reports();
				nw.View_Reports();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);
	}

}
