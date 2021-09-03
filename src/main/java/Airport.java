public class Airport {
    public static final String ICAO = "EPSY";
    public static final String[] runways = new String[]{"01", "19"};

    public static Polygon getRunwayPolygon() {

        Coordinates c1 = new Coordinates(53.49261, 20.9433);
        Coordinates c2 = new Coordinates(53.49237, 20.94416);
        Coordinates c3 = new Coordinates(53.47121, 20.93233);
        Coordinates c4 = new Coordinates(53.47123, 20.93135);

        Polygon out = new Polygon("Runway");
        out.addPoint(new Point(c1));
        out.addPoint(new Point(c2));
        out.addPoint(new Point(c3));
        out.addPoint(new Point(c4));
        //repeat first one to create closed polygon
        out.addPoint(new Point(c1));

        return out;
    }
}
