package Assignment3.presentationLayer.products;

import Assignment3.presentationLayer.MyButton;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * This class represents the first Products view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class ProductsView extends JFrame
{
    /**
     * This field is the button the user needs to press in order to open up the second Products view and add a new
     * product
     */
    private MyButton addProductButton;
    /**
     * This field is the button the user needs to press in order to open up the second Products view and edit an
     * existing product
     */
    private MyButton editProductButton;
    /**
     * This field is the button the user needs to press in order to open up the second Products view and delete an
     * existing product
     */
    private MyButton deleteProductButton;

    /**
     * This field is the table which will contain all the products found in the Product table of the database
     */
    private JTable productsTable;
    /**
     * This field is the combo box which will contain all the names of the existing products so that the user can
     * choose which product to edit/delete
     */
    private JComboBox<String> productsComboBox;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public ProductsView(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Products Table");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(10, 10));

        productsTable = new JTable();
        productsTable.setRowSelectionAllowed(false);
        productsTable.setCellSelectionEnabled(false);
        productsTable.setColumnSelectionAllowed(false);
        productsTable.setDragEnabled(false);
        productsTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        productsTable.setDefaultEditor(Object.class, null);
        productsTable.setPreferredSize(new Dimension(880, 400));

        JTableHeader header = productsTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setBackground(Color.GRAY);
        header.setEnabled(false);

        this.getContentPane().add(productsTable, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(productsTable);
        this.getContentPane().add(scrollPane);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        this.getContentPane().add(panel, BorderLayout.SOUTH);

        addProductButton = new MyButton("Add");
        addProductButton.setBackground(new Color(250, 129, 129));
        addProductButton.setHoverBackgroundColor(new Color (252, 89, 89));
        addProductButton.setPressedBackgroundColor(new Color(255, 63, 63));
        addProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(addProductButton);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(300);
        panel.add(splitPane, BorderLayout.SOUTH);

        productsComboBox = new JComboBox<>();
        productsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        productsComboBox.setMaximumRowCount(3);
        splitPane.setLeftComponent(productsComboBox);

        JSplitPane splitPane2 = new JSplitPane();
        splitPane2.setEnabled(false);
        splitPane2.setDividerLocation(270);

        editProductButton = new MyButton("Edit");
        editProductButton.setBackground(new Color(255, 200, 100));
        editProductButton.setHoverBackgroundColor(new Color (255, 200, 50));
        editProductButton.setPressedBackgroundColor(new Color(255, 200, 0));
        editProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        splitPane2.setLeftComponent(editProductButton);

        deleteProductButton = new MyButton("Delete");
        deleteProductButton.setBackground(new Color(135, 206, 250));
        deleteProductButton.setHoverBackgroundColor(new Color(85, 206, 250));
        deleteProductButton.setPressedBackgroundColor(new Color(35, 206, 250));
        deleteProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        splitPane2.setRightComponent(deleteProductButton);
        splitPane.setRightComponent(splitPane2);

        this.setVisible(true);
        this.addWindowListener(windowListener);
    }

    /**
     * This method adds an action listener to the Add button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Add button is pressed by the user
     */
    public void addAddProductButtonListener(ActionListener actionListener)
    {
        addProductButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Edit button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Edit button is pressed by the user
     */
    public void addEditProductButtonListener(ActionListener actionListener)
    {
        editProductButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Delete button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Delete button is pressed by the user
     */
    public void addDeleteProductButtonListener(ActionListener actionListener)
    {
        deleteProductButton.addActionListener(actionListener);
    }

    /**
     * This method is the accessor of the productsTable field
     * @return the current productsTable of a ProductsView object
     */
    public JTable getProductsTable()
    {
        return productsTable;
    }

    /**
     * This method is the accessor of the productsComboBox field
     * @return the current productsComboBox of a ProductsView object
     */
    public JComboBox<String> getProductsComboBox()
    {
        return productsComboBox;
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