import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class QuantityScreen extends JFrame implements ActionListener {
    public ResultSet result;
    private final JButton jbload;
    private final JTable jtstock;
    private final DefaultTableModel tableModel = new DefaultTableModel();

    public QuantityScreen() {

        setTitle("Nerdy Gadgets - Voorraad");
        setSize(800, 500);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jtstock = new JTable(tableModel);
        add(new JScrollPane(jtstock), BorderLayout.CENTER);


        jbload = new JButton("Haal voorraad op");
        jbload.addActionListener(this);
        add(jbload, BorderLayout.PAGE_START);

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
            jbload.setText("Refresh voorraad");
        }
    }

    private void loadData() throws SQLException {

        jbload.setEnabled(false);

        DBConnection dbConnection = new DBConnection();
        ResultSet rs = dbConnection.getQuantity();
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
