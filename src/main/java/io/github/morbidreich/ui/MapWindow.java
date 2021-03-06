package io.github.morbidreich.ui;

import io.github.morbidreich.airspaceElements.Airspace;
import io.github.morbidreich.surveilance.DataAcquisition;
import io.github.morbidreich.ui.menu.Menu;
import io.github.morbidreich.ui.statusbar.StatusBar;
import io.github.morbidreich.utils.SettingsManager;

import java.awt.*;

import javax.swing.*;

/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

public class MapWindow extends JFrame {
    private final StatusBar statusBar;

    public Thread dataAcquisitionThread;

    /**
     * Creates a new window..
     */
    public MapWindow(Airspace airspace) {
        String version = SettingsManager.getInstance().get("version");
        super.setTitle("Airspace Display " + version + " - NOT FOR OPERATIONAL USE!");

        MapPanel map = new MapPanel();
        map.addAirspace(airspace);
        map.setDefaultElementsVisibility();



        Menu menu = new Menu(map, airspace, this);
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
        if (SettingsManager.getInstance().get("show.adsb").equals("1"))
            startFeedingTracks(map, statusBar);

    }

    public void startFeedingTracks(MapPanel mapPanel, StatusBar statusBar) {

        DataAcquisition dataAcquisition = new DataAcquisition(mapPanel, statusBar);

        dataAcquisitionThread = new Thread(dataAcquisition);
        dataAcquisitionThread.setDaemon(true);
        dataAcquisitionThread.start();
    }

    public StatusBar getStatusBar() {
        return this.statusBar;
    }
}


