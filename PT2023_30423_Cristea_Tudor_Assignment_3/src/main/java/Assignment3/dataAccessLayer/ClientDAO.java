package Assignment3.dataAccessLayer;

import Assignment3.modelLayer.Client;

import java.util.List;

/**
 * This class contains methods for the Client data access object
 * @author Tudor Cristea
 */
public class ClientDAO extends AbstractDAO<Client>
{
    /**
     * This is the only constructor of this class. It calls the constructor of its parent class
     */
    public ClientDAO()
    {
        super();
    }

    /**
     * This method overrides the original getMaxId() method from the generic class. It calls the method with the same name form its parent class
     * @return the maximum id found in the Client table
     * @throws Exception if something goes wrong when communicating with the database or if the result set returned by the executed query is null
     */
    @Override
    public int getMaxId() throws Exception
    {
        return super.getMaxId();
    }

    /**
     * This method overrides the original findAll() method from the generic class. It calls the method with the same name form its parent class
     * @return a list of Client class objects corresponding to the entities found
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public List<Client> findAll() throws Exception
    {
        return super.findAll();
    }

    /**
     * This method overrides the original findById() method from the generic class. It calls the method with the same name form its parent class
     * @param id the id of the client to be found in the Client table of the database
     * @return a Client class object corresponding to the client found in the Client table
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public Client findById(int id) throws Exception
    {
        return super.findById(id);
    }

    /**
     * This method overrides the original findByName() method from the generic class. It calls the method with the same name form its parent class
     * @param name the name of the client to be found in the Client table of the database
     * @return a Client class object corresponding to the client found in the Client table
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public Client findByName(String name) throws Exception
    {
        return super.findByName(name);
    }

    /**
     * This method inserts a new entity of type Client into the Client table
     * @param client the Client object corresponding to the inserted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void insert(Client client) throws Exception
    {
        super.insert(client);
    }

    /**
     * This method updates an existing entity of type Client from the Client table
     * @param client the Client object corresponding to the updated entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void update(Client client) throws Exception
    {
        super.update(client);
    }

    /**
     * This method deletes an existing entity of type Client from the Client table
     * @param client the Client object corresponding to the deleted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    @Override
    public void delete(Client client) throws Exception
    {
        super.delete(client);
    }
}