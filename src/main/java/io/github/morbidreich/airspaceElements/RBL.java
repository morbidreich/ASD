package io.github.morbidreich.airspaceElements;

import io.github.morbidreich.surveilance.Track;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.utils.Calculations;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This class represents RBL object - Range Bearing line. It is used to measure distance
 * between two clicked points on MapPanel. Clicked point can be empty space on MapPanel
 * or Track Label. If user clicks empty space, then corresponding end of RBL will stick to that static
 * position defined by geographical coordinates. If user clicks Track Label, then that end of
 * RBL will stick to Track. In that case measurement will be automatically updated with each
 * refresh of surveilace data (every AppSettings.RADAR_REFRESH_RATE) to take
 * into account Tracks changing position. RBL may connect two static positions, static position
 * and Track, or two Tracks.
 */
public class RBL {
    private BasePoint startPoint;
    private BasePoint endPoint;
    private Track startTrack;
    private Track endTrack;
    private int labelX, labelY;
    private final int labelWidth = 65;
    private final int labelHeight = 35;

    public RBL() {}

    public BasePoint getStartPoint() {
        if (startPoint != null)
            return startPoint;
        else if (startTrack != null){
            Coordinates c = new Coordinates(startTrack.getLatitude(), startTrack.getLongitude());
            return new BasePoint(c);
        }
        else
            return null;
    }

    public void setStartPoint(BasePoint startPoint) {
        this.startPoint = startPoint;
    }

    public BasePoint getEndPoint() {
        if (this.endPoint != null)
                return endPoint;
        else if (endTrack != null){
            Coordinates c = new Coordinates(endTrack.getLatitude(), endTrack.getLongitude());
           return new BasePoint(c);
        }
        else
            return null;
    }

    public void setEndPoint(BasePoint endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartTrack(Track startTrack) {
        this.startPoint = null;
        this.startTrack = startTrack;
    }

    public Track getStartTrack() {
        return startTrack;
    }

    public void setEndTrack(Track endTrack) {
        this.endPoint = null;
        this.endTrack = endTrack;
    }

    public Track getEndTrack() {
        return endTrack;
    }

    public void drawLabel(int x1, int y1, int x2, int y2, double scale, Graphics2D g) {

        //calculate distance
        double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / scale;
        dist *= 0.5399568; //convert km to nautical miles
        String sDist = String.format("%.1f", dist);

        double angle = Calculations.getAngleGivenTwoPoints(x1, y1, x2, y2);
        //apply magnetic variation, around +5deg
        angle -= AppSettings.MAGNETIC_VARIATION;
        int iAngle = (int)(angle > 0 ? angle : angle + 360);

        // display to the right or left of cursor to avoid overlapping

        if (x1 < x2) {
            // label displayed left of cursor
            g.drawString("R: " + sDist + "NM", x1 - 65, y1);
            g.drawString("B: " + iAngle + "\u00B0", x1 - 65, y1 + 15);
            labelX = x1-70;
            labelY = y1-15;
            g.drawRect(labelX, labelY, labelWidth, labelHeight);
        }
        else {
            //label displayed right of cursor
            g.drawString("R: " + sDist + "NM", x1 + 15, y1);
            g.drawString("B: " + iAngle + "\u00B0", x1 + 15, y1 + 15);
            labelX = x1+10;
            labelY = y1-15;
            g.drawRect(labelX, labelY, labelWidth, labelHeight);
        }
    }

    public boolean labelClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (
                x >= labelX && x <= labelX + labelWidth &&
                y >= labelY && y <= labelY + labelHeight)
            return true;
        else
            return false;
    }
}
