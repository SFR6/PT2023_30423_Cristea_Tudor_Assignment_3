package Assignment3.dataAccessLayer;

import Assignment3.modelLayer.Product;

import java.util.List;

/**
 * This class contains methods for the Product data access object
 * @author Tudor Cristea
 */
public class ProductDAO extends AbstractDAO<Product>
{
    /**
     * This is the only constructor of this class. It calls the constructor of its parent class
     */
    public ProductDAO()
    {
        super();
    }

    /**
     * This method overrides the original getMaxId() method from the generic class. It calls the method with the same name form its parent class
     * @return the maximum id found in the Product table
     * @throws Exception if something goes wrong when communicating with the database or if the result set returned by the executed query is null
     */
    @Override
    public int getMaxId() throws Exception
    {
        return super.getMaxId();
    }

    /**
     * This method overrides the original findAll() method from the generic class. It calls the method with the same name form its parent class
     * @return a list of Product class objects corresponding to the entities found
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public List<Product> findAll() throws Exception
    {
        return super.findAll();
    }

    /**
     * This method overrides the original findById() method from the generic class. It calls the method with the same name form its parent class
     * @param id the id of the product to be found in the Product table of the database
     * @return a Product class object corresponding to the product found in the Product table
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public Product findById(int id) throws Exception
    {
        return super.findById(id);
    }

    /**
     * This method overrides the original findByName() method from the generic class. It calls the method with the same name form its parent class
     * @param name the name of the product to be found in the Product table of the database
     * @return a Product class object corresponding to the product found in the Product table
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public Product findByName(String name) throws Exception
    {
        return super.findByName(name);
    }

    /**
     * This method inserts a new entity of type Product into the Product table
     * @param product the Product object corresponding to the inserted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void insert(Product product) throws Exception
    {
        super.insert(product);
    }

    /**
     * This method updates an existing entity of type Product from the Product table
     * @param product the Product object corresponding to the updated entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void update(Product product) throws Exception
    {
        super.update(product);
    }

    /**
     * This method deletes an existing entity of type Product from the Product table
     * @param product the Product object corresponding to the deleted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void delete(Product product) throws Exception
    {
        super.delete(product);
    }
}