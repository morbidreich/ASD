import java.util.ArrayList;

public class Airport {
    public static final String ICAO = "EPSY";
    public static final String[] runways = new String[]{"01", "19"};

    public static Polygon getRunwayPolygon() {

        Coordinates c1 = new Coordinates(53.49261, 20.9433);
        Coordinates c2 = new Coordinates(53.49237, 20.94416);
        Coordinates c3 = new Coordinates(53.47121, 20.93233);
        Coordinates c4 = new Coordinates(53.47123, 20.93135);

        Polygon out = new Polygon("Runway");
        out.addFix(new Fix("", c1, FixType.UNDEFINED));
        out.addFix(new Fix("", c2, FixType.UNDEFINED));
        out.addFix(new Fix("", c3, FixType.UNDEFINED));
        out.addFix(new Fix("", c4, FixType.UNDEFINED));
        out.addFix(new Fix("", c1, FixType.UNDEFINED));

        return out;
    }
}
