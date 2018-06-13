package DataBase_Library_Management;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RemoveItemsFromCart extends JFrame {
	
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public String removed_Book =" ";
	public static int No_Of_Copies = 0;
	
	/**
	 * Create the frame.
	 */
	public RemoveItemsFromCart(String UserName) {
		frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 550, 350);
	    frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(199, 21, 133));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblBookIsbn = new JLabel(" Book ISBN");
		lblBookIsbn.setForeground(new Color(199, 21, 133));
		lblBookIsbn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblNoofcopies = new JLabel("NoOFCopies");
		lblNoofcopies.setForeground(new Color(199, 21, 133));
		lblNoofcopies.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				No_Of_Copies = Integer.parseInt(textField_1.getText());
		        textField_1.setText(" ");
		        removed_Book = textField.getText();
		        textField.setText(" ");
		        String query = "SELECT NO_OF_COPIES FROM USER_CART WHERE UserName = \""+UserName+"\" AND ISBN = \""+removed_Book+"\";";
				int NumberOfCopies = 0;
				Statement command = null;
				try {
					command = Password.conn.createStatement();
				} catch (SQLException e1) {
					System.out.println("error : "+e1.getMessage());
				}
				try {
					ResultSet result = command.executeQuery(query);
					if(result.next()) {
						NumberOfCopies = result.getInt(1);
					}
				
				} catch (SQLException e1) {
					System.out.println("error : "+e1.getMessage());
				}
				if(NumberOfCopies > No_Of_Copies) {
					int total = NumberOfCopies - No_Of_Copies;
					query = "UPDATE USER_CART SET NO_OF_COPIES = "+ total +" WHERE UserName = \""+UserName+"\" AND ISBN = \""+removed_Book+"\";";
				} else if(NumberOfCopies == No_Of_Copies) {
					query = "DELETE USER_CART WHERE UserName = \""+UserName+"\" AND ISBN = \""+removed_Book+"\";";
				}
				try {
					PreparedStatement create = Password.conn.prepareStatement(query);
					create.executeUpdate();
				} catch (SQLException e1) {
					System.out.println("error : "+e1.getMessage());
				}
			}
		});
				
		
		
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setForeground(new Color(199, 21, 133));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(60)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addComponent(lblBookIsbn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(135)
							.addComponent(lblNoofcopies, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(157)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookIsbn, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNoofcopies, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(btnNewButton)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		
		JLabel lblRemoveItemsFrom = new JLabel("Remove Items From Your Shopping Cart ");
		lblRemoveItemsFrom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRemoveItemsFrom.setForeground(new Color(255, 255, 0));
		panel.add(lblRemoveItemsFrom);
		contentPane.setLayout(gl_contentPane);
	}
}
