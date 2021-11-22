package io.github.morbidreich.ui;

import java.awt.event.*;

public class MapPanelMouseWheelListener implements MouseWheelListener {

    //general fields
    private MapPanel mapPanel;

    //MouseWheelZoomer fields
    private static final double zoomFactor = .05;


    public MapPanelMouseWheelListener(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double oldScale = mapPanel.getScale();
        //double scale = mapPanel.getScale();

        int rotation = e.getWheelRotation();
        if (rotation > 0) {
            mapPanel.setScale(mapPanel.getScale() / (1 + rotation * zoomFactor));
        } else {
            mapPanel.setScale(mapPanel.getScale() * (1 - rotation * zoomFactor));
        }

        // When zooming, the easting/northing at the cursor position must
        // remain the same, so we have to pan in addition to changing the
        // scale. The maths for easting (same goes for northing):
        //
        // before: x = (easting - oEasting) * oldScale
        // after: x = (easting - newOEasting) * scale
        //
        // (x remains the same, easting remains the same)
        //
        // hence: newOEasting = easting - (easting - oEasting) * oldScale / scale
        // with: easting = x/scale + oEasting
        // hence finally: newOEasting = oEasting + x * (1/oldScale - 1/scale)
        int x = e.getX();
        int y = e.getY();
        int h = mapPanel.getHeight();

        mapPanel.setoEasting(mapPanel.getoEasting() + x * (1 / oldScale - 1 / mapPanel.getScale()));
        mapPanel.setoNorthing(mapPanel.getoNorthing() + (h - y) * (1 / oldScale - 1 / mapPanel.getScale()));

        //System.out.println(rotation + " => " + scale);
        mapPanel.repaint();
    }
}
