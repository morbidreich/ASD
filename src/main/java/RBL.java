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

    public void drawMe(Graphics2D g) {

    }

    public void drawLabel(int x1, int y1, int x2, int y2, double scale, Graphics2D g) {

        //calculate distance
        double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / scale;
        dist *= 0.5399568; //convert to nautical miles
        int idist = (int)dist;



        // display to the right or left of cursor to avoid overlapping

        if (x1 < x2) {
            // label displayed left of cursor
            g.drawString("R:" + idist + "NM", x1 - 50, y1);
            g.drawString("B: ", x1 - 50, y1 + 15);
            g.drawRect(x1-55, y1-15, 50, 35);
        }
        else {
            //label displayed right of cursor
            g.drawString("R: " + idist + "NM", x1 + 15, y1);
            g.drawString("B: ", x1 + 15, y1 + 15);
            g.drawRect(x1+10, y1-15, 50, 35);
        }


    }
}
