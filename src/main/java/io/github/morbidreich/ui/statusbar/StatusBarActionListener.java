package io.github.morbidreich.ui.statusbar;

import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.utils.SettingsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBarActionListener implements ActionListener {
    private final MapPanel mapPanel;
    public StatusBarActionListener(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JToggleButton t = (JToggleButton) e.getSource();

        SettingsManager.getInstance().set("vector.length", t.getText());
        mapPanel.repaint();

    }
}
