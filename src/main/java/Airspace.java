import java.util.ArrayList;
import java.util.List;

public class Airspace {

    private List<Fix> fixList;
    private List<Polygon> polygonList;
    private List<Polygon> sidList;
    private List<Polygon> starList;

    public Airspace() {
        fixList = new ArrayList<>();
        polygonList = new ArrayList<>();
        sidList = new ArrayList<>();


        readData();
    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }

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
}
