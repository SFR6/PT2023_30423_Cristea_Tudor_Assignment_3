package Assignment3.presentationLayer.controllers;

import Assignment3.businessLayer.BillsBLL;
import Assignment3.businessLayer.ClientsBLL;
import Assignment3.businessLayer.OrdersBLL;
import Assignment3.businessLayer.ProductsBLL;
import Assignment3.modelLayer.Bill;
import Assignment3.modelLayer.Client;
import Assignment3.modelLayer.Orders;
import Assignment3.modelLayer.Product;
import Assignment3.presentationLayer.views.MainView;
import Assignment3.presentationLayer.views.bills.BillsView;
import Assignment3.presentationLayer.views.clients.ClientsView;
import Assignment3.presentationLayer.views.orders.OrdersView;
import Assignment3.presentationLayer.views.products.ProductsView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *  This class controls the main view of the application. It calls methods that instantiate the first views for the
 *  Product, Client, Order and Bill classes in order to make the navigation more organized
 *  @author Tudor Cristea
 */
public class MainController
{
    /**
     * The main view (part of the GUI)
     */
    private MainView view;

    /**
     * The windowListener of the first views for the Product, Client, Order and Bill classes
     */
    private WindowListener windowListener;

    /**
     * This is the only constructor of this class
     * @param view is the view that is controlled by this class
     */
    public MainController(MainView view)
    {
        this.view = view;

        windowListener = new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                view.setEnabled(false);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                view.setEnabled(true);
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

        this.view.addClientsButtonListener(new ClientsButtonListener());
        this.view.addProductsButtonListener(new ProductsButtonListener());
        this.view.addOrdersButtonListener(new OrdersButtonListener());
        this.view.addBillsButtonListener(new BillsButtonListener());
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Clients Button
     */
    class ClientsButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and displays a table containing all the clients
         * from the database or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ClientsView clientsView = new ClientsView(windowListener);
            ClientsBLL clientsBLL  = new ClientsBLL();
            List<Client> clientList = null;
            try
            {
                clientList = clientsBLL.findAllClients();
            }
            catch (Exception ex)
            {
                clientsView.showErrorMessage(ex.getMessage());
            }
            Field[] fields = Client.class.getDeclaredFields();
            String[] headerTitles = new String[fields.length];
            for (int i = 0; i < fields.length; ++i)
            {
                headerTitles[i] = fields[i].getName();
            }
            clientsView.getClientsTable().setModel(new DefaultTableModel(headerTitles, 0));
            clientsView.getClientsComboBox().removeAllItems();

            for (Client client: clientList)
            {
                ((DefaultTableModel) clientsView.getClientsTable().getModel()).addRow(new String[]{String.valueOf(client.getId()), client.getName(), client.getPhoneNumber()});
                clientsView.getClientsComboBox().addItem(client.getName());
            }

            ClientsController clientsController = new ClientsController(clientsView);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Products Button
     */
    class ProductsButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and displays a table containing all the products
         * from the database or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ProductsView productsView = new ProductsView(windowListener);
            ProductsBLL productsBLL = new ProductsBLL();
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

            ProductsController productsController = new ProductsController(productsView);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Orders Button
     */
    class OrdersButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and displays a table containing all the orders
         * from the database or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            OrdersView ordersView = new OrdersView(windowListener);
            OrdersBLL ordersBLL = new OrdersBLL();
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

            ClientsBLL clientsBLL = new ClientsBLL();
            ProductsBLL productsBLL = new ProductsBLL();
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

            OrdersController ordersController = new OrdersController(ordersView, ordersList.size() == 0);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Bills Button
     */
    class BillsButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and displays a table containing all the bills
         * from the database or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BillsView billsView = new BillsView(windowListener);
            BillsBLL billsBLL = new BillsBLL();
            List<Bill> billList = null;
            try
            {
                billList = billsBLL.findAllBills();
            }
            catch (Exception ex)
            {
                billsView.showErrorMessage(ex.getMessage());
            }
            Field[] fields = Bill.class.getDeclaredFields();
            String[] headerTitles = new String[fields.length];
            for (int i = 0; i < fields.length; ++i)
            {
                headerTitles[i] = fields[i].getName();
            }
            billsView.getBillsTable().setModel(new DefaultTableModel(headerTitles, 0));

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            for (Bill bill: billList)
            {
                ((DefaultTableModel) billsView.getBillsTable().getModel()).addRow(new String[]{String.valueOf(bill.id()), String.valueOf(bill.orderId()), bill.clientName(), bill.clientPhoneNumber(), bill.productName(), String.valueOf(bill.quantity()), String.valueOf(bill.totalSum()), sdf.format(bill.date())});
            }
        }
    }
}