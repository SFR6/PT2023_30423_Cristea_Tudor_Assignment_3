package Assignment3.modelLayer;

import java.sql.Timestamp;

/**
 * This class/record contains fields and methods for the Bill object. All the methods are automatically generated
 * @author Tudor Cristea
 */
public record Bill(int id, int orderId, String clientName, String clientPhoneNumber, String productName, int quantity, double totalSum, Timestamp date)
{

}