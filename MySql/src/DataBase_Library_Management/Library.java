package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Library {

	private JFrame frame;
    private String user;
	/**
	 * Launch the application.
	 * @param user 
	 */
	public  void Library() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library window = new Library();
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
	public Library() {
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
	
		JButton btnNewButton = new JButton("Add Books");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setForeground(new Color(199, 21, 133));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            
            	Add_Book nw=new Add_Book();
            	nw.Add_Book();
			    frame.dispose();
			}
		});
		btnNewButton.setBounds(27, 23, 150, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdateBook = new JButton("Search Books");
		btnUpdateBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnUpdateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search_Manager nw=new Search_Manager();
            	nw.Search_Manager();
            	frame.dispose();
			}
		});
		btnUpdateBook.setForeground(new Color(199, 21, 133));
		btnUpdateBook.setBackground(Color.WHITE);
		btnUpdateBook.setBounds(27, 84, 150, 37);
		frame.getContentPane().add(btnUpdateBook);
		
		JButton btnOrdrs = new JButton("Ordrs");
		btnOrdrs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnOrdrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Library_Order_Application nw = new Library_Order_Application();
				frame.dispose();
			}
		});
		btnOrdrs.setForeground(new Color(199, 21, 133));
		btnOrdrs.setBackground(Color.WHITE);
		btnOrdrs.setBounds(27, 147, 150, 37);
		frame.getContentPane().add(btnOrdrs);
		
		JButton btnReport = new JButton("View Reports");
		btnReport.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View_Reports nw = new View_Reports();
				nw.View_Reports();
				frame.dispose();
			}
		});
		btnReport.setForeground(new Color(199, 21, 133));
		btnReport.setBackground(Color.WHITE);
		btnReport.setBounds(27, 200, 150, 37);
		frame.getContentPane().add(btnReport);
		
		JButton btnReport1 = new JButton("Promote");
		btnReport1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnReport1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promote nw = new promote();
				nw.promote();
				frame.dispose();
			}
		});
		btnReport1.setForeground(new Color(199, 21, 133));
		btnReport1.setBackground(Color.WHITE);
		btnReport1.setBounds(27, 250, 150, 37);
		frame.getContentPane().add(btnReport1);
		
		
		JButton btnNewButton_1 = new JButton("User Mode");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User nw=new User();
				nw.User();
				frame.dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(199, 21, 133));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBounds(281, 215, 123, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		 /**********************back button*********************/
		JButton btnSignUp = new JButton("Back");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login nw = new Login();
				nw.Login();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSignUp.setForeground(new Color(199, 21, 133));
		btnSignUp.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnSignUp);
	}
	 
}
