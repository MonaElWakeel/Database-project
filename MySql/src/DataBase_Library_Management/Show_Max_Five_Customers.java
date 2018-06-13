package DataBase_Library_Management;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Show_Max_Five_Customers extends JFrame {
	
    private JFrame frame;
    private JTable table;

	/**
	 * Create the frame.
	 */
	public Show_Max_Five_Customers(ArrayList<String> UserNames , ArrayList<Float> prices) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		Object columnNames[] = { "User Name", "Total Price"};
	    Object rowData[][] = new Object[5][2];
	    for(int i = 0; i < 5 ; i++) {
	    	if(i<UserNames.size()) {
	    	rowData[i][0] = UserNames.get(i);
	    	rowData[i][1] = prices.get(i);
	    	}
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
