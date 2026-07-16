package sirius.views.components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int radius;
    private boolean drawShadow;

    public RoundedPanel(int radius) {
        this(radius, false);
    }

    public RoundedPanel(int radius, boolean drawShadow) {
        this.radius = radius;
        this.drawShadow = drawShadow;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (drawShadow) {
            g2.setColor(new Color(0, 0, 0, 40));
            g2.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, radius, radius);
            
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 8, radius, radius);
        } else {
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
    }
}