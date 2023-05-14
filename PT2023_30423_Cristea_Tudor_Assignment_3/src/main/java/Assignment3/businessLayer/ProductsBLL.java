package Assignment3.businessLayer;

import Assignment3.dataAccessLayer.ProductDAO;
import Assignment3.modelLayer.Product;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *  This class represents the data access object class for the Product class
 *  @author Tudor Cristea
 */
public class ProductsBLL
{
    /**
     * The product data access object
     */
    private ProductDAO productDAO;

    /**
     * This is the only constructor of this class. It creates a new product data access object in order to facilitate
     * the communication with the database
     */
    public ProductsBLL()
    {
        productDAO = new ProductDAO();
    }

    /**
     * This method returns the list of all products from the database
     * @return the list of products
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public ArrayList<Product> findAllProducts() throws Exception
    {
        return new ArrayList<>(productDAO.findAll());
    }

    /**
     * This method checks if the data entered by the user is valid or not, i.e. for the Name Text Field, it should not
     * be empty or contain anything other than letters, spaces or dashes; for the Quantity Text Field, it should not
     * be empty or contain anything other than digits; for the Price Text Field it should not be empty or contain
     * anything other than an integer number or a floating point number with more than 2 decimals
     * @param name is a String which represents the data taken from the Name Text Field (entered by the user)
     * @param quantity is a String which represents the data taken from the Quantity Text Field (entered by the user)
     * @param price is a String which represents the data taken from the Price Text Field (entered by the user)
     * @return true if the data is valid
     * @throws Exception if the data is not valid
     */
    private boolean isDataValid(String name, String quantity, String price) throws Exception
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

        String quantityRegex = "^[1-9]\\d*$";
        if (quantity.equals(""))
        {
            throw new Exception("Quantity: empty string");
        }
        else if (!Pattern.matches(quantityRegex, quantity))
        {
            throw new Exception("Quantity: invalid integer number");
        }

        String priceRegex = "^[0-9]+(?:\\.[0-9]{1,2})?$";
        if (price.equals(""))
        {
            throw new Exception("Price: empty string");
        }
        else if (!Pattern.matches(priceRegex, price))
        {
            throw new Exception("Price: invalid floating point number");
        }

        return true;
    }

    /**
     * This method returns a product that has a certain id
     * @param id the id of the searched product
     * @return the product with the specific id
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public Product findProductById(int id) throws Exception
    {
        return productDAO.findById(id);
    }

    /**
     * This method returns a product that has a certain name
     * @param name the name of the searched product
     * @return the product with the specific name
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public Product findProductByName(String name) throws Exception
    {
        return productDAO.findByName(name);
    }

    /**
     * This method computes the id of the new product, and then inserts a new product into the database
     * @param name the name of the new product
     * @param quantity the phone number of the new product
     * @param price the price of the new product
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public void insertProduct(String name, int quantity, double price) throws Exception
    {
        if (isDataValid(name, String.valueOf(quantity), String.valueOf(price)))
        {
            int id = productDAO.getMaxId() + 1;
            productDAO.insert(new Product(id, name, quantity, price));
        }
    }

    /**
     * This method updates an existing product from the database
     * @param product the product to be updated
     * @param name the (new) name of the product
     * @param quantity the (new) quantity of the product
     * @param price the (new) price of the product
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public void updateProduct(Product product, String name, int quantity, double price) throws Exception
    {
        if (isDataValid(name, String.valueOf(quantity), String.valueOf(price)))
        {
            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            productDAO.update(product);
        }
    }

    /**
     * This method deletes an existing product from the database
     * @param product the product to be deleted
     * @throws Exception if something goes wrong when communicating with the product dao
     */
    public void deleteProduct(Product product ) throws Exception
    {
        productDAO.delete(product);
    }
}