package Assignment3.dataAccessLayer.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  This class helps the application to establish a connection to the database
 *  @author Tudor Cristea
 */
public class ConnectionFactory
{
    /**
     *  The Logger object helps with debugging the application in case of an error by displaying, in the console,
     *  messages regarding the error and their severity
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    /**
     *  This String field contains the driver part of a bigger string which will be used to create the database
     *  connection
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     *  This String field contains the url part of a bigger string which will be used to create the database
     *  connection
     */
    private static final String DBURL = "jdbc:mysql://localhost:3306/store";
    /**
     *  This String field contains the user part of a bigger string which will be used to create the database
     *  connection
     */
    private static final String USER = "root";
    /**
     *  This String field contains the password part of a bigger string which will be used to create the database
     *  connection
     */
    private static final String PASS = "root";

    /**
     *  This ConnectionFactory field is an instance of the object itself. This is used to create a singleton. This
     *  means that only one object of this class will exist in the application
     */
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * This is the only constructor of this class
     */
    private ConnectionFactory()
    {
        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a connection to the database by using a concatenation of the last three String fields of
     * this class, or displays a warning message in the console through the Logger object, if it is unable to do so
     */
    private Connection createConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the database");
        }
        return connection;
    }

    /**
     * This method is used by other classes to get the connection to the database by creating the singleton or
     * accessing it if it was already created
     * @return a connection to the database
     */
    public static Connection getConnection()
    {
        return singleInstance.createConnection();
    }

    /**
     * This method closes the connection to the database, or displays a warning message in the console through the
     * Logger object, if it is unable to do so
     * @param connection the connection to be closed
     */
    public static void close(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection");
            }
        }
    }

    /**
     * This method closes the statement of a query, or displays a warning message in the console through the Logger
     * object, if it is unable to do so
     * @param statement the statement to be closed
     */
    public static void close(Statement statement)
    {
        if (statement != null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
            }
        }
    }

    /**
     * This method closes the result set of an executed statement, or displays a warning message in the console through
     * the Logger object, if it is unable to do so
     * @param resultSet the result set to be closed
     */
    public static void close(ResultSet resultSet)
    {
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the result set");
            }
        }
    }
}