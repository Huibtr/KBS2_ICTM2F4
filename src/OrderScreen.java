import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class OrderScreen extends JFrame{
    private ArrayList<Order> orders;

    public OrderScreen() {
        getOrder();
        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel();

        Object[] columnsName = new Object[] {
                "KlantID", "BestellingID", "ProductID", "Voorraad"
        };

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[4];

        for(int i = 0; i < orders.size(); i++){

            rowData[0] = orders.get(i).getCustomerID();
            rowData[1] = orders.get(i).getOrderID();
            rowData[2] = orders.get(i).getStockItemID();
            rowData[3] = orders.get(i).getQuantity();
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
                    JOptionPane.showMessageDialog(null, selectrow);
                }
            }
        });

    }

    public void getOrder(){

        orders = new ArrayList<>();
        Order order;
        try {

            DBConnection dbConnection = new DBConnection();
            ResultSet rs = dbConnection.getOrderlines();

            while(rs.next()){

                order = new Order(
                        rs.getInt("CustomerID"),
                        rs.getInt("OrderID"),
                        rs.getInt("StockItemID"),
                        rs.getInt("Quantity")
                );

                orders.add(order);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}
