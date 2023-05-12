package Assignment3.presentationLayer.orders;

import Assignment3.presentationLayer.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * This class represents the second Orders view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class OrdersView2 extends JFrame
{
    /**
     * This field is the button the user needs to press in order to reset the selected item found in the clientComboBox
     * to the initial one
     */
    private MyButton resetClientButton;
    /**
     * This field is the button the user needs to press in order to reset the selected item found in the
     * productComboBox to the initial one
     */
    private MyButton resetProductButton;
    /**
     * This field is the button the user needs to press in order to reset the String found in the quantityTextField to
     * the initial one
     */
    private MyButton resetQuantityButton;

    /**
     * This field is the button the user needs to press in order to add a new order to the database
     */
    private MyButton addOrderButton;
    /**
     * This field is the button the user needs to press in order to apply the modifications made to a certain order
     * from the database
     */
    private MyButton editOrderButton;
    /**
     * This field is the button the user needs to press in order to delete an existing order from the database
     */
    private MyButton deleteOrderButton;

    /**
     * This field is the title label of this view. It changes based on the action the user is doing: Add, Edit or
     * Delete
     */
    private JLabel titleLabel;

    /**
     * This field is the client combo box, where the user chooses the client of an order
     */
    private JComboBox<String> clientComboBox;
    /**
     * This field is the product combo box, where the user chooses the product of an order
     */
    private JComboBox<String> productComboBox;
    /**
     * This field is the quantity text field, where the user enters the quantity of an order
     */
    private JTextField quantityTextField;

    /**
     * This is the only constructor of this class
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public OrdersView2(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Orders Action");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(5, 3, 10, 10));

        JLabel emptyLabel = new JLabel();
        this.getContentPane().add(emptyLabel);

        titleLabel = new JLabel();
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        this.getContentPane().add(titleLabel);

        JLabel emptyLabel2 = new JLabel();
        this.getContentPane().add(emptyLabel2);

        JLabel clientLabel = new JLabel("Client:");
        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(clientLabel);

        clientComboBox = new JComboBox<>();
        clientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 28));
        clientComboBox.setMaximumRowCount(3);
        this.getContentPane().add(clientComboBox);

        resetClientButton = new MyButton("Reset");
        resetClientButton.setBackground(new Color(144, 238, 144));
        resetClientButton.setHoverBackgroundColor(new Color(144, 238, 94));
        resetClientButton.setPressedBackgroundColor(new Color(144, 238, 44));
        resetClientButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(resetClientButton);

        JLabel productLabel = new JLabel("Product:");
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(productLabel);

        productComboBox = new JComboBox<>();
        productComboBox.setFont(new Font("Tahoma", Font.PLAIN, 28));
        productComboBox.setMaximumRowCount(3);
        this.getContentPane().add(productComboBox);

        resetProductButton = new MyButton("Reset");
        resetProductButton.setBackground(new Color(144, 238, 144));
        resetProductButton.setHoverBackgroundColor(new Color(144, 238, 94));
        resetProductButton.setPressedBackgroundColor(new Color(144, 238, 44));
        resetProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(resetProductButton);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(quantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        quantityTextField.setColumns(10);
        this.getContentPane().add(quantityTextField);

        resetQuantityButton = new MyButton("Reset");
        resetQuantityButton.setBackground(new Color(144, 238, 144));
        resetQuantityButton.setHoverBackgroundColor(new Color(144, 238, 94));
        resetQuantityButton.setPressedBackgroundColor(new Color(144, 238, 44));
        resetQuantityButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(resetQuantityButton);

        addOrderButton = new MyButton("Add");
        addOrderButton.setBackground(new Color(250, 129, 129));
        addOrderButton.setHoverBackgroundColor(new Color (252, 89, 89));
        addOrderButton.setPressedBackgroundColor(new Color(255, 63, 63));
        addOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(addOrderButton);

        editOrderButton = new MyButton("Edit");
        editOrderButton.setBackground(new Color(255, 200, 100));
        editOrderButton.setHoverBackgroundColor(new Color (255, 200, 50));
        editOrderButton.setPressedBackgroundColor(new Color(255, 200, 0));
        editOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(editOrderButton);

        deleteOrderButton = new MyButton("Delete");
        deleteOrderButton.setBackground(new Color(135, 206, 250));
        deleteOrderButton.setHoverBackgroundColor(new Color(85, 206, 250));
        deleteOrderButton.setPressedBackgroundColor(new Color(35, 206, 250));
        deleteOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(deleteOrderButton);

        this.setVisible(true);
        this.addWindowListener(windowListener);
    }

    /**
     * This method adds an action listener to the ResetClient button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetClient button is pressed by the user
     */
    public void addResetClientButtonListener(ActionListener actionListener)
    {
        resetClientButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the ResetProduct button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetProduct button is pressed by the user
     */
    public void addResetProductButtonListener(ActionListener actionListener)
    {
        resetProductButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the ResetQuantity button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetQuantity button is pressed by the user
     */
    public void addResetQuantityButtonListener(ActionListener actionListener)
    {
        resetQuantityButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the AddOrder button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the AddOrder button is pressed by the user
     */
    public void addAddOrderButtonListener(ActionListener actionListener)
    {
        addOrderButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the EditOrder button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the EditOrder button is pressed by the user
     */
    public void addEditOrderButtonListener(ActionListener actionListener)
    {
        editOrderButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the DeleteOrder button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the DeleteOrder button is pressed by the user
     */
    public void addDeleteOrderButtonListener(ActionListener actionListener)
    {
        deleteOrderButton.addActionListener(actionListener);
    }

    /**
     * This method is the accessor of the resetClientButton field
     * @return the current resetClientButton of an OrdersView2 object
     */
    public MyButton getResetClientButton()
    {
        return resetClientButton;
    }

    /**
     * This method is the accessor of the resetProductButton field
     * @return the current resetProductButton of an OrdersView2 object
     */
    public MyButton getResetProductButton()
    {
        return resetProductButton;
    }

    /**
     * This method is the accessor of the resetQuantityButton field
     * @return the current resetQuantityButton of an OrdersView2 object
     */
    public MyButton getResetQuantityButton()
    {
        return resetQuantityButton;
    }

    /**
     * This method is the accessor of the addOrderButton field
     * @return the current addOrderButton of an OrdersView2 object
     */
    public MyButton getAddOrderButton()
    {
        return addOrderButton;
    }

    /**
     * This method is the accessor of the editOrderButton field
     * @return the current editOrderButton of an OrdersView2 object
     */
    public MyButton getEditOrderButton()
    {
        return editOrderButton;
    }

    /**
     * This method is the accessor of the deleteOrderButton field
     * @return the current deleteOrderButton of an OrdersView2 object
     */
    public MyButton getDeleteOrderButton()
    {
        return deleteOrderButton;
    }

    /**
     * This method is the accessor of the titleLabel field
     * @return the current titleLabel of an OrdersView2 object
     */
    public JLabel getTitleLabel()
    {
        return titleLabel;
    }

    /**
     * This method is the accessor of the clientComboBox field
     * @return the current clientComboBox of an OrdersView2 object
     */
    public JComboBox<String> getClientComboBox()
    {
        return clientComboBox;
    }

    /**
     * This method is the accessor of the productComboBox field
     * @return the current productComboBox of an OrdersView2 object
     */
    public JComboBox<String> getProductComboBox()
    {
        return productComboBox;
    }

    /**
     * This method is the accessor of the quantityTextField field
     * @return the current quantityTextField of an OrdersView2 object
     */
    public JTextField getQuantityTextField()
    {
        return quantityTextField;
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