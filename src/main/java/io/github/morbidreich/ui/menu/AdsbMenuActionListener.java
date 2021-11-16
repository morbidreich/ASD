package io.github.morbidreich.ui.menu;

import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.ui.MapWindow;
import io.github.morbidreich.utils.SettingsManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record AdsbMenuActionListener(MapPanel mapPanel) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JCheckBoxMenuItem) {
            JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
            SettingsManager.getInstance().set("show.adsb", cb.isSelected() ? "1" : "0");
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
