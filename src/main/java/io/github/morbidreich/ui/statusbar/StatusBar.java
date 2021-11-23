package io.github.morbidreich.ui.statusbar;

import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.utils.SettingsManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusBar extends JPanel {

    private final MapPanel mapPanel;

    private final Color backgroundColor = new Color(50, 50,50);
    private final Color textColor = new Color(120, 120,120);
    private final Color connectedColor = new Color(0, 120,0);
    private final Color errorColor = new Color(170, 50,50);

    private final JLabel l1;
    private final JLabel l2;
    public final JLabel l3;

    private final JToggleButton vect1min;
    private final JToggleButton vect3min;
    private final JToggleButton vect5min;
    private final JToggleButton vect8min;

    private final ButtonGroup bg;

    public StatusBar(JFrame parent, MapPanel mapPanel) {

        this.mapPanel = mapPanel;

        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(parent.getWidth(), 32));
        setBackground(backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(backgroundColor);

        l1 = new JLabel("  ADS-B data status: ");
        l1.setForeground(textColor);
        l1.setAlignmentX(LEFT_ALIGNMENT);

        String status = SettingsManager.getInstance().get("show.adsb");

        l2 = new JLabel(status.equals("1") ? " INITIALIZING " : " DISABLED ");
        l2.setForeground(textColor);
        l2.setAlignmentX(LEFT_ALIGNMENT);

        l3 = new JLabel("...");
        l3.setForeground(textColor);
        l3.setAlignmentX(LEFT_ALIGNMENT);

        leftPanel.add(l1);
        leftPanel.add(l2);
        leftPanel.add(l3);

        add(leftPanel);



        vect1min = new JToggleButton("1");
        vect3min = new JToggleButton("3");
        vect5min = new JToggleButton("5");
        vect8min = new JToggleButton("8");

        vect1min.addActionListener(new StatusBarActionListener(mapPanel));
        vect3min.addActionListener(new StatusBarActionListener(mapPanel));
        vect5min.addActionListener(new StatusBarActionListener(mapPanel));
        vect8min.addActionListener(new StatusBarActionListener(mapPanel));

        vect1min.setBackground(backgroundColor);
        vect3min.setBackground(backgroundColor);
        vect5min.setBackground(backgroundColor);
        vect8min.setBackground(backgroundColor);

        vect1min.setForeground(textColor);
        vect3min.setForeground(textColor);
        vect5min.setForeground(textColor);
        vect8min.setForeground(textColor);

        vect1min.setHorizontalAlignment(SwingConstants.RIGHT);

        bg = new ButtonGroup();
        bg.add(vect1min);
        bg.add(vect3min);
        bg.add(vect5min);
        bg.add(vect8min);

        JLabel vectorDescription = new JLabel("Speed vector length: ");
        vectorDescription.setForeground(textColor);
        add(vectorDescription);

        add(vect1min);
        add(vect3min);
        add(vect5min);
        add(vect8min);

        preselectToggleButton();

    }

    private void preselectToggleButton() {
        String opt = SettingsManager.getInstance().get("vector.length");
        switch (opt) {
            case "1" -> vect1min.setSelected(true);
            case "3" -> vect3min.setSelected(true);
            case "5" -> vect5min.setSelected(true);
            case "8" -> vect8min.setSelected(true);
        }
    }


    public void updateStatusOK(int trackedObjects) {
        l2.setForeground(connectedColor);
        l2.setText(" CONNECTED ");
        l3.setText(" tracking " + trackedObjects + " object" + ((trackedObjects > 1) ? "s" : ""));
        //update to default color in case error color altering leaves that at wrong color
        l3.setForeground(textColor);
    }

    public void updateStatusError(String reconnectMessage) {
        l2.setForeground(errorColor);
        l2.setText(" NOT CONNECTED ");
        l3.setText(reconnectMessage);
    }

    public void updateStatusOffline() {
        l2.setForeground(textColor);
        l2.setText(" DISABLED ");
        l3.setText("");
    }

    public void updateStatusErrorChangeColor(Color c) {
        l3.setForeground(c);
    }
}
