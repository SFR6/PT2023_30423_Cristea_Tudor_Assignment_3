package Assignment3.dataAccessLayer;

import Assignment3.modelLayer.Orders;

import java.util.List;

/**
 * This class contains methods for the Orders data access object
 * @author Tudor Cristea
 */
public class OrdersDAO extends AbstractDAO<Orders>
{
    /**
     * This is the only constructor of this class. It calls the constructor of its parent class
     */
    public OrdersDAO()
    {
        super();
    }

    /**
     * This method overrides the original getMaxId() method from the generic class. It calls the method with the same name form its parent class
     * @return the maximum id found in the Orders table
     * @throws Exception if something goes wrong when communicating with the database or if the result set returned by the executed query is null
     */
    @Override
    public int getMaxId() throws Exception
    {
        return super.getMaxId();
    }

    /**
     * This method overrides the original findAll() method from the generic class. It calls the method with the same name form its parent class
     * @return a list of Orders class objects corresponding to the entities found
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public List<Orders> findAll() throws Exception
    {
        return super.findAll();
    }

    /**
     * This method overrides the original findById() method from the generic class. It calls the method with the same name form its parent class
     * @param id the id of the order to be found in the Orders table of the database
     * @return an Orders class object corresponding to the order found in the Orders table
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public Orders findById(int id) throws Exception
    {
        return super.findById(id);
    }

    /**
     * This method inserts a new entity of type Orders into the Orders table
     * @param orders the Orders object corresponding to the inserted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void insert(Orders orders) throws Exception
    {
        super.insert(orders);
    }

    /**
     * This method updates an existing entity of type Orders from the Orders table
     * @param orders the Orders object corresponding to the updated entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void update(Orders orders) throws Exception
    {
        super.update(orders);
    }

    /**
     * This method deletes an existing entity of type Orders from the Orders table
     * @param orders the Orders object corresponding to the deleted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void delete(Orders orders) throws Exception
    {
        super.delete(orders);
    }
}