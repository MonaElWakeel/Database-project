package DataBase_Library_Management;

import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class OrderTableConfirmation {

	private JTable table;
	private ArrayList<String> Order_Table_ISBN = new ArrayList<>();
	private ArrayList<String> Order_Table_PUBLISHER = new ArrayList<>();
	private ArrayList<Integer> Order_Table_Copies = new ArrayList<>();
	private ArrayList<String> Order_Table_Status = new ArrayList<>();

	public OrderTableConfirmation() {
		String query = "SELECT * FROM ORDER_APPLICATION;";
		Statement command = null;
		try {
			command = Password.conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("error : "+e1.getMessage());
		}
		try {
			ResultSet result = command.executeQuery(query);
			while(result.next()) {
				Order_Table_ISBN.add(result.getString(1));
				Order_Table_PUBLISHER.add(result.getString(2));
				Order_Table_Copies.add(result.getInt(3));
				Order_Table_Status.add(result.getString(4));
			}
		} catch (SQLException e1) {
			System.out.println("error : "+e1.getMessage());
		}
		Object columnNames[] = { "ISBN", "Publisher_Name", "NO_OF_COPIES", "Status_Of_Order" };
		Object rowData[][] = new Object[Order_Table_ISBN.size()][columnNames.length];
		for (int i = 0; i < rowData.length; i++) {
			rowData[i][0] = Order_Table_ISBN.get(i);
			rowData[i][1] = Order_Table_PUBLISHER.get(i);
			rowData[i][2] = Order_Table_Copies.get(i);
			if (Order_Table_Status.get(i).equals("true")) {
				rowData[i][3] = true;
			} else {
				rowData[i][3] = false;
			}
		}
		JFrame frame = new JFrame("Order Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return Boolean.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				for (int i = 0; i < rowData.length; i++) {
					
					Object checked = model.getValueAt(i, 3);
					if (checked != rowData[i][3]) {
						rowData[i][3] = checked;
						/*******************************/
						Statement command = null;
						String query1 = "SELECT Actual_Quantity FROM BOOK WHERE ISBN = '" + rowData[i][0] + "'";
						System.out.println(query1);
						command = null;
						try {
							command = Password.conn.createStatement();
						} catch (SQLException e1) {
							System.out.println("error:"+e1.getMessage());
						}
						int book_counter = 0;
						try {
							ResultSet result = command.executeQuery(query1);
							while (result.next()) {
								book_counter = result.getInt(1);
								System.out.println(result.getInt(1));
							}
						} catch (SQLException e1) {
							System.out.println("error:"+e1.getMessage());
						}
						
						int total = (int) rowData[i][2] + book_counter;
						/******************************/
						System.out.println(rowData[i][3]);
						String query = "UPDATE ORDER_APPLICATION SET Status_Of_Order = \"" + rowData[i][3]
								+ "\" WHERE ISBN = \"" + rowData[i][0] + "\";";
						 command = null;
						try {
							PreparedStatement create = Password.conn.prepareStatement(query);
							create.executeUpdate();
						} catch (SQLException e1) {
							System.out.println("error:"+e1.getMessage());
						}
		
						query = "UPDATE BOOK SET Actual_Quantity = " + total + " WHERE ISBN = \"" + rowData[i][0] + "\";";
						try {
							PreparedStatement create = Password.conn.prepareStatement(query);
							create.executeUpdate();
						} catch (SQLException e1) {
							System.out.println("error:"+e1.getMessage());
						}
						query = "DELETE FROM ORDER_APPLICATION WHERE ISBN = \"" + rowData[i][0] + "\";";
						try {
							PreparedStatement create = Password.conn.prepareStatement(query);
							create.executeUpdate();
						} catch (SQLException e1) {
							System.out.println("error:"+e1.getMessage());
						}
					}
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(550, 350);
		frame.setVisible(true);

	}
}
