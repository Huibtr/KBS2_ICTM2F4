import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RouteBepalingScherm extends JFrame {
    private JLabel route;
    private JTable tabel;
    public RouteBepalingScherm(){
        setTitle("Routebepaling");
        setSize(500,500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        route = new JLabel("Hier komt de route");
        add(route);
        tabel = new JTable(3,5);
        add(tabel);
        setVisible(true);
    }
}
