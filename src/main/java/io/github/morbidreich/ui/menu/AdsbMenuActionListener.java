package io.github.morbidreich.ui.menu;

import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.ui.MapWindow;
import io.github.morbidreich.utils.SettingsManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record AdsbMenuActionListener(MapPanel mapPanel, MapWindow mapWindow) implements ActionListener {



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JCheckBoxMenuItem) {
            JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
            boolean isSelected = cb.isSelected();
            System.out.println("Action listener for CheckBoxShowAdsb, check state:" + isSelected);


            if (isSelected) {
                SettingsManager.getInstance().set("show.adsb", "1");
                mapWindow.startFeedingTracks(mapPanel,mapWindow.getStatusBar());
//                mapWindow.dataAcquisitionThread.start();
//                System.out.println(mapWindow.dataAcquisitionThread.getState());
//                System.out.println("will start adsb thread");

            }
            else {
                SettingsManager.getInstance().set("show.adsb", "0");
                mapWindow.dataAcquisitionThread.interrupt();
                mapPanel.repaint();
                System.out.println("will stop adsb thread");
            }

        }
        else if (e.getSource() instanceof JRadioButtonMenuItem) {
            JRadioButtonMenuItem rb = (JRadioButtonMenuItem) e.getSource();
            switch (rb.getName()) {
                case "Brightness" -> {
                    SettingsManager.getInstance().set("plot.brightness", rb.getText());
                    mapPanel.repaint();
                    // adjust brightness
                }
                case "History" -> {
                    SettingsManager.getInstance().set("plot.history", rb.getText());
                    mapPanel.repaint();
                    // adjust history
                }
            }
        }
    }
}
