package io.github.morbidreich.surveilance;

import io.github.morbidreich.ui.MapPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TrackLabel {
    private int labelX, labelY;
    private final int labelWidth = 80;
    private final int labelHeight = 40;
    private boolean isDragged;
    private Track track;

    private int displacementX, displacementY = 0;

    public TrackLabel(Track track) {
        this.track = track;

    }

    //x and y are screen coordinates of track
    public void draw(Graphics g, int x, int y) {

        labelX = x + displacementX + 20;
        labelY = y + displacementY - 40;

        //draw line from track to label
        g.drawLine(x, y, x + displacementX + 20, y + displacementY - 20);

        //draw outline of label
        g.drawRect(x + displacementX + 20, y + displacementY - 40, labelWidth, labelHeight);
        g.drawString(track.getCallsing(), x + displacementX + 25, y + displacementY - 25);
    }

    public boolean isMouseOver(MouseEvent e) {
        return (e.getX() > labelX && e.getX() < labelX + labelWidth &&
                e.getY() > labelY && e.getY() < labelY + labelHeight);
    }

    public int getLabelX() {
        return labelX;
    }

    public int getLabelY() {
        return labelY;
    }

    public void setDisplacementX(int displacementX) {
        this.displacementX = displacementX;
    }

    public void setDisplacementY(int displacementY) {
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
}
