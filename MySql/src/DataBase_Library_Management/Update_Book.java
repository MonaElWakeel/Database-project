package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.PreparedStatement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class Update_Book {

	private JFrame frame;
	private JTextField textField;
	//private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	static String selected = new String();
	private String[] splitStr = new String[7];

	/**
	 * Launch the application.
	 */
	public void Update_Book(String selected_book) {
		selected = selected_book;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Book window = new Update_Book();
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
	public Update_Book() {
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

		JLabel lblUpdateBook = new JLabel("Update Book");
		lblUpdateBook.setForeground(new Color(199, 21, 133));
		lblUpdateBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUpdateBook.setBounds(160, 11, 94, 14);
		frame.getContentPane().add(lblUpdateBook);

		JLabel lblEnterBookName = new JLabel("ISBN:");
		lblEnterBookName.setForeground(new Color(199, 21, 133));
		lblEnterBookName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblEnterBookName.setBounds(10, 39, 118, 14);
		frame.getContentPane().add(lblEnterBookName);

		/*JLabel lblEnterNewQuantity = new JLabel("Required quantity :");
		lblEnterNewQuantity.setForeground(new Color(199, 21, 133));
		lblEnterNewQuantity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblEnterNewQuantity.setBounds(10, 76, 125, 14);
		frame.getContentPane().add(lblEnterNewQuantity);*/

		textField = new JTextField();
		textField.setBounds(138, 36, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		/*textField_1 = new JTextField();

		textField_1.setBounds(138, 73, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);*/

		JLabel label = new JLabel("Title :");
		label.setForeground(new Color(199, 21, 133));
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(10, 111, 46, 14);
		frame.getContentPane().add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(138, 108, 86, 20);
		frame.getContentPane().add(textField_2);

		JLabel label_1 = new JLabel("Quantity :");
		label_1.setForeground(new Color(199, 21, 133));
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label_1.setBounds(10, 147, 73, 14);
		frame.getContentPane().add(label_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(138, 144, 86, 20);
		frame.getContentPane().add(textField_3);

		JLabel label_2 = new JLabel("Publisher :");
		label_2.setForeground(new Color(199, 21, 133));
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label_2.setBounds(10, 188, 78, 20);
		frame.getContentPane().add(label_2);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(138, 188, 86, 20);
		frame.getContentPane().add(textField_4);

		JLabel label_3 = new JLabel("Year :");
		label_3.setForeground(new Color(199, 21, 133));
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label_3.setBounds(234, 39, 46, 14);
		frame.getContentPane().add(label_3);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(338, 35, 86, 20);
		frame.getContentPane().add(textField_5);

		JLabel label_4 = new JLabel("Price :");
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label_4.setForeground(new Color(199, 21, 133));
		label_4.setBounds(234, 76, 46, 14);
		frame.getContentPane().add(label_4);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(338, 73, 86, 20);
		frame.getContentPane().add(textField_6);

		JLabel label_5 = new JLabel("Catalog :");
		label_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label_5.setForeground(new Color(199, 21, 133));
		label_5.setBounds(234, 111, 73, 14);
		frame.getContentPane().add(label_5);
		JLabel lblNewLabel = new JLabel("add author :");
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(234, 147, 94, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("remove author:");
		lblNewLabel_1.setForeground(new Color(199, 21, 133));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(234, 191, 106, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField_7 = new JTextField();
		textField_7.setBounds(338, 144, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setBounds(338, 188, 86, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		JComboBox<String> comboBox = new JComboBox<String>(new String[] { "Science", "Art", "Religion", "History", "Geography" });
		comboBox.setForeground(new Color(199, 21, 133));
		comboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		comboBox.setBounds(340, 108, 84, 20);
		frame.getContentPane().add(comboBox);
		System.out.println(selected);
		if (!selected.isEmpty()) {
			splitStr = selected.trim().split("\\s+");
			System.out.println(splitStr.length);
			textField.setText(splitStr[0]);
			textField_2.setText(splitStr[1]);
			textField_4.setText(splitStr[2]);
			textField_3.setText(splitStr[6]);
			textField_5.setText(splitStr[4]);
			textField_6.setText(splitStr[3]);
			comboBox.setSelectedItem((String) splitStr[5]);
		}
		JButton btnUpdate = new JButton("update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnUpdate.setForeground(new Color(199, 21, 133));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (textField_1.getText().isEmpty()) {
					textField_1.setText("0");
				}*/
				String command = "update BOOK set ISBN = " + Sign_Up.get_value(textField.getText()) + " ,Title = "
						+ Sign_Up.get_value(textField_2.getText()) + " , Publisher_Name = "
						+ Sign_Up.get_value(textField_4.getText()) + ", Selling_Price = "
						+ Sign_Up.get_value(textField_6.getText()) + ", Publication_year = "
						+ Sign_Up.get_value(textField_5.getText()) + ", Catagory = "
						+ Sign_Up.get_value((String) comboBox.getSelectedItem()) + ", Quantity = "
						+ textField_3.getText() + " where  ISBN = \"" + splitStr[0] + "\"";
				String comm_1 = new String();
				String comm_2 = new String();
				if (!textField_7.getText().isEmpty()) {
					comm_1 = "insert into BOOK_AUTHORS values (\"" + textField.getText() + "\" , \""
							+ textField_7.getText() + "\")";
					System.out.println(comm_1);
				}
				if (!textField_8.getText().isEmpty()) {
					comm_2 = "delete from BOOK_AUTHORS where ISBN = \"" + textField.getText()
							+ "\" and Author_Name = \"" + textField_8.getText() + "\"";
					System.out.println(comm_2);

				}

				try {

					PreparedStatement create = Password.conn.prepareStatement(command);
					create.executeUpdate();
					if (!comm_1.isEmpty()) {
						PreparedStatement create_1 = Password.conn.prepareStatement(comm_1);
						create_1.executeUpdate();
					}
					if (!comm_2.isEmpty()) {
						PreparedStatement create_2 = Password.conn.prepareStatement(comm_2);
						create_2.executeUpdate();
					}
					Search_Manager nw = new Search_Manager();
					nw.Search_Manager();
					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("ERROR " + e1.getMessage());
				}

			}
		});
		btnUpdate.setBounds(335, 233, 89, 23);
		frame.getContentPane().add(btnUpdate);
		/********************** back button *********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Search_Manager nw = new Search_Manager();
				nw.Search_Manager();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 1, 80, 20);
		frame.getContentPane().add(btnSignUp);
	}
}
/*Actual_Quantity = Actual_Quantity - " + textField_1.getText()
+ ",*/