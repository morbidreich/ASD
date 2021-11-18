package io.github.morbidreich.ui.drawing;

import io.github.morbidreich.App;
import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;
import io.github.morbidreich.surveilance.Track;
import io.github.morbidreich.surveilance.TrackPosition;
import io.github.morbidreich.ui.MapPanel;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.utils.Calculations;
import io.github.morbidreich.utils.Colors;
import io.github.morbidreich.utils.SettingsManager;

import java.awt.*;
import java.util.List;

public class TrackDrawer {

    public synchronized static void drawTrack(Track track, Graphics2D g, MapPanel mapPanel) {

        //only draw tracks that are inside SHOW_BOX as defined in AppSettings
        if ((track.getLatitude() > AppSettings.SHOW_MIN_LAT || track.getLatitude() < AppSettings.SHOW_MAX_LAT) &&
                (track.getLongitude() > AppSettings.SHOW_MIN_LON || track.getLongitude() < AppSettings.SHOW_MAX_LON)) {

            // convert geographic coordinates to screen coords
            int x = mapPanel.convertX(track.getEasting());
            int y = mapPanel.convertY(track.getNorthing(), mapPanel.getHeight());

            setTrackColor(track, g);
            drawEcho(g, x, y);

            // OLD WAYS HERE
            //drawLabel(track, g, x, y);
            drawLabel(track, g, x, y);

            //drawAltitudeTrendArrow(track, g, x, y, track.getBaroAltitude());
            drawVelocityVector(track, g, mapPanel, x, y);
            drawHistoricPlots(track, g, mapPanel);
        }
    }

    private static void drawLabel(Track track, Graphics2D g, int x, int y) {
        track.getTrackLabel().draw(g, x, y);
    }



    //OLD IMPLEMENTATION HERE
//    private static void drawLabel(Track track, Graphics2D g, int x, int y) {
//        //get width of callsign string to position velocity string
//        int width = g.getFontMetrics().stringWidth(track.getCallsing());
//
//        //draw callsign
//        g.drawString(track.getCallsing(), x + 20, y + 2);
//        g.drawString(String.format("%.0f", track.getVelocity()), x + width + 20, y + 2);
//
//        //draw altitude
//        String baroAltitude = String.format("%.0f", track.getBaroAltitude());
//        g.drawString(baroAltitude, x + 20, y + 18);
//
//        //if track is dropping display info
//        if(track.isDropping())
//            g.drawString("DROP", x + 20, y - 14);
//    }

    private static void drawHistoricPlots(Track track, Graphics2D g, MapPanel mapPanel) {

        String historyLength = SettingsManager.getInstance().get("plot.history");
        int lenght;
        switch (historyLength) {
            case "Short" -> lenght = 5;
            case "Medium" -> lenght = 8;
            case "Long" -> lenght = 15;
            default -> lenght = 3;
        }
        //get current color used to draw track - dependant on user settings
        //might be brighter or dimmer
        int baseGreen = g.getColor().getGreen();

        List<TrackPosition> recentHistory = track.getRecentTrackHistory(lenght);
        int colorStep = baseGreen / (recentHistory.size() + 1);
        // im using classic loop to use i as size/color driver
        for (int i = recentHistory.size() - 1; i > 0; i--) {
            g.setColor(new Color(0, 30 + (colorStep * i), 0));

            int xx = mapPanel.convertX(recentHistory.get(i).getPosition().getEasting());
            int yy = mapPanel.convertY(recentHistory.get(i).getPosition().getNorthing(), mapPanel.getHeight());

            g.drawOval(xx - 2, yy - 2, 5, 5);
        }
    }

    private static void drawEcho(Graphics2D g, int x, int y) {
        // draw echo
        g.drawOval(x - 3, y - 3, 6, 6);
    }

//    private static void drawAltitudeTrendArrow(Track track, Graphics2D g, int x, int y, Double baroAltitude) {
//        // if absolute vertical rate is present and is greater than 1.6 m/s - more than 300feets per minute
//        // then draw altitude trend arrow and vertical rate (1m/s ~ 200feets per minute)
//        if (track.getVerticalRate() != null && Math.abs(track.getVerticalRate()) > 1.6) {
//
//            // get current alt to check it's size
//            String baroAltStr = String.format("%.0f", baroAltitude);
//            //then use that size to position trend arrow
//            int widthOfAltitudeString = g.getFontMetrics().stringWidth(baroAltStr);
//            //draw vertical line
//            g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 9, x + 20 + widthOfAltitudeString + 5, y + 17);
//            // draw tip of an arrow pointing up or down, depending on vertical speed value
//            if (track.getVerticalRate() > 0) {
//                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 9, x + 20 + widthOfAltitudeString + 7, y + 12);
//                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 9, x + 20 + widthOfAltitudeString + 3, y + 12);
//            } else {
//                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 17, x + 20 + widthOfAltitudeString + 7, y + 14);
//                g.drawLine(x + 20 + widthOfAltitudeString + 5, y + 17, x + 20 + widthOfAltitudeString + 3, y + 14);
//            }
//
//        }
//    }

    private static void drawVelocityVector(Track track, Graphics2D g, MapPanel mapPanel, int x, int y) {
        //if heading present then draw velocity vector
        //this check is needed because of getBearing method, need to make sure that
        //heading is not null to avoid exception
        if (track.getHeading() != null) {
            // get track lat lon
            double trackLongitude = track.getLongitude();
            double trackLatitude = track.getLatitude();

            //read user selected vector length
            double vectorLength = Double.parseDouble(SettingsManager.getInstance().get("vector.length"));


            double bearing = track.getBearing();
            // calculate lat lon of tip of vector

            // first calculate length of 1 degree of longitude at given latitude as described here:
            // https://en.wikipedia.org/wiki/Longitude chapter 'Length of a degree of longitude'
            double oneDegreAtLatitude = (Math.PI / 180) * 6371000 * Math.cos(Math.toRadians(trackLatitude));

            // then apply it to same formula as used in yyy calculation
            double tipOfVectorLongitude = trackLongitude + vectorLength * track.getVelocity() * (1852.0 / (60 * oneDegreAtLatitude)) * Math.sin(Math.toRadians(bearing));

            // along y axis - easier math
            // distance travelled in 1 min = (speed[kt]/60), to get that in meters multiply by 1852
            // 1 degree of latitude = 111 196m, so in 1 min we do (dist travelled[m]) / 111 196 m = degrees travelled
            // along vertical axis. Multiply that via cos(heading) and we have tipY coordinate

            double tipOfVectorLatitude = trackLatitude + vectorLength * track.getVelocity() * (1852.0 / (60 * 111196)) * Math.cos(Math.toRadians(bearing));

            BasePoint tipOfVector = new BasePoint(new Coordinates(tipOfVectorLatitude, tipOfVectorLongitude));

            // convert lat lon of vectors tip to x y
            int tipX = mapPanel.convertX(tipOfVector.getEasting());
            int tipY = mapPanel.convertY(tipOfVector.getNorthing(), mapPanel.getHeight());

            // draw line betwen x.y and tip of vector x.y
            g.drawLine(x, y, tipX, tipY);
        }
    }

    private static void setTrackColor(Track track, Graphics2D g) {
        // if spi (squawk ident) detected then draw track in blue
        String color = SettingsManager.getInstance().get("plot.brightness");

        if (track.getSpi() != null && track.getSpi())
            g.setColor(Colors.TRACK_COLOR_SPI);
        else if (track.isDropping())
            g.setColor(Colors.TRACK_COLOR_DROPING);
        else {
            switch(color) {
                case "High" -> g.setColor(Colors.TRACK_COLOR_HIGH);
                case "Medium" -> g.setColor(Colors.TRACK_COLOR_MEDIUM);
                case "Low" -> g.setColor(Colors.TRACK_COLOR_LOW);
            }
        }
    }
}
