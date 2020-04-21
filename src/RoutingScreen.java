import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutingScreen extends JFrame implements ActionListener {
    private JTable gegevens;
    private RoutingPanel route;
    private JButton JBadd;

    public RoutingScreen(){
        setTitle("Routingscreen");
        setLayout(new FlowLayout());
        setSize(500,500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        route = new RoutingPanel();
        add(route);
        gegevens = new JTable(3,5);
        add(gegevens);
        JBadd = new JButton("toevoegen");
        JBadd.addActionListener(this);
        add(JBadd);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == JBadd){
            System.out.println("gedrukt");
        }
        repaint();
    }

}
