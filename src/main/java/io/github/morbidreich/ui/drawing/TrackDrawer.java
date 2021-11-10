package io.github.morbidreich.ui.drawing;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;
import io.github.morbidreich.surveilance.Track;
import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.utils.Colors;

import java.awt.*;

public class TrackDrawer {

    public static void drawTrack(Track track, Graphics2D g, MapPanel mapPanel) {
        // convert geo coordinates to screen coords
        int x = mapPanel.convertX(track.getEasting());
        int y = mapPanel.convertY(track.getNorthing(), mapPanel.getHeight());

        setTrackColor(track, g);
        drawEcho(g, x, y);
        drawLabel(track, g, x, y);
        drawAltitudeTrendArrow(track, g, x, y, track.getBaroAltitude());
        drawVelocityVector(track, g, mapPanel, x, y);
    }

    private static void drawLabel(Track track, Graphics2D g, int x, int y) {
        //get width of callsign string to position velocity string
        int width = g.getFontMetrics().stringWidth(track.getCallsing());

        //draw callsign
        g.drawString(track.getCallsing(), x + 20, y + 2);
        g.drawString(String.format("%.0f", track.getVelocity()), x + width + 20, y + 2);

        //draw altitude
        String baroAltitude = String.format("%.0f", track.getBaroAltitude());
        g.drawString(baroAltitude, x + 20, y + 18);
    }

    private static void drawEcho(Graphics2D g, int x, int y) {
        // draw echo
        g.drawOval(x, y, 6, 6);
    }

    private static void drawAltitudeTrendArrow(Track track, Graphics2D g, int x, int y, Double baroAltitude) {
        // if absolute vertical rate is present and is greater than 1.6 m/s - more than 300feets per minute
        // then draw altitude trend arrow and vertical rate (1m/s ~ 200feets per minute)
        if (track.getVerticalRate() != null && Math.abs(track.getVerticalRate()) > 1.6) {

            // get current alt to check it's size
            String baroAltStr = String.format("%.0f", baroAltitude);
            //then use that size to position trend arrow
            int widthOfAltitudeString = g.getFontMetrics().stringWidth(baroAltStr);
            //draw vertical line
            g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 9, x + 20 + widthOfAltitudeString + 5, y + 17);
            // draw tip of an arrow pointing up or down, depending on vertical speed value
            if (track.getVerticalRate() > 0) {
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 9, x + 20 + widthOfAltitudeString + 7, y + 12);
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 9, x + 20 + widthOfAltitudeString + 3, y + 12);
            } else {
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 17, x + 20 + widthOfAltitudeString + 7, y + 14);
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 17, x + 20 + widthOfAltitudeString + 3, y + 14);
            }

        }
    }

    private static void drawVelocityVector(Track track, Graphics2D g, MapPanel mapPanel, int x, int y) {
        //if heading present then draw velocity vector
        if (track.getHeading() != null) {
            // get track lat lon
            double xx = track.getLongitude();
            double yy = track.getLatitude();

            //api returns true heading, convert to magnetic
            double heading = track.getHeading() - AppSettings.MAGNETIC_VARIATION;
            // calculate lat lon of tip of vector
            double xxx = xx + 0.06 * Math.sin(Math.toRadians(heading));
            double yyy = yy + 0.06 * Math.cos(Math.toRadians(heading));

            BasePoint tipOfVector = new BasePoint(new Coordinates(yyy, xxx));

            // convert lat lon of vectors tip to x y
            int tipX = mapPanel.convertX(tipOfVector.getEasting());
            int tipY = mapPanel.convertY(tipOfVector.getNorthing(), mapPanel.getHeight());

            // draw line betwen x.y and tip of vector x.y
            g.drawLine(x + 3, y + 3, tipX + 3, tipY + 3);
        }
    }

    private static void setTrackColor(Track track, Graphics2D g) {
        // if spi (squawk ident) detected then draw track in blue
        if (track.getSpi() != null && track.getSpi() == true)
            g.setColor(new Color(0, 190, 255));
        else // use standard green color
            g.setColor(Colors.TRACK_COLOR);
    }
}
