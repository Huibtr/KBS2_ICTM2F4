import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;


public class CustomerScreen extends JFrame implements ActionListener{
    public ResultSet result;
    private final JButton jbload;
    private final JTable jtcustomer;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private JButton jbGoBack;

    public CustomerScreen() {

        setTitle("Nerdy Gadgets - Klantgegevens");
        setSize(800, 500);
        setLayout(new GridLayout(3,1));
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jbGoBack = new JButton("< terug");
        jbGoBack.addActionListener(this);
        add(jbGoBack);

        jbload = new JButton("Haal klantgegevens op");
        jbload.addActionListener(this);
        add(jbload, BorderLayout.PAGE_START);

        jtcustomer = new JTable(tableModel);
        add(new JScrollPane(jtcustomer), BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbload){
            System.out.println("refresh data");
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    loadData();
                    return null;
                }
            }.execute();
            jbload.setText("Refresh klantgegevens");
        } else if(e.getSource() == jbGoBack) {
            dispose();
            DataScreen dataScreen = new DataScreen();
        }
    }

    private void loadData() throws SQLException {

        jbload.setEnabled(false);

        DBConnection dbConnection = new DBConnection();
        ResultSet rs = dbConnection.getCustomers();
        ResultSetMetaData metaData = rs.getMetaData();

        // naam van de kolommen
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        // gegevens uit de data base
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                vector.add(rs.getObject(i));
            }
            data.add(vector);
        }

        tableModel.setDataVector(data, columnNames);
        jbload.setEnabled(true);

    }


}
