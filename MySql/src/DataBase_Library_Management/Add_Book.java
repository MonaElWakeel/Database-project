package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import javax.swing.JComboBox;
import java.awt.List;
import java.awt.Font;

public class Add_Book {
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	/**
	 * Launch the application.
	 */
	public void Add_Book() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Book window = new Add_Book();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return;
	}

	/**
	 * Create the application.
	 */
	public Add_Book() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Book new_Book;new_Book = new Book();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 102));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBookName = new JLabel("ISBN :");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblBookName.setForeground(new Color(199, 21, 133));
		lblBookName.setBounds(36, 65, 64, 24);
		frame.getContentPane().add(lblBookName);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
            new_Book.setISBN(textField.getText());
			System.out.println(new_Book.getISBN());

            }
		});
	
		textField.setBounds(110, 67, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Title :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setBounds(36, 100, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBook = new JLabel("Book Information");
		lblBook.setForeground(new Color(199, 21, 133));
		lblBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblBook.setBounds(155, 11, 129, 24);
		frame.getContentPane().add(lblBook);
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				new_Book.setTitle(textField_1.getText());
			}
		});
		
		textField_1.setBounds(110, 97, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("add");
		btnNewButton.setForeground(new Color(199, 21, 133));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(new_Book.getCatalog()==null){
					new_Book.setCatalog("Science");
				}
            	String command = "INSERT INTO BOOK values (";
            	String comm = "INSERT INTO BOOK_AUTHORS values (";
            	command = command+Sign_Up.get_value(new_Book.getISBN())+","+Sign_Up.get_value(new_Book.getTitle())+","+Sign_Up.get_value(new_Book.getPublisher())+","+Sign_Up.get_value(new_Book.getPrice())+","+Sign_Up.get_value(new_Book.getYear())+","+Sign_Up.get_value(new_Book.getCatalog())+", "+new_Book.getQuantity()+","+new_Book.getQuantity()+",0)";
            	
				try {
					System.out.println(command);
					PreparedStatement create = Password.conn.prepareStatement(command);
					create.executeUpdate();
					for(int i = 0 ; i < new_Book.getAuthors().size();i++){
						String temp = comm;
						comm=comm+Sign_Up.get_value(new_Book.getISBN())+","+Sign_Up.get_value(new_Book.getAuthors().get(i))+")";
						System.out.println(comm);
						PreparedStatement create_1 = Password.conn.prepareStatement(comm);
						create_1.executeUpdate();
						comm = temp;
					}
					Library nw=new Library();
					nw.Library();
					frame.dispose();
				} catch (Exception e) {
					
					System.out.println("ERROR "+e.getMessage());
				}
				
				
				
			}
		});
		btnNewButton.setBounds(345, 228, 64, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblQuantity.setForeground(new Color(199, 21, 133));
		lblQuantity.setBounds(24, 131, 76, 14);
		frame.getContentPane().add(lblQuantity);
		
		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				new_Book.setQuantity(Integer.parseInt(textField_2.getText()));
			}
		});
		textField_2.setBounds(110, 128, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher :");
		lblPublisher.setForeground(new Color(199, 21, 133));
		lblPublisher.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPublisher.setBounds(24, 159, 76, 20);
		frame.getContentPane().add(lblPublisher);
		
		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				new_Book.setPublisher(textField_3.getText());
			}
		});
		textField_3.setBounds(110, 159, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Year :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setForeground(new Color(199, 21, 133));
		lblNewLabel_1.setBounds(36, 190, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price :");
		lblNewLabel_2.setForeground(new Color(199, 21, 133));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setBounds(206, 70, 71, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				new_Book.setYear(textField_4.getText());
			}
		});
		textField_4.setBounds(110, 187, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				new_Book.setPrice(textField_5.getText());
			}
		});
		textField_5.setBounds(294, 67, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblCatalog = new JLabel("Catalog :");
		lblCatalog.setForeground(new Color(199, 21, 133));
		lblCatalog.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCatalog.setBounds(206, 100, 91, 17);
		frame.getContentPane().add(lblCatalog);
		
		JComboBox comboBox = new JComboBox(new String[] {"Science", "Art", "Religion", "History", "Geography"});
		comboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		comboBox.setForeground(new Color(199, 21, 133));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new_Book.setCatalog(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(292, 97, 86, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAuthors = new JLabel("author(s) :");
		lblAuthors.setForeground(new Color(199, 21, 133));
		lblAuthors.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAuthors.setBounds(206, 131, 91, 14);
		frame.getContentPane().add(lblAuthors);
		
		List list = new List();
		list.setBounds(285, 162, 110, 60);
		frame.getContentPane().add(list);
		textField_6 = new JTextField();
		textField_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.add(textField_6.getText());
				new_Book.getAuthors().add(textField_6.getText());
				textField_6.setText("");
			}
		});
		textField_6.setBounds(294, 128, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		 /**********************back button*********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Library nw = new Library();
				nw.Library();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
	}
}
