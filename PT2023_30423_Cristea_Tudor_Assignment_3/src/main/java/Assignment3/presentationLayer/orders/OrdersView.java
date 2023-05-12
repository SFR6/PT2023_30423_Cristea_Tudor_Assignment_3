package Assignment3.presentationLayer.orders;

import Assignment3.presentationLayer.MyButton;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * This class represents the first Orders view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class OrdersView extends JFrame
{
    /**
     * This field is the button the user needs to press in order to open up the second Orders view and add a new order
     */
    private MyButton addOrderButton;
    /**
     * This field is the button the user needs to press in order to open up the second Orders view and edit an existing
     * order
     */
    private MyButton editOrderButton;
    /**
     * This field is the button the user needs to press in order to open up the second Orders view and delete an
     * existing order
     */
    private MyButton deleteOrderButton;

    /**
     * This field is the table which will contain all the orders found in the Orders table of the database
     */
    private JTable ordersTable;
    /**
     * This field is the combo box which will contain all the ids of the existing orders so that the user can choose
     * which order to edit/delete
     */
    private JComboBox<String> ordersComboBox;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public OrdersView(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Orders Table");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(10, 10));

        ordersTable = new JTable();
        ordersTable.setRowSelectionAllowed(false);
        ordersTable.setCellSelectionEnabled(false);
        ordersTable.setColumnSelectionAllowed(false);
        ordersTable.setDragEnabled(false);
        ordersTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ordersTable.setDefaultEditor(Object.class, null);
        ordersTable.setPreferredSize(new Dimension(880, 400));

        JTableHeader header = ordersTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setBackground(Color.GRAY);
        header.setEnabled(false);

        this.getContentPane().add(ordersTable, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        this.getContentPane().add(scrollPane);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        this.getContentPane().add(panel, BorderLayout.SOUTH);

        addOrderButton = new MyButton("Add");
        addOrderButton.setBackground(new Color(250, 129, 129));
        addOrderButton.setHoverBackgroundColor(new Color (252, 89, 89));
        addOrderButton.setPressedBackgroundColor(new Color(255, 63, 63));
        addOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(addOrderButton);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(300);
        panel.add(splitPane, BorderLayout.SOUTH);

        ordersComboBox = new JComboBox<>();
        ordersComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ordersComboBox.setMaximumRowCount(3);
        splitPane.setLeftComponent(ordersComboBox);

        JSplitPane splitPane2 = new JSplitPane();
        splitPane2.setEnabled(false);
        splitPane2.setDividerLocation(270);

        editOrderButton = new MyButton("Edit");
        editOrderButton.setBackground(new Color(255, 200, 100));
        editOrderButton.setHoverBackgroundColor(new Color (255, 200, 50));
        editOrderButton.setPressedBackgroundColor(new Color(255, 200, 0));
        editOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        splitPane2.setLeftComponent(editOrderButton);

        deleteOrderButton = new MyButton("Delete");
        deleteOrderButton.setBackground(new Color(135, 206, 250));
        deleteOrderButton.setHoverBackgroundColor(new Color(85, 206, 250));
        deleteOrderButton.setPressedBackgroundColor(new Color(35, 206, 250));
        deleteOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        splitPane2.setRightComponent(deleteOrderButton);
        splitPane.setRightComponent(splitPane2);

        this.setVisible(true);
        this.addWindowListener(windowListener);
    }

    /**
     * This method adds an action listener to the Add button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Add button is pressed by the user
     */
    public void addAddOrderButtonListener(ActionListener actionListener)
    {
        addOrderButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Edit button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Edit button is pressed by the user
     */
    public void addEditOrderButtonListener(ActionListener actionListener)
    {
        editOrderButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Delete button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Delete button is pressed by the user
     */
    public void addDeleteOrderButtonListener(ActionListener actionListener)
    {
        deleteOrderButton.addActionListener(actionListener);
    }

    /**
     * This method is the accessor of the ordersTable field
     * @return the current ordersTable of an OrdersView object
     */
    public JTable getOrdersTable()
    {
        return ordersTable;
    }

    /**
     * This method is the accessor of the ordersComboBox field
     * @return the current ordersComboBox of an OrdersView object
     */
    public JComboBox<String> getOrdersComboBox()
    {
        return ordersComboBox;
    }

    /**
     * This method is the accessor of the editOrderButton field
     * @return the current editOrderButton of an OrdersView object
     */
    public MyButton getEditOrderButton()
    {
        return editOrderButton;
    }

    /**
     * This method is the accessor of the deleteOrderButton field
     * @return the current deleteOrderButton of an OrdersView object
     */
    public MyButton getDeleteOrderButton()
    {
        return deleteOrderButton;
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