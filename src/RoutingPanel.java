import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class RoutingPanel extends JPanel {
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        setBackground(Color.black);
        g.fillOval(100,100,100,100);
    }
}

