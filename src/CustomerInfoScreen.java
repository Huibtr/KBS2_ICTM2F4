import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerInfoScreen extends JFrame {
    private String name;
    private int customerId;
    private String deliveryAddressLine1;
    private String cityName;
    private String deliveryPostalCode;
    private String phoneNumber;
    public CustomerInfoScreen(int customerID) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        ResultSet resultSet = dbConnection.getCustomersInfo(customerID);

        while (resultSet.next()){
            customerId = resultSet.getInt("CustomerID");
            name = resultSet.getString("CustomerName");
            deliveryAddressLine1 = resultSet.getString("DeliveryAddressLine1");
            cityName = resultSet.getString("CityName");
            deliveryPostalCode = resultSet.getString("DeliveryPostalCode");
            phoneNumber = resultSet.getString("PhoneNumber");
        }
        setTitle("Nerdy Gadgets");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8,2));

        JLabel jlName = new JLabel("klant naam:");
        add(jlName);

        JLabel jlgetName = new JLabel(name);
        add(jlgetName);

        setVisible(true);

    }
}
