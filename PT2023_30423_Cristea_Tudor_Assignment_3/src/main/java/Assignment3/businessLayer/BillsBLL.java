package Assignment3.businessLayer;

import Assignment3.dataAccessLayer.BillDAO;
import Assignment3.modelLayer.Bill;
import Assignment3.modelLayer.Client;
import Assignment3.modelLayer.Orders;
import Assignment3.modelLayer.Product;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *  This class represents the data access object class for the Bill class
 *  @author Tudor Cristea
 */
public class BillsBLL
{
    /**
     * The bill data access object
     */
    private BillDAO billDAO;

    /**
     * This is the only constructor of this class. It creates a new bill data access object in order to facilitate
     * the communication with the database
     */
    public BillsBLL()
    {
        billDAO = new BillDAO();
    }

    /**
     * This method returns the list of all bills from the database
     * @return the list of bills
     * @throws Exception if something goes wrong when communicating with the bill dao
     */
    public ArrayList<Bill> findAllBills() throws Exception
    {
        return new ArrayList<>(billDAO.findAll());
    }

    /**
     * This method computes the id of the new product, and then inserts a new product into the database
     * @param orders the order corresponding to the new bill
     * @param client the client corresponding to the new bill
     * @param product the product corresponding to the new bill
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public void insertBill(Orders orders, Client client, Product product) throws Exception
    {
        int billId = billDAO.getMaxId() + 1;
        billDAO.insert(new Bill(billId, orders.getId(), client.getName(), client.getPhoneNumber(), product.getName(), orders.getQuantity(), orders.getQuantity() * product.getPrice(), new Timestamp(System.currentTimeMillis())));
    }
}