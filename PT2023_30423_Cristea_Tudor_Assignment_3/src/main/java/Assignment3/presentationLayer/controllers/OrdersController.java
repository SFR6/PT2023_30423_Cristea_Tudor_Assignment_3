package Assignment3.presentationLayer.controllers;

import Assignment3.businessLayer.BillsBLL;
import Assignment3.businessLayer.ClientsBLL;
import Assignment3.businessLayer.OrdersBLL;
import Assignment3.businessLayer.ProductsBLL;
import Assignment3.modelLayer.Client;
import Assignment3.modelLayer.Orders;
import Assignment3.modelLayer.Product;
import Assignment3.presentationLayer.views.orders.OrdersView;
import Assignment3.presentationLayer.views.orders.OrdersView2;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

/**
 *  This class controls the two orders views. It calls methods that communicate with and modify the Orders table from
 *  the database as well as methods that communicate with the user through the interface
 *  @author Tudor Cristea
 */
public class OrdersController
{
    /**
     * The business logic layer of the orders
     */
    private OrdersBLL ordersBLL;

    /**
     * The first order view (part of the GUI)
     */
    private OrdersView ordersView;
    /**
     * The second order view (part of the GUI)
     */
    private OrdersView2 ordersView2;

    /**
     * The order to be deleted/updated
     */
    private Orders order;

    /**
     * The initial name of the client - in case of adding an order, it will be an empty string
     *                                - in case of editing an order, it will be the name of the client associated with that order
     */
    private String initialClientName;
    /**
     * The initial name of the product - in case of adding an order, it will be an empty string
     *                                 - in case of editing an order, it will be the name of the product associated with that order
     */
    private String initialProductName;
    /**
     * The initial quantity of the order - in case of adding an order, it will be an empty string
     *                                   - in case of editing an order, it will be the quantity of the order
     */
    private String initialQuantity;

    /**
     * The windowListener of the first view
     */
    private WindowListener windowListener;

    /**
     * This is the only constructor of this class
     * @param ordersView is the view that is controlled by this class
     * @param noOrders is the number of orders in the database; based on this parameter, the edit and delete order will be disabled or enabled
     */
    public OrdersController(OrdersView ordersView, boolean noOrders)
    {
        this.ordersView = ordersView;
        this.ordersBLL = new OrdersBLL();

        windowListener = new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                ordersView.setEnabled(false);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                ordersView.setEnabled(true);
                List<Orders> ordersList = null;
                try
                {
                    ordersList = ordersBLL.findAllOrders();
                }
                catch (Exception ex)
                {
                    ordersView.showErrorMessage(ex.getMessage());
                }
                Field[] fields = Orders.class.getDeclaredFields();
                String[] headerTitles = new String[fields.length];
                for (int i = 0; i < fields.length; ++i)
                {
                    headerTitles[i] = fields[i].getName();
                    if (i != 0)
                    {
                        headerTitles[i] = headerTitles[i].replace("Id", "Name");
                    }
                }
                ordersView.getOrdersTable().setModel(new DefaultTableModel(headerTitles, 0));
                ordersView.getOrdersComboBox().removeAllItems();

                ProductsBLL productsBLL = new ProductsBLL();
                ClientsBLL clientsBLL = new ClientsBLL();
                for (Orders orders : ordersList)
                {
                    try
                    {
                        Client client = clientsBLL.findClientById(orders.getClientId());
                        Product product = productsBLL.findProductById(orders.getProductId());
                        ((DefaultTableModel) ordersView.getOrdersTable().getModel()).addRow(new String[]{String.valueOf(orders.getId()), client.getName(), product.getName(), String.valueOf(orders.getQuantity())});
                        ordersView.getOrdersComboBox().addItem(String.valueOf(orders.getId()));
                    }
                    catch (Exception ex)
                    {
                        ordersView.showErrorMessage(ex.getMessage());
                    }
                }

                ordersView.getEditOrderButton().setEnabled(!(ordersList.size() == 0));
                ordersView.getDeleteOrderButton().setEnabled(!(ordersList.size() == 0));
                ordersView.getOrdersComboBox().setEnabled(!(ordersList.size() == 0));
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

        this.ordersView.addAddOrderButtonListener(new AddOrdersButtonListener());
        this.ordersView.addEditOrderButtonListener(new EditOrdersButtonListener());
        this.ordersView.addDeleteOrderButtonListener(new DeleteOrdersButtonListener());

        this.ordersView.getEditOrderButton().setEnabled(!noOrders);
        this.ordersView.getDeleteOrderButton().setEnabled(!noOrders);
        ordersView.getOrdersComboBox().setEnabled(!noOrders);
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Client Button
     */
    class ResetClientButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Client ComboBox's selected item
         * to the initial client (the one that was selected when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ordersView2.getClientComboBox().setSelectedItem(initialClientName);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Product Button
     */
    class ResetProductButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Product ComboBox's selected item
         * to the initial product (the one that was selected when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ordersView2.getProductComboBox().setSelectedItem(initialProductName);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Quantity Button
     */
    class ResetQuantityButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Quantity's Text Field to the
         * initial one (the one that was present when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ordersView2.getQuantityTextField().setText(initialQuantity);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Add Button from the second Order View
     */
    class AddButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and adds a new order to the database or
         * displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ClientsBLL clientsBLL = new ClientsBLL();
            ProductsBLL productsBLL = new ProductsBLL();
            BillsBLL billsBLL = new BillsBLL();

            String quantity = ordersView2.getQuantityTextField().getText();
            try
            {
                Client client = clientsBLL.findClientByName((String)ordersView2.getClientComboBox().getSelectedItem());
                Product product = productsBLL.findProductByName((String)ordersView2.getProductComboBox().getSelectedItem());

                order = ordersBLL.insertOrder(client.getId(), product.getId(), Integer.parseInt(quantity), product.getQuantity());
                billsBLL.insertBill(order, client, product);
                ordersView2.showMessage("Order added successfully!");
                product.setQuantity(product.getQuantity() - Integer.parseInt(quantity));
                productsBLL.updateProduct(product, product.getName(), product.getQuantity(), product.getPrice());
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Edit Button from the second Order View
     */
    class EditButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and edits an existing order from the database or
         * displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ClientsBLL clientsBLL = new ClientsBLL();
            ProductsBLL productsBLL = new ProductsBLL();
            BillsBLL billsBLL = new BillsBLL();

            String quantity = ordersView2.getQuantityTextField().getText();

            try
            {
                Client client = clientsBLL.findClientByName((String)ordersView2.getClientComboBox().getSelectedItem());
                Product product = productsBLL.findProductByName((String)ordersView2.getProductComboBox().getSelectedItem());

                order = ordersBLL.updateOrder(order, Integer.parseInt(quantity), product.getQuantity(), client.getId(), product.getId());
                billsBLL.insertBill(order, client, product);
                ordersView2.showMessage("Order updated successfully!");
                if (initialProductName.equals(product.getName()))
                {
                    int diff = Integer.parseInt(quantity) - Integer.parseInt(initialQuantity);
                    product.setQuantity(product.getQuantity() - diff);
                    productsBLL.updateProduct(product, product.getName(), product.getQuantity(), product.getPrice());
                }
                else
                {
                    Product initialProduct = productsBLL.findProductByName(initialProductName);
                    initialProduct.setQuantity(initialProduct.getQuantity() + Integer.parseInt(initialQuantity));
                    productsBLL.updateProduct(initialProduct, initialProduct.getName(), initialProduct.getQuantity(), initialProduct.getPrice());

                    product.setQuantity(product.getQuantity() - Integer.parseInt(quantity));
                    productsBLL.updateProduct(product, product.getName(), product.getQuantity(), product.getPrice());
                }

            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Delete Button from the second Order View
     */
    class DeleteButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and deletes an existing order from the database
         * or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ProductsBLL productsBLL = new ProductsBLL();

            try
            {
                ordersBLL.deleteOrder(order);
                ordersView2.showMessage("Order deleted successfully!");
                Product product = productsBLL.findProductByName((String)ordersView2.getProductComboBox().getSelectedItem());
                product.setQuantity(product.getQuantity() + order.getQuantity());
                productsBLL.updateProduct(product, product.getName(), product.getQuantity(), product.getPrice());
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Add Button from the first Order View
     */
    class AddOrdersButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Order View so that the user
         * can enter the parameters for a new order to be created or displays a prompt containing the error message, if
         * it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ordersView2 = new OrdersView2(windowListener);
            ordersView2.getEditOrderButton().setEnabled(false);
            ordersView2.getDeleteOrderButton().setEnabled(false);
            ordersView2.getTitleLabel().setText("<html><div style='text-align: center;'>Add<br>Order</div></html>");
            ordersView2.addAddOrderButtonListener(new AddButtonListener());
            ordersView2.addResetClientButtonListener(new ResetClientButtonListener());
            ordersView2.addResetProductButtonListener(new ResetProductButtonListener());
            ordersView2.addResetQuantityButtonListener(new ResetQuantityButtonListener());

            ClientsBLL clientsBLL = new ClientsBLL();
            List<Client> clientList = null;
            try
            {
                clientList = clientsBLL.findAllClients();
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
            ordersView2.getClientComboBox().removeAllItems();
            for (Client client: clientList)
            {
                ordersView2.getClientComboBox().addItem(client.getName());
            }

            ProductsBLL productsBLL = new ProductsBLL();
            List<Product> productList = null;
            try
            {
                productList = productsBLL.findAllProducts();
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
            ordersView2.getProductComboBox().removeAllItems();
            for (Product product: productList)
            {
                ordersView2.getProductComboBox().addItem(product.getName());
            }

            initialClientName = (String)ordersView2.getClientComboBox().getSelectedItem();
            initialProductName = (String)ordersView2.getProductComboBox().getSelectedItem();
            initialQuantity = ordersView2.getQuantityTextField().getText();
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Edit Button from the first Order View
     */
    class EditOrdersButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Order View so that the user
         * can modify the parameters of an existing order to be edited (which was selected by the user) or displays a
         * prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ordersView2 = new OrdersView2(windowListener);
            ordersView2.getAddOrderButton().setEnabled(false);
            ordersView2.getDeleteOrderButton().setEnabled(false);
            ordersView2.getTitleLabel().setText("<html><div style='text-align: center;'>Edit<br>Order</div></html>");
            ordersView2.addResetClientButtonListener(new ResetClientButtonListener());
            ordersView2.addResetProductButtonListener(new ResetProductButtonListener());
            ordersView2.addResetQuantityButtonListener(new ResetQuantityButtonListener());
            ordersView2.addEditOrderButtonListener(new EditButtonListener());

            ClientsBLL clientsBLL = new ClientsBLL();
            List<Client> clientList = null;
            try
            {
                clientList = clientsBLL.findAllClients();
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
            ordersView2.getClientComboBox().removeAllItems();
            for (Client client: clientList)
            {
                ordersView2.getClientComboBox().addItem(client.getName());
            }

            ProductsBLL productsBLL = new ProductsBLL();
            List<Product> productList = null;
            try
            {
                productList = productsBLL.findAllProducts();
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
            ordersView2.getProductComboBox().removeAllItems();
            for (Product product: productList)
            {
                ordersView2.getProductComboBox().addItem(product.getName());
            }

            try
            {
                order = ordersBLL.findOrderById(Integer.parseInt((String) ordersView.getOrdersComboBox().getSelectedItem()));
                Product product = productsBLL.findProductById(order.getProductId());
                Client client = clientsBLL.findClientById(order.getClientId());
                ordersView2.getProductComboBox().setSelectedItem(product.getName());
                ordersView2.getClientComboBox().setSelectedItem(client.getName());
                ordersView2.getQuantityTextField().setText(String.valueOf(order.getQuantity()));
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }

            initialClientName = (String)ordersView2.getClientComboBox().getSelectedItem();
            initialProductName = (String)ordersView2.getProductComboBox().getSelectedItem();
            initialQuantity = ordersView2.getQuantityTextField().getText();
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Delete Button from the first Order View
     */
    class DeleteOrdersButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Order View so that the user
         * can delete an existing order (which was selected by the user) or displays a prompt containing the error
         * message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ordersView2 = new OrdersView2(windowListener);
            ordersView2.getAddOrderButton().setEnabled(false);
            ordersView2.getEditOrderButton().setEnabled(false);
            ordersView2.getResetClientButton().setEnabled(false);
            ordersView2.getResetProductButton().setEnabled(false);
            ordersView2.getResetQuantityButton().setEnabled(false);
            ordersView2.getClientComboBox().setEnabled(false);
            ordersView2.getProductComboBox().setEnabled(false);
            ordersView2.getQuantityTextField().setEnabled(false);
            ordersView2.getTitleLabel().setText("<html><div style='text-align: center;'>Delete<br>Order</div></html>");
            ordersView2.addDeleteOrderButtonListener(new DeleteButtonListener());

            ClientsBLL clientsBLL = new ClientsBLL();
            List<Client> clientList = null;
            try
            {
                clientList = clientsBLL.findAllClients();
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
            ordersView2.getClientComboBox().removeAllItems();
            for (Client client: clientList)
            {
                ordersView2.getClientComboBox().addItem(client.getName());
            }

            ProductsBLL productsBLL = new ProductsBLL();
            List<Product> productList = null;
            try
            {
                productList = productsBLL.findAllProducts();
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
            ordersView2.getProductComboBox().removeAllItems();
            for (Product product: productList)
            {
                ordersView2.getProductComboBox().addItem(product.getName());
            }

            try
            {
                order = ordersBLL.findOrderById(Integer.parseInt((String) ordersView.getOrdersComboBox().getSelectedItem()));
                Product product = productsBLL.findProductById(order.getProductId());
                Client client = clientsBLL.findClientById(order.getClientId());
                ordersView2.getProductComboBox().setSelectedItem(product.getName());
                ordersView2.getClientComboBox().setSelectedItem(client.getName());
                ordersView2.getQuantityTextField().setText(String.valueOf(order.getQuantity()));
            }
            catch (Exception ex)
            {
                ordersView2.showErrorMessage(ex.getMessage());
            }
        }
    }
}