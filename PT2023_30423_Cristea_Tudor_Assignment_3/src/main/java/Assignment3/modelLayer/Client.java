package Assignment3.modelLayer;

/**
 * This class contains fields and methods for the Client object
 * @author Tudor Cristea
 */
public class Client
{
    /**
     * This field represents the unique identifier of a client
     */
    private int id;
    /**
     * This field represents the unique name of a client
     */
    private String name;
    /**
     * This field represents the unique phone number of a client
     */
    private String phoneNumber;
    /**
     * This is one of the constructors of this class. It does not initialize any field of a newly created Client object
     */
    public Client()
    {

    }

    /**
     * This is one of the constructors of this class. It initializes all fields of a newly created Client object
     * @param id the id of the newly created Client object
     * @param name the name of the newly created Client object
     * @param phoneNumber the phone number of the newly created Client object
     */
    public Client(int id, String name, String phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method is the accessor for the id field
     * @return the current id of a Client object
     */
    public int getId()
    {
        return id;
    }

    /**
     * This method is the mutator for the id field
     * @param id the new id of a Client object
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * This method is the accessor for the name field
     * @return the current name of a Client object
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is the mutator for the name field
     * @param name the new name of a Client object
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method is the accessor for the phoneNumber field
     * @return the current phoneNumber of a Client object
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * This method is the mutator for the phoneNumber field
     * @param phoneNumber the new phoneNumber of a Client object
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method was overridden for debugging purposes
     * @return a String containing all the fields, including their current values of a Client object
     */
    @Override
    public String toString()
    {
        return "Client [" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ']';
    }
}