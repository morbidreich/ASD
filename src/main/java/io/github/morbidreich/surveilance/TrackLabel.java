package io.github.morbidreich.surveilance;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.ui.MapPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
//TODO think of renaming it to TrackLabelDrawer, moving to correct package

public class TrackLabel {
    private int labelX, labelY;
    private int labelWidth;
    private final int labelHeight = 36;
    private final Track track;
    private boolean isMouseOver = false;


    private int displacementX, displacementY = 0;

    public TrackLabel(Track track) {
        this.track = track;
    }

    //x and y are screen coordinates of track
    public void draw(Graphics2D g, int x, int y) {

        //reference values used for drawing label elements
        //adjusted for current x/y displacement
        int xx = x + displacementX;
        int yy = y + displacementY;

        labelX = xx + 20;
        labelY = yy - 40;


        drawLineFromTrackToLabel(g, x, y);
        // modify line connecting label with track
        // to avoid overlapping label by line in certain positions (e.g left of track)
//        if (displacementX < -45 && displacementY > 10)
//            g.drawLine(x, y, xx + 70, yy - 40);
//            //g.drawLine(x, y, xx + 20, yy - 10);
//        else
//            g.drawLine(x, y, xx + 20, yy - 10);

        //format speed and altitude to round decimal part
        String velocity = String.format("%.0f", track.getVelocity());
        String altitude = String.format("%.0f", track.getBaroAltitude());

        //get width of callsign string to position velocity string
        int widthCallsign = g.getFontMetrics().stringWidth(track.getCallsing());
        int widthVelocity = g.getFontMetrics().stringWidth(velocity);

        g.drawString(track.getCallsing(), xx + 25, yy - 25);
        g.drawString(velocity, xx + widthCallsign + 30, yy - 25);
        g.drawString(altitude, xx + 25, yy - 10);

        //calculate label width
        labelWidth = 5 + widthCallsign + 5 + widthVelocity + 5;

        drawAltitudeTrendArrow(track, g, xx, yy);

        //TODO if mouse over label then draw outline
        if (isMouseOver)
            g.drawRect(labelX, labelY, labelWidth, labelHeight);
    }

    private void drawLineFromTrackToLabel(Graphics2D g, int x, int y) {
        // start of line will always be at x, y - which is track position

        // case I
        if (displacementX > -80 && displacementY < 22)
            g.drawLine(x, y, labelX, labelY+labelHeight);
        if (displacementX > -80 && displacementY >= 22)
            g.drawLine(x, y, labelX, labelY);
        if (displacementX <= -80 && displacementY < 22)
            g.drawLine(x, y, labelX+labelWidth, labelY+labelHeight);
        if (displacementX <= -80 && displacementY >= 22)
            g.drawLine(x, y, labelX+labelWidth, labelY);



    }


    public boolean isMouseOver(MouseEvent e) {
        return (e.getX() > labelX && e.getX() < labelX + labelWidth &&
                e.getY() > labelY && e.getY() < labelY + labelHeight);
    }

    private void drawAltitudeTrendArrow(Track track, Graphics2D g, int xx, int yy) {
        // if absolute vertical rate is present and is greater than 1.6 m/s - more than 300feets per minute
        // then draw altitude trend arrow and vertical rate (1m/s ~ 200feets per minute)
        if (track.getVerticalRate() != null && Math.abs(track.getVerticalRate()) > 1.6) {

            // get current alt to check it's size
            String baroAltStr = String.format("%.0f", track.getBaroAltitude());
            //then use that size to position trend arrow
            int widthOfAltitudeString = g.getFontMetrics().stringWidth(baroAltStr);
            //draw vertical line
            g.drawLine(xx + 20 + widthOfAltitudeString + 8, yy - 19, xx + 20 + widthOfAltitudeString + 8, yy - 11);
            // draw tip of an arrow pointing up or down, depending on vertical speed value
            if (track.getVerticalRate() > 0) {
                g.drawLine(xx + 20 + widthOfAltitudeString + 8, yy - 19, xx + 20 + widthOfAltitudeString + 10, yy - 16);
                g.drawLine(xx + 20 + widthOfAltitudeString + 8, yy - 19, xx + 20 + widthOfAltitudeString + 6, yy - 16);
            } else {
                g.drawLine(xx + 20 + widthOfAltitudeString + 8, yy - 11, xx + 20 + widthOfAltitudeString + 10, yy - 14);
                g.drawLine(xx + 20 + widthOfAltitudeString + 8, yy - 11, xx + 20 + widthOfAltitudeString + 6, yy - 14);
            }
        }
    }

    public void setDisplacementX(int displacementX) {
        // limit maximum distace that label can be displaced
        // if label is too far from track then it behaves strange
        // while zooming/unzooming
        if (displacementX > 30)
            this.displacementX = 30;
        else if (displacementX < -120)
            this.displacementX = -120;
        else
            this.displacementX = displacementX;
    }

    public void setDisplacementY(int displacementY) {
        if (displacementY > 70)
            this.displacementY = 70;
        else if (displacementY < -50)
            this.displacementY = -50;
        else
            this.displacementY = displacementY;
    }

    public int getDisplacementX() {
        return displacementX;
    }

    public int getDisplacementY() {
        return displacementY;
    }

    public Track getTrack() {
        return this.track;
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        isMouseOver = mouseOver;
    }
}
