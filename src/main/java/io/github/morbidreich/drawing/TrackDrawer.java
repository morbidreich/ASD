package io.github.morbidreich.drawing;

import io.github.morbidreich.BasePoint;
import io.github.morbidreich.Colors;
import io.github.morbidreich.Coordinates;
import io.github.morbidreich.MapPanel;
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
        g.drawString(String.format("%.0f", track.getBaroAltitude()), x + 20, y + 18);

        if (track.getHeading() != null) {
            // get track lat lon
            double xx = track.getLongitude();
            double yy = track.getLatitude();

            // so far so good
            //System.out.println("lat/lon:" + yy + " " + xx);

            // calculate lat lon of tip of vector
            double heading = track.getHeading();
            double xxx = xx + 0.06 * Math.sin(Math.toRadians(heading));
            double yyy = yy + 0.06 * Math.cos(Math.toRadians(heading));

            //
            System.out.println("H:" + heading + " " + yy + "/" + xx + "tip:" + yyy + "/" + xxx);

            BasePoint tipOfVector = new BasePoint(new Coordinates(yyy, xxx));

            // convert lat lon of vectors tip to x y
            int tipX = mapPanel.convertX(tipOfVector.getEasting());
            int tipY = mapPanel.convertY(tipOfVector.getNorthing(), mapPanel.getHeight());

            // draw line betwen x.y and vector x.y
            g.drawLine(x+3,y+3, tipX+3, tipY+3);
        }
    }
}
