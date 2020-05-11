import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxProvincies extends JPanel implements ActionListener {
    private String provincieNaam;
    private RoutingScreen routingScreen;
    public ComboBoxProvincies() {
        String[] provincies = new String[]{"Utrecht", "Limburg", "Noord-Brabant", "Friesland", "Noord-Holland", "Zuid-Holland", "Drenthe",
                "Gelderland", "Flevoland", "Groningen", "Zeeland", "Overijssel"};
        JComboBox provincielijst = new JComboBox(provincies);
        provincielijst.setSelectedIndex(11);
        provincielijst.addActionListener(this);
        add(provincielijst);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        provincieNaam = (String)cb.getSelectedItem();
        if (provincieNaam == null) {
            provincieNaam = "Utrecht";
        }
        System.out.println(provincieNaam);







    }

    public String getProvincieNaam() {
        return provincieNaam;
    }
}
