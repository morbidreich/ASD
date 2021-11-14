package io.github.morbidreich.airspaceElements;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    public static final String ICAO = "EPSY";
    public static final String[] runways = new String[]{"01", "19"};

    public static Polygon getRunwayPolygon() {

        Coordinates c1 = new Coordinates(53.4712776385262, 20.931392776526227);
        Coordinates c2 = new Coordinates(53.471158458321696, 20.93204310082635);
        Coordinates c3 = new Coordinates(53.49246048979633, 20.943952653302773);
        Coordinates c4 = new Coordinates(53.49258304336401, 20.943350826488388);

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
