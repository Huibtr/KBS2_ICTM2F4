import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class QuantityScreen extends JFrame {
    private ArrayList<Quantity> quantities;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private JButton jbGoBack;

    public QuantityScreen() {
            getQuantity();
            JTable table = new JTable();

            DefaultTableModel model = new DefaultTableModel();

            Object[] columnsName = new Object[] {
                    "Product naam", "Product nummer", "aantal"
            };

            model.setColumnIdentifiers(columnsName);

            Object[] rowData = new Object[3];

            for(int i = 0; i < quantities.size(); i++){

                rowData[0] = quantities.get(i).getStockItemName();
                rowData[1] = quantities.get(i).getStockItemID();
                rowData[2] = quantities.get(i).getQuantityOnHand();
                model.addRow(rowData);
            }

            table.setModel(model);

            //add the table to the frame
            this.add(new JScrollPane(table));

            this.setTitle("Table Example");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);

            ListSelectionModel modelclick = table.getSelectionModel();
            modelclick.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    if (!modelclick.isSelectionEmpty()){
                        int selectrow = modelclick.getMinSelectionIndex();
                        try {
                            QuantityInfoScreen QuantityInfoScreen = new QuantityInfoScreen();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }

    public void getQuantity(){
        quantities = new ArrayList<>();
        Quantity quantity;
        try {
            DBConnection dbConnection = new DBConnection();
            ResultSet rs = dbConnection.getQuantity();

            while(rs.next()){
                quantity = new Quantity(
                        rs.getString("StockItemName"),
                        rs.getInt("StockItemID"),
                        rs.getInt("QuantityOnHand")
                );
                quantities.add(quantity);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
