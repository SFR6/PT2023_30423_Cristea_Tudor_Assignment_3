package Assignment3.presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class represents the main view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class MainView extends JFrame
{
    /**
     * This field is the button the user needs to press in order to open up the first Clients view and based on the
     * table of clients, choose what operation to perform on this table: Add, Edit or Delete
     */
    private MyButton clientsButton;
    /**
     * This field is the button the user needs to press in order to open up the first Products view and based on the
     * table of products, choose what operation to perform on this table: Add, Edit or Delete
     */
    private MyButton productsButton;
    /**
     * This field is the button the user needs to press in order to open up the first Orders view and based on the
     * table of orders, choose what operation to perform on this table: Add, Edit or Delete
     */
    private MyButton ordersButton;
    /**
     * This field is the button the user needs to press in order to open up the Bills view
     */
    private MyButton billsButton;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     */
    public MainView()
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Store Database Management Application");
        this.getContentPane().setBackground(new Color(216, 191, 216));
        this.setBounds(100, 100, 880, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(2, 4, 10, 10));

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>Store Database Management Application</div></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        this.getContentPane().add(titleLabel);

        JLabel emptyLabel = new JLabel("");
        this.getContentPane().add(emptyLabel);

        JLabel emptyLabel2 = new JLabel("");
        this.getContentPane().add(emptyLabel2);

        JLabel emptyLabel3 = new JLabel("");
        this.getContentPane().add(emptyLabel3);

        clientsButton = new MyButton("Clients");
        clientsButton.setBackground(new Color(250, 129, 129));
        clientsButton.setHoverBackgroundColor(new Color (252, 89, 89));
        clientsButton.setPressedBackgroundColor(new Color(255, 63, 63));
        clientsButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(clientsButton);

        productsButton = new MyButton("Products");
        productsButton.setBackground(new Color(255, 200, 100));
        productsButton.setHoverBackgroundColor(new Color (255, 200, 50));
        productsButton.setPressedBackgroundColor(new Color(255, 200, 0));
        productsButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(productsButton);

        ordersButton = new MyButton("Orders");
        ordersButton.setBackground(new Color(135, 206, 250));
        ordersButton.setHoverBackgroundColor(new Color(85, 206, 250));
        ordersButton.setPressedBackgroundColor(new Color(35, 206, 250));
        ordersButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(ordersButton);

        billsButton = new MyButton("Bills");
        billsButton.setBackground(new Color(135, 206, 250));
        billsButton.setBackground(new Color(144, 238, 144));
        billsButton.setHoverBackgroundColor(new Color(144, 238, 94));
        billsButton.setPressedBackgroundColor(new Color(144, 238, 44));
        billsButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(billsButton);

        this.setVisible(true);
    }

    /**
     * This method adds an action listener to the Clients button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Clients button is pressed by the user
     */
    public void addClientsButtonListener(ActionListener actionListener)
    {
        clientsButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Products button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Products button is pressed by the user
     */
    public void addProductsButtonListener(ActionListener actionListener)
    {
        productsButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Orders button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Orders button is pressed by the user
     */
    public void addOrdersButtonListener(ActionListener actionListener)
    {
        ordersButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the Bills button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the Bills button is pressed by the user
     */
    public void addBillsButtonListener(ActionListener actionListener)
    {
        billsButton.addActionListener(actionListener);
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