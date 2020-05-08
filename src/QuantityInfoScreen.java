import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantityInfoScreen extends JFrame{
    private String StockItemName;
    private int StockItemID;
    private int QuantityOnHand;

    public QuantityInfoScreen () throws SQLException {
        DBConnection dbConnection = new DBConnection();
        ResultSet resultSet = dbConnection.getQuantityInfo();

        while (resultSet.next()){
            StockItemName = resultSet.getString("StockItemName");
            StockItemID = resultSet.getInt("StockItemID");
            QuantityOnHand = resultSet.getInt("QuantityOnHand");
        }
        setTitle("NerdyGadgets - Voorraad");
        setSize(500, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel JLStockItemName = new JLabel ("Productnaam: ");
        add (JLStockItemName);

        JLabel JLgetStockItemName = new JLabel (StockItemName);
        add (JLgetStockItemName);

        JLabel JLStockItemID = new JLabel ("Productnummer: ");
        add (JLStockItemID);

        JLabel JLgetStockItemID = new JLabel ("" + StockItemID);
        add (JLgetStockItemID);

        JLabel JLQuantityOnHand = new JLabel ("Voorraad: ");
        add (JLQuantityOnHand);

        JLabel JLgetQuantityOnHand = new JLabel ("" + QuantityOnHand);
        add (JLgetQuantityOnHand);

        setVisible(true);
    }

}
