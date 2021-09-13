public class Airport {
    public static final String ICAO = "EPSY";
    public static final String[] runways = new String[]{"01", "19"};

    public static Polygon getRunwayPolygon() {

        Coordinates c1 = new Coordinates(53.4926, 20.9433);
        Coordinates c2 = new Coordinates(53.4923, 20.9441);
        Coordinates c3 = new Coordinates(53.4712, 20.9323);
        Coordinates c4 = new Coordinates(53.4712, 20.9313);

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
