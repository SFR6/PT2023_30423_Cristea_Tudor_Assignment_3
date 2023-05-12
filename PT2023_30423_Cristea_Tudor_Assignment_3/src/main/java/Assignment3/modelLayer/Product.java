package Assignment3.modelLayer;

/**
 * This class contains fields and methods for the Product object
 * @author Tudor Cristea
 */
public class Product
{
    /**
     * This field represents the unique identifier of a product
     */
    private int id;
    /**
     * This field represents the unique name of a product
     */
    private String name;
    /**
     * This field represents the quantity of a product
     */
    private int quantity;
    /**
     * This field represents the price of a product
     */
    private double price;

    /**
     * This is one of the constructors of this class. It does not initialize any field of a newly created Product object
     */
    public Product()
    {

    }

    /**
     * This is one of the constructors of this class. It initializes all fields of a newly created Product object
     * @param id the id of the newly created Product object
     * @param name the name of the newly created Product object
     * @param quantity the quantity of the newly created Product object
     * @param price the price of the newly created Product object
     */
    public Product(int id, String name, int quantity, double price)
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * This method is the accessor for the id field
     * @return the current id of a Product object
     */
    public int getId()
    {
        return id;
    }

    /**
     * This method is the mutator for the id field
     * @param id the new id of a Product object
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * This method is the accessor for the name field
     * @return the current name of a Product object
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is the mutator for the name field
     * @param name the new name of a Product object
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method is the accessor for the quantity field
     * @return the current quantity of a Product object
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * This method is the mutator for the quantity field
     * @param quantity the new quantity of a Product object
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * This method is the accessor for the price field
     * @return the current price of a Product object
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * This method is the mutator for the price field
     * @param price the new price of a Product object
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * This method was overridden for debugging purposes
     * @return a String containing all the fields, including their current values of a Product object
     */
    @Override
    public String toString()
    {
        return "Product [" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ']';
    }
}