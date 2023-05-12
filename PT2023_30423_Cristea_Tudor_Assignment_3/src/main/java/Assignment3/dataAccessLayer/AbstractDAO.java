package Assignment3.dataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Assignment3.dataAccessLayer.connection.ConnectionFactory;
import Assignment3.modelLayer.Bill;

/**
 * This generic class contains methods for a data access object, therefore facilitating the communication with the
 * entities of any table from the database
 * @author Tudor Cristea
 */
public class AbstractDAO<T>
{
    /**
     * This field will contain the type(class) of the object for which methods are applied on
     */
    private final Class<T> type;

    /**
     * This is the only constructor of this class. It only establishes the type(class) of the generic class
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * This method gets the maximum id of all the entities in the table of the database corresponding to the class T
     * @return the maximum id
     * @throws Exception if something goes wrong when communicating with the database or if the result set returned by the executed query is null
     */
    public int getMaxId() throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT MAX(" + type.getDeclaredFields()[0].getName() + ") FROM " + type.getSimpleName();
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return resultSet.getInt(1);
            }
            else
            {
                throw new SQLException("Result Set is null");
            }
        }
        catch (SQLException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This method creates a MySQL select query according to the field parameter
     * @param field specifies for which field to make the search in a table from the database; if it is null, then the method returns a query that will select every entity of a table
     * @return a String containing the select query
     */
    private String createSelectQuery(String field)
    {
        if (field == null)
        {
            return "SELECT * FROM " + type.getSimpleName();
        }
        return "SELECT * FROM " + type.getSimpleName() + " WHERE " + field + " = ?";
    }

    /**
     * This method uses the select query created to find all entities from a table
     * @return a list of T class objects corresponding to the entities found
     * @throws Exception if something goes wrong when communicating with the database
     */
    public List<T> findAll() throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(null);
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }
        catch (SQLException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This method uses the select query created to find a certain entity from a table that has a specific id
     * @param id the id of the searched entity
     * @return a T type object corresponding to the found entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    public T findById(int id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getDeclaredFields()[0].getName());
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }
        catch (SQLException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This method uses the select query created to find a certain entity from a table that has a specific name
     * @param name the name of the searched entity
     * @return a T type object corresponding to the found entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    public T findByName(String name) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getDeclaredFields()[1].getName());
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }
        catch (SQLException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This method creates corresponding objects that are supposed to be identical to their entity counterparts from the database
     * @param resultSet the result set returned by an executed query (from other methods within this class)
     * @return a list of T type objects
     * @throws Exception if something goes wrong when communicating with the database or if the reflection techniques are used incorrectly
     */
    private List<T> createObjects(ResultSet resultSet) throws Exception
    {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for (Constructor c : constructors)
        {
            constructor = c;
            if (constructor.getGenericParameterTypes().length == 0)
            {
                break;
            }
        }
        try
        {
            while (resultSet.next())
            {
                constructor.setAccessible(true);
                T instance = null;
                if (!type.equals(Bill.class))
                {
                    instance = (T)constructor.newInstance();
                    for (Field field : type.getDeclaredFields())
                    {
                        String fieldName = field.getName();
                        Object value = null;
                        if (field.getType() == int.class)
                        {
                            value = resultSet.getInt(fieldName);
                        }
                        else if (field.getType() == String.class)
                        {
                            value = resultSet.getString(fieldName);
                        }
                        else if (field.getType() == double.class)
                        {
                            value = resultSet.getDouble(fieldName);
                        }

                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                }
                else
                {
                    Object[] value = new Object[Bill.class.getDeclaredFields().length];
                    int i = 0;
                    for (Field field : type.getDeclaredFields())
                    {
                        String fieldName = field.getName();
                        if (field.getType() == int.class)
                        {
                            value[i] = resultSet.getInt(fieldName);
                        }
                        else if (field.getType() == double.class)
                        {
                            value[i] = resultSet.getDouble(fieldName);
                        }
                        else if (field.getType() == Timestamp.class)
                        {
                            value[i] = resultSet.getTimestamp(fieldName);
                        }
                        else if (field.getType() == String.class)
                        {
                            value[i] = resultSet.getString(fieldName);
                        }
                        ++i;
                    }
                    instance = (T)constructor.newInstance(value);
                }
                list.add(instance);
            }
        }
        catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
               InvocationTargetException | SQLException | IntrospectionException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        return list;
    }

    /**
     * This method creates a MySQL insert query according to the fields parameter
     * @param fields specifies which fields will need to be used for the insertion of a new entity
     * @return a String containing the insert query
     */
    private String createInsertQuery(List<String> fields)
    {
        String result = "INSERT INTO " + type.getSimpleName() + " (";
        for (int i = 0; i < fields.size() - 1; ++i)
        {
            result += (fields.get(i) + ",");
        }
        result += (fields.get(fields.size() - 1) + ") VALUES (");
        for (int i = 0; i < fields.size() - 1; ++i)
        {
            result += "?,";
        }
        result += "?)";

        return result;
    }

    /**
     * This method inserts a new entity of type T into the table corresponding to its class
     * @param t the object of type T corresponding to the inserted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    public void insert(T t) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;

        List<String> fields = new ArrayList<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field f: declaredFields)
        {
            fields.add(f.getName());
        }
        String query = createInsertQuery(fields);

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (int i = 0; i < declaredFields.length; ++i)
            {
                declaredFields[i].setAccessible(true);
                if (declaredFields[i].getType() == int.class)
                {
                    statement.setInt(i + 1, declaredFields[i].getInt(t));
                }
                else if (declaredFields[i].getType() == String.class)
                {
                    statement.setString(i + 1, (String)declaredFields[i].get(t));
                }
                else if (declaredFields[i].getType() == double.class)
                {
                    statement.setDouble(i + 1, declaredFields[i].getDouble(t));
                }
                else if (declaredFields[i].getType() == Timestamp.class)
                {
                    statement.setTimestamp(i + 1, (Timestamp)declaredFields[i].get(t));
                }
            }
            statement.executeUpdate();
        }
        catch (SQLException | IllegalAccessException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This method creates a MySQL update query according to the fields parameter
     * @param fields specifies which fields will need to be used for the update of an existing entity
     * @return a String containing the update query
     */
    private String createUpdateQuery(List<String> fields)
    {
        String result = "UPDATE " + type.getSimpleName() + " SET ";
        for (int i = 1; i < fields.size() - 1; ++i)
        {
            result += (fields.get(i) + " = ?,");
        }
        result += (fields.get(fields.size() - 1) + " = ? WHERE " + fields.get(0) + " = ?");

        return result;
    }

    /**
     * This method updates an existing entity of type T from the table corresponding to its class
     * @param t The class corresponding to the updated entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    public void update(T t) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;

        List<String> fields = new ArrayList<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field f: declaredFields)
        {
            fields.add(f.getName());
        }
        String query = createUpdateQuery(fields);

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (int i = 1; i < declaredFields.length; ++i)
            {
                declaredFields[i].setAccessible(true);
                if (declaredFields[i].getType() == int.class)
                {
                    statement.setInt(i, declaredFields[i].getInt(t));
                }
                else if (declaredFields[i].getType() == String.class)
                {
                    statement.setString(i, (String)declaredFields[i].get(t));
                }
                else if (declaredFields[i].getType() == double.class)
                {
                    statement.setDouble(i, declaredFields[i].getDouble(t));
                }
            }
            declaredFields[0].setAccessible(true);
            statement.setInt(declaredFields.length, declaredFields[0].getInt(t));
            statement.executeUpdate();
        }
        catch (SQLException | IllegalAccessException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * This method creates a MySQL delete query according to the field parameter
     * @param field specifies which field will need to be used for the deletion of a new entity
     * @return a String containing the delete query
     */
    private String createDeleteQuery(String field)
    {
        return "DELETE FROM " + type.getSimpleName() + " WHERE " + field + " = ?";
    }

    /**
     * This method deletes an existing entity of type T from the table corresponding to its class
     * @param t The class corresponding to the deleted entity
     * @throws Exception if something goes wrong when communicating with the database
     */
    public void delete(T t) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;

        Field[] declaredFields = type.getDeclaredFields();
        String query = createDeleteQuery(declaredFields[0].getName());

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            declaredFields[0].setAccessible(true);
            statement.setInt(1, declaredFields[0].getInt(t));
            statement.executeUpdate();
        }
        catch (SQLException | IllegalAccessException e)
        {
            throw new Exception(type.getName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
