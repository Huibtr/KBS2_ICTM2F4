import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private String databaseName = "wideworldimporters";
    private String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private String username = "root";
    private String password = "";
    private ResultSet result;

    public ResultSet getCustomers(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement query = connection.createStatement();

             result = query.executeQuery("select CustomerID, CustomerName, DeliveryAddressLine1, CityName, DeliveryPostalCode, PhoneNumber from customers inner join cities where customers.DeliveryCityID =  cities.CityID;");

        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet getCustomersInfo(int customerID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement query = connection.createStatement();

            result = query.executeQuery("select CustomerID, CustomerName, DeliveryAddressLine1, CityName, DeliveryPostalCode, PhoneNumber from customers inner join cities where customers.DeliveryCityID =  cities.CityID and customers.CustomerID ="+ customerID +";");

        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet getOrderlines(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement query = connection.createStatement();

            result = query.executeQuery("SELECT orders.CustomerID, orders.OrderID, orderlines.StockitemID, orderlines.Quantity FROM orderlines LEFT JOIN orders ON orders.OrderID=orderlines.OrderID Where CustomerID < 4;");

        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet getQuantity(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement query = connection.createStatement();

            result = query.executeQuery("SELECT stockitems.StockItemName, stockitems.StockItemID, stockitemholdings.QuantityOnHand FROM stockitems JOIN stockitemholdings ON stockitems.StockItemID = stockitemholdings.StockItemID;");

        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet getCoordinates(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement query = connection.createStatement();

            result = query.executeQuery("select X, Y from address_coordinate;");

        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
            ex.printStackTrace();
        }
        return result;
    }
}

