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
}
