import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    static String databaseName = "wideworldimporters";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String password = "";

    public static void main(String[] args) {
        DBConnection connection = new DBConnection();
        connection.createConnection();
    }

    public void createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("connected to database");

            Statement query = connection.createStatement();

            // haalt nu alle orderlines uit de wwi database op
            ResultSet result = query.executeQuery("SELECT * FROM orderlines;");
            while (result.next()){
                String table = result.getString("OrderID");
                System.out.println(table);
            }

            // insert
            //query.executeUpdate("insert into student values ('S11224', 'pim', 'hart', 'test', 'test'); ");
        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null, ex);
            ex.printStackTrace();
        }

    }
}
