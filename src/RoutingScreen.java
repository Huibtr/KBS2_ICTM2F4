import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutingScreen extends JFrame implements ActionListener {
    private Coordination coordination;
    private JTable gegevens;
    private JButton jbAdd;

    public RoutingScreen(){
        setLayout(new FlowLayout());
        setSize(530, 500);
        RoutingPanel panel = new RoutingPanel(coordination);
        add(panel);
        jbAdd = new JButton("toevoegen");
        jbAdd.addActionListener(this);
        add(jbAdd);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbAdd) {
        }
        repaint();
    }

}
