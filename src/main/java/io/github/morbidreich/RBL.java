package io.github.morbidreich;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RBL {
    private BasePoint startPoint;
    private BasePoint endPoint;
    private int labelX, labelY;
    private final int labelWidth = 65;
    private final int labelHeight = 35;


    public RBL(BasePoint startPoint, BasePoint endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public BasePoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(BasePoint startPoint) {
        this.startPoint = startPoint;
    }

    public BasePoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(BasePoint endPoint) {
        this.endPoint = endPoint;
    }

    public void drawLabel(int x1, int y1, int x2, int y2, double scale, Graphics2D g) {

        //calculate distance
        double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / scale;
        dist *= 0.5399568; //convert km to nautical miles
        String sDist = String.format("%.1f", dist);

        //calculate bearing
        //https://math.stackexchange.com/questions/707673/find-angle-in-degrees-from-one-point-to-another-in-2d-space
        double a = y2 - y1;
        double b = x2 - x1;
        //default 0 is pointing eastward so i rotate it counterclockwise by substracting 90 degrees
        double angle = Math.toDegrees(Math.atan2(a, b)) - 90;
        //apply magnetic variation, around +5deg
        angle -= ApplicationSettings.MAGNETIC_VARIATION;
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
