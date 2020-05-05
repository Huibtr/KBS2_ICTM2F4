import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class CustomerScreen extends JFrame {
    private  ArrayList<Customer> customers;
    public ResultSet result;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private JButton jbGoBack;

    public CustomerScreen() {
        getCustomer();
        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel();

        Object[] columnsName = new Object[] {
                "CustomerID", "CustomerName", "Adress", "Stadsnaam", "Postcode", "Telefoonnummer"
        };

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[6];

        for(int i = 0; i < customers.size(); i++){

            rowData[0] = customers.get(i).getCustomerID();
            rowData[1] = customers.get(i).getCustomerName();
            rowData[2] = customers.get(i).getDeliveryAddressLine1();
            rowData[3] = customers.get(i).getCityName();
            rowData[4] = customers.get(i).getDeliveryPostalCode();
            rowData[5] = customers.get(i).getPhoneNumber();
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

    public void getCustomer(){

        customers = new ArrayList<>();
        Customer customer;
        try {

            DBConnection dbConnection = new DBConnection();
            ResultSet rs = dbConnection.getCustomers();

            while(rs.next()){

                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("DeliveryAddressLine1"),
                        rs.getString("CityName"),
                        rs.getString("DeliveryPostalCode"),
                        rs.getString("PhoneNumber")
                );

                customers.add(customer);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }



}


