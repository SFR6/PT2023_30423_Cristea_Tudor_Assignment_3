package Assignment3.presentationLayer.views.clients;

import Assignment3.presentationLayer.MyButton;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * This class represents the first Clients view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class ClientsView extends JFrame
{
    /**
     * This field is the button the user needs to press in order to open up the second Clients view and add a new
     * client
     */
    private MyButton addClientButton;
    /**
     * This field is the button the user needs to press in order to open up the second Clients view and edit an
     * existing client
     */
    private MyButton editClientButton;
    /**
     * This field is the button the user needs to press in order to open up the second Clients view and delete an
     * existing client
     */
    private MyButton deleteClientButton;

    /**
     * This field is the table which will contain all the clients found in the Client table of the database
     */
    private JTable clientsTable;
    /**
     * This field is the combo box which will contain all the names of the existing clients so that the user can choose
     * which client to edit/delete
     */
    private JComboBox<String> clientsComboBox;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public ClientsView(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Clients Table");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(10, 10));

        clientsTable = new JTable();
        clientsTable.setRowSelectionAllowed(false);
        clientsTable.setCellSelectionEnabled(false);
        clientsTable.setColumnSelectionAllowed(false);
        clientsTable.setDragEnabled(false);
        clientsTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        clientsTable.setDefaultEditor(Object.class, null);
        clientsTable.setPreferredSize(new Dimension(880, 400));

        JTableHeader header = clientsTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setBackground(Color.GRAY);
        header.setEnabled(false);

        this.getContentPane().add(clientsTable, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(clientsTable);
        this.getContentPane().add(scrollPane);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        this.getContentPane().add(panel, BorderLayout.SOUTH);

        addClientButton = new MyButton("Add");
        addClientButton.setBackground(new Color(250, 129, 129));
        addClientButton.setHoverBackgroundColor(new Color (252, 89, 89));
        addClientButton.setPressedBackgroundColor(new Color(255, 63, 63));
        addClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(addClientButton);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(300);
        panel.add(splitPane, BorderLayout.SOUTH);

        clientsComboBox = new JComboBox<>();
        clientsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clientsComboBox.setMaximumRowCount(3);
        splitPane.setLeftComponent(clientsComboBox);

        JSplitPane splitPane2 = new JSplitPane();
        splitPane2.setEnabled(false);
        splitPane2.setDividerLocation(270);

        editClientButton = new MyButton("Edit");
        editClientButton.setBackground(new Color(255, 200, 100));
        editClientButton.setHoverBackgroundColor(new Color (255, 200, 50));
        editClientButton.setPressedBackgroundColor(new Color(255, 200, 0));
        editClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        splitPane2.setLeftComponent(editClientButton);

        deleteClientButton = new MyButton("Delete");
        deleteClientButton.setBackground(new Color(135, 206, 250));
        deleteClientButton.setHoverBackgroundColor(new Color(85, 206, 250));
        deleteClientButton.setPressedBackgroundColor(new Color(35, 206, 250));
        deleteClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        splitPane2.setRightComponent(deleteClientButton);
        splitPane.setRightComponent(splitPane2);

        this.setVisible(true);
        this.addWindowListener(windowListener);
    }

    /**
     * This method adds an action listener to the Add button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Add button is pressed by the user
     */
    public void addAddClientButtonListener(ActionListener actionListener)
    {
        addClientButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Edit button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Edit button is pressed by the user
     */
    public void addEditClientButtonListener(ActionListener actionListener)
    {
        editClientButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Delete button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Delete button is pressed by the user
     */
    public void addDeleteClientButtonListener(ActionListener actionListener)
    {
        deleteClientButton.addActionListener(actionListener);
    }

    /**
     * This method is the accessor of the clientsTable field
     * @return the current clientsTable of a ClientsView object
     */
    public JTable getClientsTable()
    {
        return clientsTable;
    }

    /**
     * This method is the accessor of the clientsComboBox field
     * @return the current clientsComboBox of a ClientsView object
     */
    public JComboBox<String> getClientsComboBox()
    {
        return clientsComboBox;
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