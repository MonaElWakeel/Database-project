package DataBase_Library_Management;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewAllUserBooks extends JFrame {
	
	private JFrame frame;
    private JTable table;
	private ArrayList<String> booksISBN = new ArrayList<>();
	private ArrayList<String> booksTitle= new ArrayList<>();
	/**
	 * Create the frame.
	 */
	public ViewAllUserBooks(String UserName) {
		frame = new JFrame("View All Books In Your Shopping Cart");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String query = "SELECT ISBN FROM USER_CART WHERE UserName =\""+UserName+"\";";
		Statement command = null;
		try {
			command = Password.conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("error : "+e1.getMessage());
		}
		try {
			ResultSet result = command.executeQuery(query);
			while(result.next()) {
				booksISBN.add(result.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("error : "+e1.getMessage());
		}
		for(int i = 0; i < booksISBN.size();i++) {
			query = "SELECT Title FROM BOOK WHERE ISBN = \""+booksISBN.get(i)+"\";";
			command = null;
			try {
				command = Password.conn.createStatement();
			} catch (SQLException e1) {
				System.out.println("error : "+e1.getMessage());
			}
			try {
				ResultSet result = command.executeQuery(query);
				while(result.next()) {
					booksTitle.add(result.getString(1));
				}
			} catch (SQLException e1) {
				System.out.println("error : "+e1.getMessage());
			}
		}
		Object columnNames[] = { "ISBN", "Title"};
		Object rowData[][] = new Object[booksISBN.size()][2];
		for(int i = 0 ; i < rowData.length; i++) {
		    rowData[i][0] = booksISBN.get(i);
		    rowData[i][1] = booksTitle.get(i);
		}
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
                    default:
                        return String.class;
                }
            }
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(550, 350);
	    frame.setVisible(true);
		
	}
}
