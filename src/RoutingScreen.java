import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutingScreen extends JFrame implements ActionListener {
    private JLabel route;
    private JTable gegevens;

    public RoutingScreen(){
        setTitle("Routingscreen");
        setLayout(new FlowLayout());
        setSize(500,500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        route = new JLabel("Hier komt de route");
        add(route);
        gegevens = new JTable(3,5);
        add(gegevens);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

}
