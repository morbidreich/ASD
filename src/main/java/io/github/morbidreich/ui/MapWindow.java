package io.github.morbidreich.ui;

import io.github.morbidreich.airspaceElements.Airspace;
import io.github.morbidreich.surveilance.DataAcquisition;
import io.github.morbidreich.ui.statusbar.StatusBar;
import io.github.morbidreich.utils.SettingsManager;

import java.awt.*;

import javax.swing.*;

/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

public class MapWindow extends JFrame {
    private final MapPanel map;
    private StatusBar statusBar;

    Thread dataAcquisitionThread;
    Thread statusBarThread;

    /**
     * Creates a new window..
     */
    public MapWindow(Airspace airspace) {
        //super("Airspace Display - NOT FOR OPERATIONAL USE!");
        String version = SettingsManager.getInstance().get("version");
        super.setTitle("Airspace Display " + version + " - NOT FOR OPERATIONAL USE!");

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

        //add status bar
        statusBar = new StatusBar(this, map);
        add(statusBar, BorderLayout.SOUTH);

        //start api thread
        startFeedingTracks(map, statusBar);

    }

    private void startFeedingTracks(MapPanel mapPanel, StatusBar statusBar) {
        DataAcquisition dataAcquisition = new DataAcquisition(mapPanel, statusBar);

        dataAcquisitionThread = new Thread(dataAcquisition);
        dataAcquisitionThread.setDaemon(true);
        dataAcquisitionThread.start();
    }
}


