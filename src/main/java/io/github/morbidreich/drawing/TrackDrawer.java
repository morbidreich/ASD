package io.github.morbidreich.drawing;

import io.github.morbidreich.*;
import io.github.morbidreich.surveilance.Track;

import java.awt.*;

public class TrackDrawer {

    public static void drawTrack(Track track, Graphics2D g, MapPanel mapPanel) {
        g.setColor(Colors.TRACK_COLOR);

        int x = mapPanel.convertX(track.getEasting());
        int y = mapPanel.convertY(track.getNorthing(), mapPanel.getHeight());

        g.drawOval(x,y,6,6);

        //get width of callsign string to position velocity
        int width = g.getFontMetrics().stringWidth(track.getCallsing());

        g.drawString(track.getCallsing(), x + 20, y + 2);
        g.drawString(String.format("%.0f", track.getVelocity()), x + width + 20, y + 2);

        String baroAltitude = String.format("%.0f", track.getBaroAltitude());
        g.drawString(baroAltitude, x + 20, y + 18);

        // if absolute vertical rate is present and is greater than 1.6 m/s - more than 300feets per minute
        // then draw altitude trend arrow and vertical rate (1m/s ~ 200feets per minute)

        if (track.getVerticalRate() != null && Math.abs(track.getVerticalRate()) > 1.6) {

            int widthOfAltitudeString = g.getFontMetrics().stringWidth(baroAltitude);
            g.drawLine(x + 20 + widthOfAltitudeString + 5, y+9, x + 20 + widthOfAltitudeString + 5, y+17);

            if (track.getVerticalRate()>0) {
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y+9, x + 20 + widthOfAltitudeString + 7, y+12);
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y+9, x + 20 + widthOfAltitudeString + 3, y+12);
            }
            else {
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y+17, x + 20 + widthOfAltitudeString + 7, y+14);
                g.drawLine(x + 20 + widthOfAltitudeString + 5, y+17, x + 20 + widthOfAltitudeString + 3, y+14);
            }

        }

        if (track.getHeading() != null) {
            // get track lat lon
            double xx = track.getLongitude();
            double yy = track.getLatitude();

            // calculate lat lon of tip of vector
            double heading = track.getHeading() - ApplicationSettings.MAGNETIC_VARIATION;
            double xxx = xx + 0.06 * Math.sin(Math.toRadians(heading));
            double yyy = yy + 0.06 * Math.cos(Math.toRadians(heading));

            BasePoint tipOfVector = new BasePoint(new Coordinates(yyy, xxx));

            // convert lat lon of vectors tip to x y
            int tipX = mapPanel.convertX(tipOfVector.getEasting());
            int tipY = mapPanel.convertY(tipOfVector.getNorthing(), mapPanel.getHeight());

            // draw line betwen x.y and vector x.y
            g.drawLine(x+3,y+3, tipX+3, tipY+3);
        }
    }
}
