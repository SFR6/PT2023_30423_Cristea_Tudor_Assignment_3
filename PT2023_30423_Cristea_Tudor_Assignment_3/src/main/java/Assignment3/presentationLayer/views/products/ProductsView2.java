package Assignment3.presentationLayer.views.products;

import Assignment3.presentationLayer.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * This class represents the second Products view of the application (part of the GUI)
 * @author Tudor Cristea
 */
public class ProductsView2 extends JFrame
{
    /**
     * This field is the button the user needs to press in order to reset the String found in the nameTextField to the
     * initial one
     */
    private MyButton resetNameButton;
    /**
     * This field is the button the user needs to press in order to reset the String found in the quantityTextField to
     * the initial one
     */
    private MyButton resetQuantityButton;
    /**
     * This field is the button the user needs to press in order to reset the String found in the priceTextField to the
     * initial one
     */
    private MyButton resetPriceButton;

    /**
     * This field is the button the user needs to press in order to add a new product to the database
     */
    private MyButton addProductButton;
    /**
     * This field is the button the user needs to press in order to apply the modifications made to a certain product
     * from the database
     */
    private MyButton editProductButton;
    /**
     * This field is the button the user needs to press in order to delete an existing product from the database
     */
    private MyButton deleteProductButton;

    /**
     * This field is the title label of this view. It changes based on the action the user is doing: Add, Edit or
     * Delete
     */
    private JLabel titleLabel;

    /**
     * This field is the name text field, where the user enters the name of a product
     */
    private JTextField nameTextField;
    /**
     * This field is the quantity text field, where the user enters the quantity of a product
     */
    private JTextField quantityTextField;
    /**
     * This field is the price text field, where the user enters the price of a product
     */
    private JTextField priceTextField;

    /**
     * This is the only constructor of this class. Here is where all the components of this view are created and added
     * to the content pane
     * @param windowListener is a modified version of a windowListener; it disables the previous window when this one is opened and re-enables it after this one is closed
     */
    public ProductsView2(WindowListener windowListener)
    {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tudor Cristea\\Pictures\\Screenshots\\store.png"));
        this.setTitle("Products Action");
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

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        priceTextField.setColumns(10);
        this.getContentPane().add(priceTextField);

        resetPriceButton = new MyButton("Reset");
        resetPriceButton.setBackground(new Color(144, 238, 144));
        resetPriceButton.setHoverBackgroundColor(new Color(144, 238, 94));
        resetPriceButton.setPressedBackgroundColor(new Color(144, 238, 44));
        resetPriceButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(resetPriceButton);

        addProductButton = new MyButton("Add");
        addProductButton.setBackground(new Color(250, 129, 129));
        addProductButton.setHoverBackgroundColor(new Color (252, 89, 89));
        addProductButton.setPressedBackgroundColor(new Color(255, 63, 63));
        addProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(addProductButton);

        editProductButton = new MyButton("Edit");
        editProductButton.setBackground(new Color(255, 200, 100));
        editProductButton.setHoverBackgroundColor(new Color (255, 200, 50));
        editProductButton.setPressedBackgroundColor(new Color(255, 200, 0));
        editProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(editProductButton);

        deleteProductButton = new MyButton("Delete");
        deleteProductButton.setBackground(new Color(135, 206, 250));
        deleteProductButton.setHoverBackgroundColor(new Color(85, 206, 250));
        deleteProductButton.setPressedBackgroundColor(new Color(35, 206, 250));
        deleteProductButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        this.getContentPane().add(deleteProductButton);

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
     * This method adds an action listener to the ResetQuantity button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetQuantity button is pressed by the user
     */
    public void addResetQuantityButtonListener(ActionListener actionListener)
    {
        resetQuantityButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the ResetPrice button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the ResetPrice button is pressed by the user
     */
    public void addResetPriceButtonListener(ActionListener actionListener)
    {
        resetPriceButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the AddProduct button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the AddProduct button is pressed by the user
     */
    public void addAddProductButtonListener(ActionListener actionListener)
    {
        addProductButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the EditProduct button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the EditProduct button is pressed by the user
     */
    public void addEditProductButtonListener(ActionListener actionListener)
    {
        editProductButton.addActionListener(actionListener);
    }

    /**
     * This method adds an action listener to the DeleteProduct button of this class
     * @param actionListener the action listener which contains all the instructions that will be executed when the DeleteProduct button is pressed by the user
     */
    public void addDeleteProductButtonListener(ActionListener actionListener)
    {
        deleteProductButton.addActionListener(actionListener);
    }

    /**
     * This method is the accessor of the titleLabel field
     * @return the current titleLabel of a ProductsView2 object
     */
    public JLabel getTitleLabel()
    {
        return titleLabel;
    }

    /**
     * This method is the accessor of the nameTextField field
     * @return the current nameTextField of a ProductsView2 object
     */
    public JTextField getNameTextField()
    {
        return nameTextField;
    }

    /**
     * This method is the accessor of the quantityTextField field
     * @return the current quantityTextField of a ProductsView2 object
     */
    public JTextField getQuantityTextField()
    {
        return quantityTextField;
    }

    /**
     * This method is the accessor of the priceTextField field
     * @return the current priceTextField of a ProductsView2 object
     */
    public JTextField getPriceTextField()
    {
        return priceTextField;
    }

    /**
     * This method is the accessor of the resetNameButton field
     * @return the current resetNameButton of a ProductsView2 object
     */
    public MyButton getResetNameButton()
    {
        return resetNameButton;
    }

    /**
     * This method is the accessor of the resetQuantityButton field
     * @return the current resetQuantityButton of a ProductsView2 object
     */
    public MyButton getResetQuantityButton()
    {
        return resetQuantityButton;
    }

    /**
     * This method is the accessor of the resetPriceButton field
     * @return the current resetPriceButton of a ProductsView2 object
     */
    public MyButton getResetPriceButton()
    {
        return resetPriceButton;
    }

    /**
     * This method is the accessor of the addProductButton field
     * @return the current addProductButton of a ProductsView2 object
     */
    public MyButton getAddProductButton()
    {
        return addProductButton;
    }

    /**
     * This method is the accessor of the editProductButton field
     * @return the current editProductButton of a ProductsView2 object
     */
    public MyButton getEditProductButton()
    {
        return editProductButton;
    }

    /**
     * This method is the accessor of the deleteProductButton field
     * @return the current deleteProductButton of a ProductsView2 object
     */
    public MyButton getDeleteProductButton()
    {
        return deleteProductButton;
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