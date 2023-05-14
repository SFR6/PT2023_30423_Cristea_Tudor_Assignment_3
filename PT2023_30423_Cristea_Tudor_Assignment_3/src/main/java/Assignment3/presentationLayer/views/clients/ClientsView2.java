package Assignment3.presentationLayer.views.clients;

import Assignment3.presentationLayer.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * This class represents the second Clients view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class ClientsView2 extends JFrame
{
    /**
     * This field is the button the user needs to press in order to reset the String found in the nameTextField to the
     * initial one
     */
    private MyButton resetNameButton;
    /**
     * This field is the button the user needs to press in order to reset the String found in the phoneNumberTextField
     * to the initial one
     */
    private MyButton resetPhoneNumberButton;

    /**
     * This field is the button the user needs to press in order to add a new client to the database
     */
    private MyButton addClientButton;
    /**
     * This field is the button the user needs to press in order to apply the modifications made to a certain client
     * from the database
     */
    private MyButton editClientButton;
    /**
     * This field is the button the user needs to press in order to delete an existing client from the database
     */
    private MyButton deleteClientButton;

    /**
     * This field is the title label of this view. It changes based on the action the user is doing: Add, Edit or
     * Delete
     */
    private JLabel titleLabel;

    /**
     * This field is the name text field, where the user enters the name of a client
     */
    private JTextField nameTextField;
    /**
     * This field is the phone number text field, where the user enters the phone number of a client
     */
    private JTextField phoneNumberTextField;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public ClientsView2(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Clients Action");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(4, 3, 10, 10));

        JLabel emptyLabel = new JLabel();
        this.getContentPane().add(emptyLabel);

        titleLabel = new JLabel();
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        this.getContentPane().add(titleLabel);

        JLabel emptyLabel2 = new JLabel();
        this.getContentPane().add(emptyLabel2);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        nameTextField.setColumns(10);
        this.getContentPane().add(nameTextField);

        resetNameButton = new MyButton("Reset");
        resetNameButton.setBackground(new Color(144, 238, 144));
        resetNameButton.setHoverBackgroundColor(new Color(144, 238, 94));
        resetNameButton.setPressedBackgroundColor(new Color(144, 238, 44));
        resetNameButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(resetNameButton);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        phoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(phoneNumberLabel);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        phoneNumberTextField.setColumns(10);
        this.getContentPane().add(phoneNumberTextField);

        resetPhoneNumberButton = new MyButton("Reset");
        resetPhoneNumberButton.setBackground(new Color(144, 238, 144));
        resetPhoneNumberButton.setHoverBackgroundColor(new Color(144, 238, 94));
        resetPhoneNumberButton.setPressedBackgroundColor(new Color(144, 238, 44));
        resetPhoneNumberButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(resetPhoneNumberButton);

        addClientButton = new MyButton("Add");
        addClientButton.setBackground(new Color(250, 129, 129));
        addClientButton.setHoverBackgroundColor(new Color (252, 89, 89));
        addClientButton.setPressedBackgroundColor(new Color(255, 63, 63));
        addClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(addClientButton);

        editClientButton = new MyButton("Edit");
        editClientButton.setBackground(new Color(255, 200, 100));
        editClientButton.setHoverBackgroundColor(new Color (255, 200, 50));
        editClientButton.setPressedBackgroundColor(new Color(255, 200, 0));
        editClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(editClientButton);

        deleteClientButton = new MyButton("Delete");
        deleteClientButton.setBackground(new Color(135, 206, 250));
        deleteClientButton.setHoverBackgroundColor(new Color(85, 206, 250));
        deleteClientButton.setPressedBackgroundColor(new Color(35, 206, 250));
        deleteClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(deleteClientButton);

        this.setVisible(true);
        this.addWindowListener(windowListener);
    }

    /**
     * This method adds an action listener to the ResetName button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetName button is pressed by the user
     */
    public void addResetNameButtonListener(ActionListener actionListener)
    {
        resetNameButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the ResetPhoneNumber button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetPhoneNumber button is pressed by the user
     */
    public void addResetPhoneNumberButtonListener(ActionListener actionListener)
    {
        resetPhoneNumberButton.addActionListener(actionListener);
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
     * This method is the accessor of the titleLabel field
     * @return the current titleLabel of a ClientsView2 object
     */
    public JLabel getTitleLabel()
    {
        return titleLabel;
    }

    /**
     * This method is the accessor of the nameTextField field
     * @return the current nameTextField of a ClientsView2 object
     */
    public JTextField getNameTextField()
    {
        return nameTextField;
    }

    /**
     * This method is the accessor of the phoneNumberTextField field
     * @return the current phoneNumberTextField of a ClientsView2 object
     */
    public JTextField getPhoneNumberTextField()
    {
        return phoneNumberTextField;
    }

    /**
     * This method is the accessor of the resetNameButton field
     * @return the current resetNameButton of a ClientsView2 object
     */
    public MyButton getResetNameButton()
    {
        return resetNameButton;
    }

    /**
     * This method is the accessor of the resetPhoneNumberButton field
     * @return the current resetPhoneNumberButton of a ClientsView2 object
     */
    public MyButton getResetPhoneNumberButton()
    {
        return resetPhoneNumberButton;
    }

    /**
     * This method is the accessor of the addClientButton field
     * @return the current addClientButton of a ClientsView2 object
     */
    public MyButton getAddClientButton()
    {
        return addClientButton;
    }

    /**
     * This method is the accessor of the editClientButton field
     * @return the current editClientButton of a ClientsView2 object
     */
    public MyButton getEditClientButton()
    {
        return editClientButton;
    }

    /**
     * This method is the accessor of the deleteClientButton field
     * @return the current deleteClientButton of a ClientsView2 object
     */
    public MyButton getDeleteClientButton()
    {
        return deleteClientButton;
    }

    /**
     * This method displays a JOptionPane in order to inform the user in case of a successful operation
     * @param message the message to be displayed in the pop-up prompt
     */
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
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