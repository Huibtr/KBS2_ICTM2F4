import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderInfoScreen extends JFrame {
    private int customerID;
    private int orderID;
    private int stockItemID;
    private int quantity;

    public OrderInfoScreen(int orderID) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        ResultSet resultSet = dbConnection.getOrdersInfo(orderID);

        while (resultSet.next()){
            customerID = resultSet.getInt("CustomerID");
            orderID = resultSet.getInt("OrderID");
            stockItemID = resultSet.getInt("StockItemID");
            quantity = resultSet.getInt("Quantity");
        }
        setTitle("NerdyGadgets - Bestelling " + orderID);
        setSize(500, 300);
        setLayout(new GridLayout(8,2));

        setVisible(true);
    }
}
