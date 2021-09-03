import java.util.*;

public class Airspace {

    private List<Fix> fixList;
    private List<Polygon> polygonList;

    public Airspace() {
        fixList = new ArrayList<>();
        polygonList = new ArrayList<>();

        readData();
    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }
    public void setPolygonList(List<Polygon> polygonList) { this.polygonList = polygonList; }

    public List<Fix> getFixList() { return fixList; }
    public void setFixList(List<Fix> fixList) { this.fixList = fixList; }

    private void readData() {
        try {
            System.out.println("trying to load from sql database");
            SqlDataReader.readData(this);
            System.out.println("succesfully loaded airspace data from sql");
        }
        catch (Exception e) {
            System.out.println("sql data load error, loading fallback data. " + e);
            FallbackDataReader.readData(this);
            System.out.println("succesfully loaded airspace data from fallback source");
        }
        finally {

        }
    }
    ArrayList<Sid> getSids() {
        ArrayList<Sid> list = new ArrayList<>();
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
