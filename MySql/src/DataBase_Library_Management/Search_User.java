package DataBase_Library_Management;

import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Search_User {

	private JFrame frame;
	String word;
	JList<String> list;
	private String sel = null;
	private int count_limit = 0;
	private int count = 0;

	/**
	 * Launch the application.
	 */
	public void Search_User() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_User window = new Search_User();
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
	public Search_User() {
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
		int min = 0;
		int range = 100;
		frame.setTitle("Book Store");

		JComboBox jComboBox1 = new JComboBox();
		jComboBox1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jComboBox1.setForeground(new Color(199, 21, 133));
		jComboBox1.setBounds(10, 0, 99, 30);
		jComboBox1.addItem("Publisher");
		jComboBox1.addItem("ISBN");
		jComboBox1.addItem("Title");
		jComboBox1.addItem("Category");
		jComboBox1.addItem("Author");
		jComboBox1.addItem("Quantity");
		jComboBox1.addItem("Selling_Price");
		jComboBox1.addItem("Publication_year");
		jComboBox1.addItem("Actual_Quantity");

		JTextField txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.ITALIC, 13));
		JButton next_search = new JButton("next");
		next_search.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		next_search.setForeground(new Color(199, 21, 133));
		JButton prev_search = new JButton("prev");
		prev_search.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		prev_search.setForeground(new Color(199, 21, 133));
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 51, 311, 200);
		frame.getContentPane().add(scrollpane);
		txtSearch.setBounds(119, 0, 99, 30);
		txtSearch.setText("search");
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count_limit = 0;
				String selected = (String) jComboBox1.getSelectedItem();
				if (selected == null) {
					selected = "Publisher";
				}
				sel = selected;
				System.out.println(txtSearch.getText());
				boolean found = select_comboBox(txtSearch.getText(), selected, list_search_1, list_search_2);
				output_search(min, range, found, list_search_1, list_search_2, txtSearch.getText(), scrollpane);

			}
		});
		next_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count_limit = count_limit + 100;
				boolean found = select_comboBox(txtSearch.getText(), sel, list_search_1, list_search_2);
				output_search(count_limit, range, found, list_search_1, list_search_2, txtSearch.getText(), scrollpane);
			}
		});
		next_search.setBounds(331, 85, 93, 23);
		frame.getContentPane().add(next_search);

		prev_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count_limit = count_limit - 100;
				if (count_limit >= 0) {
					boolean found = select_comboBox(txtSearch.getText(), sel, list_search_1, list_search_2);
					output_search(count_limit, range, found, list_search_1, list_search_2, txtSearch.getText(),
							scrollpane);
				} else {
					count_limit = count_limit + 100;
				}
			}
		});
		prev_search.setBounds(331, 134, 93, 23);
		frame.getContentPane().add(prev_search);

		frame.getContentPane().add(jComboBox1);
		frame.getContentPane().add(txtSearch);

		/*JButton btnUpdatebook = new JButton("update");
		btnUpdatebook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnUpdatebook.setForeground(new Color(199, 21, 133));
		btnUpdatebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update_Book nw = new Update_Book();
				nw.Update_Book((String) list.getSelectedValue());
			}
		});
		btnUpdatebook.setBounds(331, 228, 93, 23);
		frame.getContentPane().add(btnUpdatebook);*/

		JButton btnNewButton = new JButton("add item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_item nw = new Add_item();
				try{
				nw.Add_item((String) list.getSelectedValue());
				frame.dispose();
				}catch(Exception e) {
					System.out.println("Error! Select from list");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setForeground(new Color(204, 0, 102));
		btnNewButton.setBounds(331, 181, 93, 23);
		frame.getContentPane().add(btnNewButton);

		/********************** back button *********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User nw = new User();
				nw.User();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);

	}

	public void output_search(int min, int range, boolean found, ArrayList<String> list_search_1,
			ArrayList<String> list_search_2, String s, JScrollPane scrollpane) {
		if (found == true) {
			int i = list_search_1.size() - 1;
			if (!list_search_1.isEmpty()) {
				list_search_1 = new ArrayList<String>();
			}
			i = list_search_2.size() - 1;
			if (!list_search_2.isEmpty()) {
				list_search_2 = new ArrayList<String>();
			}
			if (word == "publisher") {
				String query = "SELECT * FROM BOOK WHERE BOOK.Publisher_Name = '" + s + "' " + "LIMIT " + min + ","
						+ range;
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
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}

					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "author") {
				String query = "SELECT * FROM BOOK WHERE ISBN IN (SELECT ISBN FROM BOOK_AUTHORS "
						+ "WHERE BOOK_AUTHORS.Author_Name = '" + s + "')";

				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "isbn") {
				String query = "SELECT * FROM BOOK WHERE BOOK.ISBN = '" + s + "' " + "LIMIT " + min + "," + range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "title") {
				String query = "SELECT * FROM BOOK WHERE  BOOK.Title = '" + s + "' " + "LIMIT " + min + "," + range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "category") {
				String query = "SELECT * FROM BOOK WHERE  BOOK.Catagory = '" + s + "' " + "LIMIT " + min + "," + range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "Actual_Quantity") {
				String query = "SELECT * FROM BOOK WHERE  BOOK.Actual_Quantity = '" + s + "' " + "LIMIT " + min + ","
						+ range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "Quantity") {
				String query = "SELECT * FROM BOOK WHERE  BOOK.Quantity = '" + s + "' " + "LIMIT " + min + "," + range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "Publication_year") {
				String query = "SELECT * FROM BOOK WHERE  BOOK.Publication_year = '" + s + "' " + "LIMIT " + min + ","
						+ range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			} else if (word == "Selling_Price") {
				String query = "SELECT * FROM BOOK WHERE  BOOK.Selling_Price = '" + s + "' " + "LIMIT " + min + ","
						+ range;
				Statement st = null;
				try {
					st = Password.conn.createStatement();
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
				try {
					ResultSet rs = st.executeQuery(query);
					String str = new String();
					i = 0;
					while (rs.next()) {
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
						str = str + list_search_1.get(7 + i);
						list_search_2.add(str);
						str = new String();
						i = i + 8;
					}
					list = new JList(list_search_2.toArray());
					scrollpane.setViewportView(list);
				} catch (SQLException e) {
					// e.printStackTrace();
					System.out.println("ERROR! SQL Exception");
				}
			}
		} else {
			System.out.println("NOT FOUND");
			list = new JList();
			scrollpane.setViewportView(list);
		}

	}

	public boolean select_comboBox(String txtWord, Object selected, ArrayList<String> list_search_1,
			ArrayList<String> list_search_2) {
		int i = list_search_1.size() - 1;
		if (!list_search_1.isEmpty()) {
			list_search_1 = new ArrayList<String>();
		}
		i = list_search_2.size() - 1;
		if (!list_search_2.isEmpty()) {
			list_search_2 = new ArrayList<String>();
		}
		count = 0;
		if (selected.toString().equals("Publisher")) {
			word = "publisher";
			String query = "SELECT COUNT(*) FROM PUBLISHER WHERE PUBLISHER.Publisher_Name = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("ISBN")) {
			word = "isbn";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.ISBN = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Title")) {
			word = "title";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.Title = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Author")) {
			word = "author";
			String query = "SELECT COUNT(*) FROM BOOK_AUTHORS WHERE Author_Name = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Category")) {
			word = "category";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.Catagory = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Selling_Price")) {
			word = "Selling_Price";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.Selling_Price = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Publication_year")) {
			word = "Publication_year";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.Publication_year= '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Quantity")) {
			word = "Quantity";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.Quantity = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		} else if (selected.toString().equals("Actual_Quantity")) {
			word = "Actual_Quantity";
			String query = "SELECT COUNT(*) FROM BOOK WHERE BOOK.Actual_Quantity = '" + txtWord + "'";
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
					count = (rs.getInt("COUNT(*)"));
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("ERROR! SQL Exception");
			}
		}
		System.out.println(selected);
		if (count != 0) {
			return true;
		}
		return false;

	}
}
