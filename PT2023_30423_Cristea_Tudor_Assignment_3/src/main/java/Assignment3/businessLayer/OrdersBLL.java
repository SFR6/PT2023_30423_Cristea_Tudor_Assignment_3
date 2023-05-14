package Assignment3.businessLayer;

import Assignment3.dataAccessLayer.OrdersDAO;
import Assignment3.modelLayer.Orders;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *  This class represents the data access object class for the Orders class
 *  @author Tudor Cristea
 */
public class OrdersBLL
{
    /**
     * The orders data access object
     */
    private OrdersDAO ordersDAO;

    /**
     * This is the only constructor of this class. It creates a new orders data access object in order to facilitate
     * the communication with the database
     */
    public OrdersBLL()
    {
        ordersDAO = new OrdersDAO();
    }

    /**
     * This method checks if the data entered by the user is valid or not, i.e. for the Quantity Text Field, it should
     * not be empty or contain anything other than digits and the quantity of the order should not be bigger than the
     * quantity of the product
     * @param quantity is a String which represents the data taken from the Quantity Text Field (entered by the user)
     * @param productQuantity is an int which represents the Product's current Quantity
     * @return true if the data is valid
     * @throws Exception if the data is not valid
     */
    private boolean isDataValid(String quantity, int productQuantity) throws Exception
    {
        String quantityRegex = "^[1-9]\\d*$";
        if (quantity.equals(""))
        {
            throw new Exception("Quantity: empty string");
        }
        else if (!Pattern.matches(quantityRegex, quantity))
        {
            throw new Exception("Quantity: invalid integer number");
        }

        if (productQuantity < Integer.parseInt(quantity))
        {
            throw new Exception("Quantity: quantity of the order cannot be larger than the quantity of the product");
        }

        return true;
    }

    /**
     * This method returns the list of all orders from the database
     * @return the list of orders
     * @throws Exception if something goes wrong when communicating with the orders dao
     */
    public ArrayList<Orders> findAllOrders() throws Exception
    {
        return new ArrayList<>(ordersDAO.findAll());
    }

    /**
     * This method returns an order that has a certain name
     * @param id the id of the searched order
     * @return the order with the specific name
     * @throws Exception if something goes wrong when communicating with the orders dao
     */
    public Orders findOrderById(int id) throws Exception
    {
        return ordersDAO.findById(id);
    }

    /**
     * This method computes the id of the new order, and then inserts a new order into the database
     * @param clientId the client id of the new order
     * @param productId the product id of the new order
     * @param quantity the quantity of the new order
     * @param productQuantity the quantity of the product corresponding to the new order
     * @return the new order
     * @throws Exception if something goes wrong when communicating with the orders dao
     */
    public Orders insertOrder(int clientId, int productId, int quantity, int productQuantity) throws Exception
    {
        if (isDataValid(String.valueOf(quantity), productQuantity))
        {
            int id = ordersDAO.getMaxId() + 1;
            Orders newOrders = new Orders(id, clientId, productId, quantity);
            ordersDAO.insert(newOrders);
            return newOrders;
        }

        return null;
    }

    /**
     * This method updates an existing order from the database
     * @param orders the order to be updated
     * @param quantity the (new) quantity of the order
     * @param productQuantity the quantity of the product corresponding to the order
     * @param clientId the (new) client id of the order
     * @param productId the (new) product id of the order
     * @return the updated order
     * @throws Exception if something goes wrong when communicating with the orders dao
     */
    public Orders updateOrder(Orders orders, int quantity, int productQuantity, int clientId, int productId) throws Exception
    {
        if (isDataValid(String.valueOf(quantity), productQuantity))
        {
            orders.setClientId(clientId);
            orders.setProductId(productId);
            orders.setQuantity(quantity);

            ordersDAO.update(orders);
            return orders;
        }

        return null;
    }

    /**
     * This method deletes an existing order from the database
     * @param orders the order to be deleted
     * @throws Exception if something goes wrong when communicating with the orders dao
     */
    public void deleteOrder(Orders orders) throws Exception
    {
        ordersDAO.delete(orders);
    }
}