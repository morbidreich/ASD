import java.awt.*;

public class RBL {
    private Point startPoint;
    private Point endPoint;
    private int x, y;


    public RBL(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
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

        //int iAngle = (int)angle;
        int iAngle = (int)(angle < 0 ? angle + 360 : angle);

        //TODO apply magnetic declination, around +5deg


        // display to the right or left of cursor to avoid overlapping

        if (x1 < x2) {
            // label displayed left of cursor
            g.drawString("R: " + sDist + "NM", x1 - 60, y1);
            g.drawString("B: " + iAngle + "\u00B0", x1 - 60, y1 + 15);
            g.drawRect(x1-65, y1-15, 65, 35);
        }
        else {
            //label displayed right of cursor
            g.drawString("R: " + sDist + "NM", x1 + 15, y1);
            g.drawString("B: " + iAngle + "\u00B0", x1 + 15, y1 + 15);
            g.drawRect(x1+10, y1-15, 65, 35);
        }


    }
}
