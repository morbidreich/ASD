package io.github.morbidreich.ui.menu;

import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.ui.MapWindow;
import io.github.morbidreich.utils.SettingsManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class intercepts and handles events raised by ADSB data menu
 */
public record AdsbMenuActionListener(MapPanel mapPanel, MapWindow mapWindow) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        // start/stop adsb data acquisition thread
        if (e.getSource() instanceof JCheckBoxMenuItem cb) {
            if (cb.isSelected()) {
                //store user selection in settings.properties file
                SettingsManager.getInstance().set("show.adsb", "1");
                //call method that will start acquisition thread
                mapWindow.startFeedingTracks(mapPanel, mapWindow.getStatusBar());
            } else {
                //store user selection in settings.properties file
                SettingsManager.getInstance().set("show.adsb", "0");
                //call method that will stop acquisition thread
                mapWindow.dataAcquisitionThread.interrupt();
            }

        } else if (e.getSource() instanceof JRadioButtonMenuItem rb) {
            switch (rb.getName()) {
                // adjust track's brightness
                case "Brightness" -> {
                    SettingsManager.getInstance().set("plot.brightness", rb.getText());
                    mapPanel.repaint();
                }
                // adjust plot history length
                case "History" -> {
                    SettingsManager.getInstance().set("plot.history", rb.getText());
                    mapPanel.repaint();
                }
            }
        }
    }
}
