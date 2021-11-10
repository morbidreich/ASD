package io.github.morbidreich.ui;

import io.github.morbidreich.airspaceElements.Airspace;
import io.github.morbidreich.surveilance.DataAcquisition;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

public class MapWindow extends JFrame {
    private final MapPanel map;

    /**
     * Creates a new window..
     */
    public MapWindow(Airspace airspace) {

        super("Airspace Display - NOT FOR OPERATIONAL USE!");

        map = new MapPanel();
        map.addAirspace(airspace);
        map.setDefaultElementsVisibility();

        Menu menu = new Menu(map, airspace);
        setJMenuBar(menu.getMenuBar());

        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/icon.png");
        setIconImage(icon);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addStatusBar();

        startFeedingTracks();
    }

    private void addStatusBar() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        this.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 32));
        statusPanel.setBackground(new Color(50,50,50));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        JLabel statusLabel = new JLabel("  Connection status: ");
        statusLabel.setForeground(new Color(120, 120, 120));
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel statusLabel3 = new JLabel(" ONLINE ");
        statusLabel3.setForeground(new Color(0, 120, 0));
        statusLabel3.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel statusLabel2 = new JLabel("tracking 94 objects");
        statusLabel2.setForeground(new Color(120, 120, 120));
        statusLabel2.setHorizontalAlignment(SwingConstants.LEFT);

        statusPanel.add(statusLabel);
        statusPanel.add(statusLabel3);
        statusPanel.add(statusLabel2);

    }

    private void startFeedingTracks() {
        DataAcquisition dataAcquisition = new DataAcquisition(map);
        Thread t = new Thread(dataAcquisition);
        t.setDaemon(true);
        t.start();
    }
}


