package Assignment3.presentationLayer.views.bills;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * This class represents the Bills view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class BillsView extends JFrame
{
    /**
     * This field is the table which will contain all the bills found in the Bill table of the database
     */
    private JTable billsTable;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public BillsView(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Bills Table");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(10, 10));

        billsTable = new JTable();
        billsTable.setRowSelectionAllowed(false);
        billsTable.setCellSelectionEnabled(false);
        billsTable.setColumnSelectionAllowed(false);
        billsTable.setDragEnabled(false);
        billsTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        billsTable.setDefaultEditor(Object.class, null);
        billsTable.setPreferredSize(new Dimension(880, 400));

        JTableHeader header = billsTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setBackground(Color.GRAY);
        header.setEnabled(false);

        this.getContentPane().add(billsTable, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(billsTable);
        this.getContentPane().add(scrollPane);

        this.setVisible(true);
        this.addWindowListener(windowListener);
    }

    /**
     * This method is the accessor of the billsTable field
     * @return the current billsTable of a billsView object
     */
    public JTable getBillsTable()
    {
        return billsTable;
    }

    /**
     * This method displays a JOptionPane in order to inform the user in case an error occurs
     * @param message the error message to be displayed in the pop-up prompt
     */
    public void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}