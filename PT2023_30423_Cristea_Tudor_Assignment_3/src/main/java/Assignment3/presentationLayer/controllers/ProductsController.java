package Assignment3.presentationLayer.controllers;

import Assignment3.businessLayer.ProductsBLL;
import Assignment3.modelLayer.Product;
import Assignment3.presentationLayer.views.products.ProductsView;
import Assignment3.presentationLayer.views.products.ProductsView2;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.util.List;

/**
 *  This class controls the two product views. It calls methods that communicate with and modify the Product table from
 *  the database as well as methods that communicate with the user through the interface
 *  @author Tudor Cristea
 */
public class ProductsController
{
    /**
     * The business logic layer of the client
     */
    private ProductsBLL productsBLL;

    /**
     * The first product view (part of the GUI)
     */
    private ProductsView productsView;
    /**
     * The second product view (part of the GUI)
     */
    private ProductsView2 productsView2;

    /**
     * The product to be deleted/updated
     */
    private Product product;

    /**
     * The initial name of the product - in case of adding a product, it will be an empty string
     *                                 - in case of editing a product, it will be the name of that product
     */
    private String initialName;
    /**
     * The initial quantity of the product - in case of adding a product, it will be an empty string
     *                                     - in case of editing a product, it will be the quantity of that product
     */
    private String initialQuantity;
    /**
     * The initial price of the product - in case of adding a product, it will be an empty string
     *                                  - in case of editing a product, it will be the price of that product
     */
    private String initialPrice;

    /**
     * The windowListener of the first product view
     */
    private WindowListener windowListener;

    /**
     * This is the only constructor of this class. It assures the "connection" between the presentation layer and the
     * business logic layer for the Product model
     * @param productsView is the view that is controlled by this class
     */
    public ProductsController(ProductsView productsView)
    {
        this.productsView = productsView;
        this.productsBLL = new ProductsBLL();

        windowListener = new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                productsView.setEnabled(false);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                productsView.setEnabled(true);
                List<Product> productList = null;
                try
                {
                    productList = productsBLL.findAllProducts();
                }
                catch (Exception ex)
                {
                    productsView.showErrorMessage(ex.getMessage());
                }
                Field[] fields = Product.class.getDeclaredFields();
                String[] headerTitles = new String[fields.length];
                for (int i = 0; i < fields.length; ++i)
                {
                    headerTitles[i] = fields[i].getName();
                }
                productsView.getProductsTable().setModel(new DefaultTableModel(headerTitles, 0));
                productsView.getProductsComboBox().removeAllItems();

                for (Product product: productList)
                {
                    ((DefaultTableModel) productsView.getProductsTable().getModel()).addRow(new String[]{String.valueOf(product.getId()), product.getName(), String.valueOf(product.getQuantity()), String.valueOf(product.getPrice())});
                    productsView.getProductsComboBox().addItem(product.getName());
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        };

        this.productsView.addAddProductButtonListener(new AddProductButtonListener());
        this.productsView.addEditProductButtonListener(new EditProductButtonListener());
        this.productsView.addDeleteProductButtonListener(new DeleteProductButtonListener());
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Name Button
     */
    class ResetNameButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Name Text Field to the initial
         * name of the product (the one that was displayed when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            productsView2.getNameTextField().setText(initialName);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Quantity Button
     */
    class ResetQuantityButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Quantity Text Field to the
         * initial quantity of the product (the one that was displayed when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            productsView2.getQuantityTextField().setText(initialQuantity);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Price Button
     */
    class ResetPriceButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Price Text Field to the initial
         * price of the product (the one that was displayed when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            productsView2.getPriceTextField().setText(initialPrice);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Add Button from the second Product View
     */
    class AddButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and adds a new product to the database or
         * displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String name = productsView2.getNameTextField().getText();
            String quantity = productsView2.getQuantityTextField().getText();
            String price = productsView2.getPriceTextField().getText();

            try
            {
                productsBLL.insertProduct(name, Integer.parseInt(quantity), Double.parseDouble(price));
                productsView2.showMessage("Product added successfully!");
            }
            catch (Exception ex)
            {
                productsView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Edit Button from the second Product View
     */
    class EditButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and edits an existing product from the database
         * or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String name = productsView2.getNameTextField().getText();
            String quantity = productsView2.getQuantityTextField().getText();
            String price = productsView2.getPriceTextField().getText();

            try
            {
                productsBLL.updateProduct(product, name, Integer.parseInt(quantity), Double.parseDouble(price));
                productsView2.showMessage("Product updated successfully!");
            }
            catch (Exception ex)
            {
                productsView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Delete Button from the second Product View
     */
    class DeleteButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and deletes an existing product from the
         * database or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                productsBLL.deleteProduct(product);
                productsView2.showMessage("Product deleted successfully!");
            }
            catch (Exception ex)
            {
                productsView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Add Button from the first Product View
     */
    class AddProductButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Product View so that the
         * user can enter the parameters for a new product to be created or displays a prompt containing the error
         * message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            productsView2 = new ProductsView2(windowListener);
            productsView2.getEditProductButton().setEnabled(false);
            productsView2.getDeleteProductButton().setEnabled(false);
            productsView2.getTitleLabel().setText("<html><div style='text-align: center;'>Add<br>Product</div></html>");
            productsView2.addResetNameButtonListener(new ResetNameButtonListener());
            productsView2.addResetQuantityButtonListener(new ResetQuantityButtonListener());
            productsView2.addResetPriceButtonListener(new ResetPriceButtonListener());
            productsView2.addAddProductButtonListener(new AddButtonListener());

            initialName = productsView2.getNameTextField().getText();
            initialQuantity = productsView2.getQuantityTextField().getText();
            initialPrice = productsView2.getPriceTextField().getText();
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Edit Button from the first Product View
     */
    class EditProductButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Product View so that the
         * user can modify the parameters of an existing product to be edited (which was selected by the user) or
         * displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            productsView2 = new ProductsView2(windowListener);
            productsView2.getAddProductButton().setEnabled(false);
            productsView2.getDeleteProductButton().setEnabled(false);
            productsView2.getTitleLabel().setText("<html><div style='text-align: center;'>Edit<br>Product</div></html>");
            productsView2.addResetNameButtonListener(new ResetNameButtonListener());
            productsView2.addResetQuantityButtonListener(new ResetQuantityButtonListener());
            productsView2.addResetPriceButtonListener(new ResetPriceButtonListener());
            productsView2.addEditProductButtonListener(new EditButtonListener());

            try
            {
                product = productsBLL.findProductByName((String) productsView.getProductsComboBox().getSelectedItem());
                productsView2.getNameTextField().setText(product.getName());
                productsView2.getQuantityTextField().setText(String.valueOf(product.getQuantity()));
                productsView2.getPriceTextField().setText(String.valueOf(product.getPrice()));
            }
            catch (Exception ex)
            {
                productsView2.showErrorMessage(ex.getMessage());
            }

            initialName = productsView2.getNameTextField().getText();
            initialQuantity = productsView2.getQuantityTextField().getText();
            initialPrice = productsView2.getPriceTextField().getText();
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Delete Button from the first Product View
     */
    class DeleteProductButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Product View so that the
         * user can delete an existing product (which was selected by the user) or displays a prompt containing the
         * error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            productsView2 = new ProductsView2(windowListener);
            productsView2.getAddProductButton().setEnabled(false);
            productsView2.getEditProductButton().setEnabled(false);
            productsView2.getResetNameButton().setEnabled(false);
            productsView2.getResetQuantityButton().setEnabled(false);
            productsView2.getResetPriceButton().setEnabled(false);
            productsView2.getNameTextField().setEnabled(false);
            productsView2.getQuantityTextField().setEnabled(false);
            productsView2.getPriceTextField().setEnabled(false);
            productsView2.getTitleLabel().setText("<html><div style='text-align: center;'>Delete<br>Product</div></html>");
            productsView2.addDeleteProductButtonListener(new DeleteButtonListener());

            try
            {
                product = productsBLL.findProductByName((String) productsView.getProductsComboBox().getSelectedItem());
                productsView2.getNameTextField().setText(product.getName());
                productsView2.getQuantityTextField().setText(String.valueOf(product.getQuantity()));
                productsView2.getPriceTextField().setText(String.valueOf(product.getPrice()));
            }
            catch (Exception ex)
            {
                productsView2.showErrorMessage(ex.getMessage());
            }
        }
    }
}