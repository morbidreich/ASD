import java.util.ArrayList;
import java.util.List;

public class Airport {
    public static final String ICAO = "EPSY";
    public static final String[] runways = new String[]{"01", "19"};

    public static Polygon getRunwayPolygon() {

        Coordinates c1 = new Coordinates(53.4926, 20.9433);
        Coordinates c2 = new Coordinates(53.4923, 20.9441);
        Coordinates c3 = new Coordinates(53.4712, 20.9323);
        Coordinates c4 = new Coordinates(53.4712, 20.9313);

        Polygon out = new Polygon();
        out.setName("Runway");
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(c1));
        pointList.add(new Point(c2));
        pointList.add(new Point(c3));
        pointList.add(new Point(c4));
        //repeat first one to create closed polygon
        pointList.add(new Point(c1));
        out.addPoints(pointList);
        out.setVisible(true);


        return out;
    }
}
