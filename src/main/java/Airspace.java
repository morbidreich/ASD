import java.util.*;

public class Airspace {

    private List<Fix> fixList;
    private List<Polygon> polygonList;
    private List<Polygon> sidList;
    private List<Polygon> starList;
    public Airport EPSY;

    public Airspace() {
        fixList = new ArrayList<>();
        polygonList = new ArrayList<>();
        sidList = new ArrayList<>();

        readData();
    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }
    public List<Fix> getFixList() { return fixList; }

    private void readData() {
        try {
            System.out.println("trying to load from sql database");
            polygonList = SqlDataReader.getPolygons();
            fixList = SqlDataReader.getFixes();
            System.out.println("succesfully loaded airspace data from sql");
        }
        catch (Exception e) {
            System.out.println("sql data load error, loading fallback data. " + e);
            polygonList = FallbackDataReader.getPolygons();
            fixList = FallbackDataReader.getFixes();
            System.out.println("succesfully loaded airspace data from fallback source");
        }
        finally {

        }
    }
    ArrayList<Sid> getSids() {
        ArrayList<Sid> list = new ArrayList<Sid>();
        for (Polygon poly : getPolygonList()) {
            if (poly instanceof Sid)
                list.add((Sid)poly);
        }
        return list;
    }

    ArrayList<Star> getStars() {
        ArrayList<Star> list = new ArrayList<>();
        for (Polygon poly: getPolygonList()) {
            if (poly instanceof Star)
                list.add((Star) poly);
        }
        return list;
    }
}
