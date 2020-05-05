import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutingScreen extends JFrame implements ActionListener {
    private Coordination coordination;
    private JTable gegevens;
    private JButton jbBack;

    public RoutingScreen(){
        setLayout(new FlowLayout());
        setSize(530, 500);
        RoutingPanel panel = new RoutingPanel(coordination);
        add(panel);
        jbBack = new JButton("terug");
        jbBack.addActionListener(this);
        add(jbBack);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            dispose();
            HomeScreen homeScreen = new HomeScreen();
        }

    }

}
