package DataBase_Library_Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.PreparedStatement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Add_item {

	private JFrame frame;
	private JTextField textField;
	int no_of_copies = 0;
	static String item = new String();

	/**
	 * Launch the application.
	 * 
	 * @param item
	 */
	public void Add_item(String item_1) {
		item = item_1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_item window = new Add_item();
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
	public Add_item() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 102));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("enter number of copies :");
		lblNewLabel.setForeground(new Color(255, 0, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(21, 107, 174, 25);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(21, 143, 373, 39);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(218, 110, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnAdd = new JButton("add");
		btnAdd.setForeground(new Color(255, 0, 204));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(item);
				no_of_copies = Integer.parseInt(textField.getText());
				String comm = "INSERT INTO USER_CART values (\"";
				try {
					String[] splited = item.split("\\s+");
					lblNewLabel_1.setText("");
					comm = comm + Welcome.user + "\",\"" + splited[0] + "\"," + no_of_copies + ")";
					System.out.println(comm);
					PreparedStatement create = Password.conn.prepareStatement(comm);
					create.executeUpdate();
					Search_User nw = new Search_User();
					nw.Search_User();
					frame.dispose();

				} catch (Exception e1) {
					lblNewLabel_1.setText("error " + e1.getMessage());
				}
			}
		});
		btnAdd.setBounds(305, 214, 89, 23);
		frame.getContentPane().add(btnAdd);
	}

}
