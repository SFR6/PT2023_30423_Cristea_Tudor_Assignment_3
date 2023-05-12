package Assignment3.dataAccessLayer;

import Assignment3.modelLayer.Bill;

import java.util.List;

/**
 * This class contains methods for the Bill data access object
 * @author Tudor Cristea
 */
public class BillDAO extends AbstractDAO<Bill>
{
    /**
     * This is the only constructor of this class. It calls the constructor of its parent class
     */
    public BillDAO()
    {
        super();
    }

    /**
     * This method overrides the original getMaxId() method from the generic class. It calls the method with the same name form its parent class
     * @return the maximum id found in the Bill table
     * @throws Exception if something goes wrong when communicating with the database or if the result set returned by the executed query is null
     */
    @Override
    public int getMaxId() throws Exception
    {
        return super.getMaxId();
    }

    /**
     * This method overrides the original findAll() method from the generic class. It calls the method with the same name form its parent class
     * @return a list of Bill class objects corresponding to the entities found
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public List<Bill> findAll() throws Exception
    {
        return super.findAll();
    }

    /**
     * This method inserts a new entity of type Bill into the Bill table
     * @param bill the Bill object corresponding to the inserted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void insert(Bill bill) throws Exception
    {
        super.insert(bill);
    }
}