import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoutingScreen extends JFrame implements ActionListener {
    private Coordination coordination;
    private JTable gegevens;
    private JButton jbBack;
    private ArrayList<Route> routelijst;
    private String[] provincies;
    private JComboBox provincielijst;

    public RoutingScreen(){
        provincies = new String[] {"Zeeland", "Limburg", "Noord-Brabant", "Friesland", "Noord-Holland", "Zuid-Holland", "Drenthe",
                                    "Gelderland", "Flevoland", "Groningen", "Utrecht", "Overijssel"};
        provincielijst = new JComboBox(provincies);
        provincielijst.setSelectedIndex(11);
        add(provincielijst);
        setLayout(new FlowLayout());
        setSize(530, 500);
        RoutingPanel panel = new RoutingPanel(coordination);
        add(panel);
        jbBack = new JButton("terug");
        jbBack.addActionListener(this);
        add(jbBack);



        getRouteInfo();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[] {
                "Naam", "Adress", "Stad", "OrderID"
        };
        model.setColumnIdentifiers(columnsName);
        Object[] rowData = new Object[4];
        for(int i = 0; i < routelijst.size(); i++){
            rowData[0] = routelijst.get(i).getName();
            rowData[1] = routelijst.get(i).getAdress();
            rowData[2] = routelijst.get(i).getStad();
            rowData[3] = routelijst.get(i).getOrderId();
            model.addRow(rowData);
        }
        table.setModel(model);
        //add the table to the frame
        this.add(new JScrollPane(table));
        this.setTitle("NerdyGadgets - Klantgegevens");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        ListSelectionModel modelclick = table.getSelectionModel();
    }

    public void getRouteInfo(){
        routelijst = new ArrayList<>();
        Route route;
        try {
            DBConnection dbConnection = new DBConnection();
            ResultSet rs = dbConnection.getRouteInfo("Utrecht");
            while(rs.next()){
                route = new Route(
                        rs.getString("CustomerName"),
                        rs.getString("DeliveryAddressLine1"),
                        rs.getString("CityName"),
                        rs.getInt("OrderID")
                );
                routelijst.add(route);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            dispose();
            HomeScreen homeScreen = new HomeScreen();
        }

    }

}
