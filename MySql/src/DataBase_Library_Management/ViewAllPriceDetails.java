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

public class ViewAllPriceDetails extends JFrame {
	
	private JFrame frame;
    private JTable table;
	public ArrayList<String> booksISBN = new ArrayList<>();
	public ArrayList<String> booksTitle= new ArrayList<>();
	public ArrayList<Float> book_price= new ArrayList<>();
	public ArrayList<Float> total_book_price= new ArrayList<>();
	public ArrayList<Integer> Copies = new ArrayList<>();
	/**
	 * Create the frame.
	 */
	public ViewAllPriceDetails(String UserName) {
		frame = new JFrame("View book prices In Your Shopping Cart");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String query = "SELECT NO_OF_COPIES FROM USER_CART WHERE UserName = \""+UserName+"\";";
		Statement command = null;
		try {
			command = Password.conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("error : "+e1.getMessage());
		}
		try {
			ResultSet result = command.executeQuery(query);
			while(result.next()) {
				Copies.add(result.getInt(1));
			}
		} catch (SQLException e1) {
			System.out.println("error : "+e1.getMessage());
		}
		query = "SELECT ISBN FROM USER_CART WHERE UserName = \""+UserName+"\";";
		command = null;
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
		for(int i = 0; i < booksISBN.size();i++) {
			query = "SELECT Selling_Price FROM BOOK WHERE ISBN = \""+booksISBN.get(i)+"\";";
		    command = null;
		    try {
			    command = Password.conn.createStatement();
		    } catch (SQLException e1) {
				System.out.println("error : "+e1.getMessage());
		    }
		    try {
			    ResultSet result = command.executeQuery(query);
			    while(result.next()) {
				    book_price.add(result.getFloat(1));
			    }
		    } catch (SQLException e1) {
				System.out.println("error : "+e1.getMessage());
		    }
		}
		float total = 0; 
		for(int i = 0; i < booksISBN.size();i++) {
		  total = book_price.get(i) * Copies.get(i);
		  total_book_price.add(total);
		}
	    Object columnNames[] = { "ISBN", "Title", "dividual_price","total_price"};
	    Object rowData[][] = new Object[booksISBN.size()][4];
	    for(int j = 0; j < booksISBN.size();j++) {
			rowData[j][0] = booksISBN.get(j);
			rowData[j][1] = booksTitle.get(j);
			rowData[j][2] = book_price.get(j);
			rowData[j][3] = total_book_price.get(j);
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
                    case 2:
                        return Float.class;
                    case 3:
                        return Float.class;
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
