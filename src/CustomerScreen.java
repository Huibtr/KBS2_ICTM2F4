import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerScreen extends JFrame implements ActionListener {
    private ResultSet result;
    private JTable jtCustomer;

    public CustomerScreen() throws SQLException {

        setTitle("Nerdy Gadgets - Klant gegevens");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));


        DBConnection dbConnection = new DBConnection();
        result = dbConnection.getCustomers();
        while (result.next()){
            String customerID = result.getString("CustomerID");
            String customerName = result.getString("CustomerName");
            String deliveryAddress = result.getString("DeliveryAddressLine1");
        }

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
