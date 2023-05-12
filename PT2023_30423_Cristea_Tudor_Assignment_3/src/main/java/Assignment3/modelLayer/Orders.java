package Assignment3.modelLayer;

/**
 * This class contains fields and methods for the Client object
 * @author Tudor Cristea
 */
public class Orders
{
    /**
     * This field represents the unique identifier of an order
     */
    private int id;
    /**
     * This field represents the unique identifier of the client associated with an order
     */
    private int clientId;
    /**
     * This field represents the unique identifier of the product associated with an order
     */
    private int productId;
    /**
     * This field represents the quantity of an order
     */
    private int quantity;

    /**
     * This is one of the constructors of this class. It does not initialize any field of a newly created Orders object
     */
    public Orders()
    {

    }

    /**
     * This is one of the constructors of this class. It initializes all fields of a newly created Orders object
     * @param id the id of the newly created Orders object
     * @param clientId the client id of the newly created Orders object
     * @param productId the product id of the newly created Orders object
     * @param quantity the quantity of the newly created Orders object
     */
    public Orders(int id, int clientId, int productId, int quantity)
    {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * This method is the accessor for the id field
     * @return the current id of an Orders object
     */
    public int getId()
    {
        return id;
    }

    /**
     * This method is the mutator for the id field
     * @param id the new id of an Orders object
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * This method is the accessor for the clientId field
     * @return the current clientId of an Orders object
     */
    public int getClientId()
    {
        return clientId;
    }

    /**
     * This method is the mutator for the clientId field
     * @param clientId the new clientId of an Orders object
     */
    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    /**
     * This method is the accessor for the productId field
     * @return the current productId of an Orders object
     */
    public int getProductId()
    {
        return productId;
    }

    /**
     * This method is the mutator for the productId field
     * @param productId the new productId of an Orders object
     */
    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    /**
     * This method is the accessor for the quantity field
     * @return the current quantity of an Orders object
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * This method is the mutator for the quantity field
     * @param quantity the new quantity of an Orders object
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * This method was overridden for debugging purposes
     * @return a String containing all the fields, including their current values of an Orders object
     */
    @Override
    public String toString()
    {
        return "Order [" + "id=" + id + ", clientId=" + clientId + ", productId=" + productId + ", quantity=" + quantity + ']';
    }
}