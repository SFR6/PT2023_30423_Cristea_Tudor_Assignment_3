package Assignment3.businessLayer;

import Assignment3.dataAccessLayer.ClientDAO;
import Assignment3.modelLayer.Client;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *  This class represents the data access object class for the Client class
 *  @author Tudor Cristea
 */
public class ClientsBLL
{
    /**
     * The client data access object
     */
    private ClientDAO clientDAO;

    /**
     * This is the only constructor of this class. It creates a new client data access object in order to facilitate
     * the communication with the database
     */
    public ClientsBLL()
    {
        clientDAO = new ClientDAO();
    }

    /**
     * This method checks if the data entered by the user is valid or not, i.e. for the Name Text Field, it should not
     * be empty or contain anything other than letters, spaces or dashes and for the Phone Text Field, it should not be
     * empty or contain anything other than digits
     * @param name is a String which represents the data taken from the Name Text Field (entered by the user)
     * @param phoneNumber is a String which represents the data taken from the Phone Number Text Field (entered by the user)
     * @return true if the data is valid
     * @throws Exception if the data is not valid
     */
    private boolean isDataValid(String name, String phoneNumber) throws Exception
    {
        String nameRegex = "^[a-zA-Z\\s\\-]+$";
        if (name.equals(""))
        {
            throw new Exception("Name: empty string");
        }
        else if (!Pattern.matches(nameRegex, name))
        {
            throw new Exception("Name: invalid string");
        }

        String phoneNumberRegex = "^07\\d{8}$";
        if (phoneNumber.equals(""))
        {
            throw new Exception("Phone Number: empty string");
        }
        else if (!Pattern.matches(phoneNumberRegex, phoneNumber))
        {
            throw new Exception("Phone Number: invalid string");
        }

        return true;
    }

    /**
     * This method returns the list of all clients from the database
     * @return the list of clients
     * @throws Exception if something goes wrong when communicating with the client dao
     */
    public ArrayList<Client> findAllClients() throws Exception
    {
        return new ArrayList<>(clientDAO.findAll());
    }

    /**
     * This method returns a client that has a certain id
     * @param id the name of the searched client
     * @return the client with the specific id
     * @throws Exception if something goes wrong when communicating with the client dao
     */
    public Client findClientById(int id) throws Exception
    {
        return clientDAO.findById(id);
    }

    /**
     * This method returns a client that has a certain name
     * @param name the name of the searched client
     * @return the client with the specific name
     * @throws Exception if something goes wrong when communicating with the client dao
     */
    public Client findClientByName(String name) throws Exception
    {
        return clientDAO.findByName(name);
    }

    /**
     * This method computes the id of the new client, and then inserts a new client into the database
     * @param name the name of the new client
     * @param phoneNumber the phone number of the new client
     * @throws Exception if something goes wrong when communicating with the client dao
     */
    public void insertClient(String name, String phoneNumber) throws Exception
    {
        if (isDataValid(name, phoneNumber))
        {
            int id = clientDAO.getMaxId() + 1;
            clientDAO.insert(new Client(id, name, phoneNumber));
        }
    }

    /**
     * This method updates an existing client from the database
     * @param client the client to be updated
     * @param name the (new) name of the client
     * @param phoneNumber the (new) phone number of the client
     * @throws Exception if something goes wrong when communicating with the client dao
     */
    public void updateClient(Client client, String name, String phoneNumber) throws Exception
    {
        if (isDataValid(name, phoneNumber))
        {
            client.setName(name);
            client.setPhoneNumber(phoneNumber);
            clientDAO.update(client);
        }
    }

    /**
     * This method deletes an existing client from the database
     * @param client the client to be deleted
     * @throws Exception if something goes wrong when communicating with the client dao
     */
    public void deleteClient(Client client) throws Exception
    {
        clientDAO.delete(client);
    }
}