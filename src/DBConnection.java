import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    static Connection connection = null;
    static String databaseName = "";
    static String url = "jdbc:mysql://localhost:3306/student";
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
            // insert
            //query.executeUpdate("insert into student values ('S11224', 'pim', 'hart', 'test', 'test'); ");

            ResultSet result = query.executeQuery("SELECT * FROM student;");
            System.out.println("WWI");
            while (result.next()){
                String table = result.getString("voornaam");
                System.out.println(table);
            }


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
