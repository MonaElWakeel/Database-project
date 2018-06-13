package DataBase_Library_Management;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ShoppingCart extends JFrame {

	private JFrame frame;
	private JPanel contentPane;

	public ShoppingCart() throws SQLException {
		initialize();
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 300, 200);
		frame.setSize(550, 350);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(199, 21, 133));

		JButton btnNewButton = new JButton("View items");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewAllUserBooks(Welcome.user);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(new Color(199, 21, 133));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));

		JButton btnNewButton_1 = new JButton("View Prices");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(new Color(199, 21, 133));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewAllPriceDetails(Welcome.user);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));

		JButton btnNewButton_2 = new JButton("Remove Items");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveItemsFromCart(Welcome.user);
			}
		});
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(new Color(199, 21, 133));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));

		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreditCardCheck nw = new CreditCardCheck();
				nw.CreditCardCheck();
			}
		});
		btnBuy.setForeground(new Color(199, 21, 133));
		btnBuy.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuy.setBackground(Color.WHITE);
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(133).addComponent(btnBuy,
								GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_contentPane.createSequentialGroup().addGap(33).addComponent(btnNewButton).addGap(23)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 201,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup().addGap(37)
														.addComponent(btnNewButton_1).addGap(55)
														.addComponent(btnNewButton_2)))))
				.addContainerGap(41, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE).addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_1)
								.addComponent(btnNewButton).addComponent(btnNewButton_2))
						.addGap(23).addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addGap(169)));

		JLabel lblShoppingCart = new JLabel("Shopping Cart ");
		lblShoppingCart.setBackground(SystemColor.activeCaption);
		lblShoppingCart.setForeground(new Color(255, 255, 0));
		lblShoppingCart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		panel.add(lblShoppingCart);
		contentPane.setLayout(gl_contentPane);
		frame.setVisible(true);

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
		btnSignUp.setBounds(390, 1, 80, 20);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnLog = new JButton("Log out");
		btnLog.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
				
				String query = "DELETE FROM USER_CART WHERE UserName = '" + Welcome.user + "';";
				System.out.println(query);
				Statement command = null;
				try {
					command = Password.conn.createStatement();
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("ERROR " + e1.getMessage());
				}
				try {
					PreparedStatement create = Password.conn.prepareStatement(query);
					create.executeUpdate();
				} catch (SQLException e1) {
					System.out.println("ERROR " + e1.getMessage());
				}
				
				Welcome user_page = new Welcome();
				user_page.Welcome();
				frame.dispose();
			}
		});
		String query ="update USER_INFORMATION set  date_of_buy = NOW()  where UserName = '"+	Welcome.user +"'";
		PreparedStatement create = Password.conn.prepareStatement(query);
		create.executeUpdate();
		btnLog.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnLog.setForeground(new Color(199, 21, 133));
		btnLog.setBounds(350, 200, 120, 20);
		frame.getContentPane().add(btnLog);

	}
}
