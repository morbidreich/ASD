package io.github.morbidreich.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusBar extends JPanel {

    private final Color backgroundColor = new Color(50, 50,50);
    private final Color textColor = new Color(120, 120,120);
    private final Color connectedColor = new Color(0, 120,0);
    private final Color errorColor = new Color(170, 50,50);

    private final JLabel l1;
    private final JLabel l2;
    private final JLabel l3;

    public StatusBar(JFrame parent) {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(parent.getWidth(), 32));
        setBackground(backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        l1 = new JLabel("  Connection status: ");
        l1.setForeground(textColor);
        l1.setHorizontalAlignment(SwingConstants.LEFT);

        l2 = new JLabel(" INITIALIZING ");
        l2.setForeground(textColor);
        l2.setHorizontalAlignment(SwingConstants.LEFT);

        l3 = new JLabel("...");
        l3.setForeground(textColor);
        l3.setHorizontalAlignment(SwingConstants.LEFT);

        add(l1);
        add(l2);
        add(l3);
    }

    public void updateStatusOK(int trackedObjects) {
        l2.setForeground(connectedColor);
        l2.setText(" CONNECTED ");
        l3.setText("tracking " + trackedObjects + " objects");
    }

    public void updateStatusError(String reconnectMessage) {
        l2.setForeground(errorColor);
        l2.setText(" NOT CONNECTED ");
        l3.setText(reconnectMessage);

    }
}
