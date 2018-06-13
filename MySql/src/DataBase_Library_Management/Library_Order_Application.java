package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;

public class Library_Order_Application  {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public ArrayList<ArrayList<String>> OrderTable = new ArrayList<>();

	
	public Library_Order_Application() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 499, 305);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		JPanel Start_Window = new JPanel();
		JPanel PublisherTitle = new JPanel();
        JPanel enterConfirm = new JPanel();
        JPanel NoOfCopies = new JPanel();
		JPanel OrderStatus = new JPanel();
		JPanel enterNumberOfCopies = new JPanel();
		JPanel enterPublisherName = new JPanel();
		JPanel enterBookID = new JPanel();
		JPanel MakeOrder = new JPanel();
		textField = new JTextField();
		textField_1 = new JTextField();
		textField_2 = new JTextField();
		textField_3 = new JTextField();

		JLabel BookID = new JLabel("ISBN ");
		BookID.setForeground(new Color(220, 20, 60));
		BookID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		Start_Window.add(BookID);
		
		JLabel PublisherName = new JLabel("Publisher_Name");
		PublisherName.setForeground(new Color(220, 20, 60));
		PublisherName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		PublisherTitle.add(PublisherName);
		
		JLabel lblNoofcopies = new JLabel("NO_OF_COPIES ");
		lblNoofcopies.setForeground(new Color(220, 20, 60));
		lblNoofcopies.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		NoOfCopies.add(lblNoofcopies);
		
		JLabel lblStatusoforder = new JLabel("Status_of_Order ");
		lblStatusoforder.setForeground(new Color(220, 20, 60));
		lblStatusoforder.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		OrderStatus.add(lblStatusoforder);
		
		JLabel lblOrderApplication = new JLabel("Order Application  ");
		lblOrderApplication.setForeground(Color.BLUE);
		lblOrderApplication.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		MakeOrder.add(lblOrderApplication);
		
		enterConfirm.add(textField);
		textField.setColumns(10);
		
		enterBookID.add(textField_3);
		textField_3.setColumns(10);
		
		enterPublisherName.add(textField_2);
		textField_2.setColumns(10);
		enterNumberOfCopies.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		enterNumberOfCopies.add(textField_1);
		textField_1.setColumns(10);
		
		JButton Add_Order_In_Table = new JButton("Submit The Order");
		Add_Order_In_Table.setForeground(new Color(75, 0, 130));
		Add_Order_In_Table.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		Add_Order_In_Table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> addBook = new ArrayList<>();
				String BooK_id = textField_3.getText()  ;
				String Publisher_Name = textField_2.getText()  ;
				String No_Of_Book = textField_1.getText()  ;
				String Status = textField.getText()  ;
				addBook.add(BooK_id);
				addBook.add(Publisher_Name);
				addBook.add(No_Of_Book);
				addBook.add(Status);
				OrderTable.add(addBook);
				/*String query = "SELECT * FROM ORDER_APPLICATION WHERE ISBN = \""+ BooK_id +"\"AND Publisher_Name = \""+Publisher_Name+"\";";
				Statement command = null;
				try {
					command = Password.conn.createStatement();
				} catch (SQLException e1) {
					System.out.println("error:"+e1.getMessage());
				}
				try {
					ResultSet result = command.executeQuery(query);
					if(result.next()) {
						int Number = result.getInt(4);
						System.out.println(Number);
						String true_or_false = result.getString(5);
						if(true_or_false.equals("false")) {
							int t = Number + Integer.parseInt(No_Of_Book);
							String query_1 = "UPDATE ORDER_APPLICATION SET NO_OF_COPIES = "+t+" WHERE ISBN = \""+ BooK_id +"\" AND Publisher_Name = \""+Publisher_Name+"\";";
							System.out.println(query_1);

							try {
								PreparedStatement create = Password.conn.prepareStatement(query_1);
								create.executeUpdate();
							} catch (SQLException e1) {
								System.out.println("error : "+e1.getMessage());
							}
						}else {
							String query_1 = "UPDATE ORDER_APPLICATION SET NO_OF_COPIES = "+No_Of_Book+" AND Status_Of_Order = \""+Status+"\" WHERE ISBN = \""+ BooK_id +"\"AND Publisher_Name = \""+Publisher_Name+"\";";
							System.out.println(query_1);
							try {
								PreparedStatement create = Password.conn.prepareStatement(query_1);
								create.executeUpdate();
							} catch (SQLException e1) {
								System.out.println("error : "+e1.getMessage());
							}
						}
					}else {*/
						String query_1 = "INSERT INTO ORDER_APPLICATION VALUES(\'"+BooK_id+"\',\'"+Publisher_Name+"\',"+No_Of_Book+",\'"+Status+"\');";
						try {
							PreparedStatement create_1 = Password.conn.prepareStatement(query_1);
							create_1.executeUpdate();
						} catch (SQLException e) {
							System.out.println("error:"+e.getMessage());
						}
					/*}
				} catch (SQLException e1) {
					System.out.println("error:"+e1.getMessage());

				}*/
			}
		});
		
		JButton btnNewButton = new JButton("Confirm Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderTableConfirmation();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setForeground(new Color(75, 0, 130));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						Library nw = new Library();
						nw.Library();
						frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnBack.setForeground(new Color(75, 0, 130));
		GroupLayout OrderApplication = new GroupLayout(contentPane);
		OrderApplication.setHorizontalGroup(
			OrderApplication.createParallelGroup(Alignment.LEADING)
				.addGroup(OrderApplication.createSequentialGroup()
					.addGap(54)
					.addComponent(MakeOrder, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addGap(58))
				.addGroup(OrderApplication.createSequentialGroup()
					.addGroup(OrderApplication.createParallelGroup(Alignment.LEADING, false)
						.addGroup(OrderApplication.createSequentialGroup()
							.addGap(1)
							.addComponent(enterBookID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(Start_Window, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(10)
					.addGroup(OrderApplication.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(enterPublisherName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(PublisherTitle, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(OrderApplication.createParallelGroup(Alignment.LEADING)
						.addGroup(OrderApplication.createSequentialGroup()
							.addComponent(NoOfCopies, GroupLayout.PREFERRED_SIZE, 95, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(OrderStatus, GroupLayout.PREFERRED_SIZE, 98, Short.MAX_VALUE)
							.addGap(3))
						.addGroup(OrderApplication.createSequentialGroup()
							.addComponent(enterNumberOfCopies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(enterConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, OrderApplication.createSequentialGroup()
					.addGap(85)
					.addGroup(OrderApplication.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(Add_Order_In_Table, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addGap(101))
				.addGroup(OrderApplication.createSequentialGroup()
					.addGap(178)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(197, Short.MAX_VALUE))
		);
		OrderApplication.setVerticalGroup(
			OrderApplication.createParallelGroup(Alignment.TRAILING)
				.addGroup(OrderApplication.createSequentialGroup()
					.addContainerGap()
					.addComponent(MakeOrder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(OrderApplication.createParallelGroup(Alignment.TRAILING)
						.addComponent(Start_Window, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PublisherTitle, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(OrderStatus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(NoOfCopies, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(OrderApplication.createParallelGroup(Alignment.LEADING)
						.addComponent(enterConfirm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(enterPublisherName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(enterBookID, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(enterNumberOfCopies, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Add_Order_In_Table)
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(btnBack)
					.addGap(21))
		);
		contentPane.setLayout(OrderApplication);
	    frame.setVisible(true);
	    
	    /**********************back button*********************/
	}
}
