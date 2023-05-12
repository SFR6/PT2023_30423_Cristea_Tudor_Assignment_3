package Assignment3.businessLayer;

import Assignment3.dataAccessLayer.BillDAO;
import Assignment3.dataAccessLayer.ClientDAO;
import Assignment3.dataAccessLayer.OrdersDAO;
import Assignment3.dataAccessLayer.ProductDAO;
import Assignment3.modelLayer.Bill;
import Assignment3.modelLayer.Client;
import Assignment3.modelLayer.Orders;
import Assignment3.modelLayer.Product;
import Assignment3.presentationLayer.MainView;
import Assignment3.presentationLayer.bills.BillsView;
import Assignment3.presentationLayer.clients.ClientsView;
import Assignment3.presentationLayer.orders.OrdersView;
import Assignment3.presentationLayer.products.ProductsView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class controls the main view of the application. It calls methods that instantiate the first views for the
 *  Product, Client, Order and Bill classes in order to make the navigation more organized
 *  @author Tudor Cristea
 */
public class MainBLL
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
    public MainBLL(MainView view)
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
            ClientDAO clientDAO = new ClientDAO();
            List<Client> clientList = null;
            try
            {
                clientList = new ArrayList<>(clientDAO.findAll());
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

            ClientsBLL clientsBLL = new ClientsBLL(clientsView);
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
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = null;
            try
            {
                productList = new ArrayList<>(productDAO.findAll());
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

            ProductsBLL productsBLL = new ProductsBLL(productsView);
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
            OrdersDAO ordersDAO = new OrdersDAO();
            List<Orders> ordersList = null;
            try
            {
                ordersList = new ArrayList<>(ordersDAO.findAll());
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

            ProductDAO productDAO = new ProductDAO();
            ClientDAO clientDAO = new ClientDAO();
            for (Orders orders : ordersList)
            {
                try
                {
                    double totalSum = productDAO.findById(orders.getProductId()).getPrice() * orders.getQuantity();
                    Client client = clientDAO.findById(orders.getClientId());
                    Product product = productDAO.findById(orders.getProductId());
                    ((DefaultTableModel) ordersView.getOrdersTable().getModel()).addRow(new String[]{String.valueOf(orders.getId()), client.getName(), product.getName(), String.valueOf(orders.getQuantity()), String.valueOf(totalSum)});
                    ordersView.getOrdersComboBox().addItem(String.valueOf(orders.getId()));
                }
                catch (Exception ex)
                {
                    ordersView.showErrorMessage(ex.getMessage());
                }
            }

            OrdersBLL ordersBLL = new OrdersBLL(ordersView, ordersList.size() == 0);
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
            BillDAO billDAO = new BillDAO();
            List<Bill> billList = null;
            try
            {
                billList = new ArrayList<>(billDAO.findAll());
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