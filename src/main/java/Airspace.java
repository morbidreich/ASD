import java.util.ArrayList;
import java.util.List;

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

    private void readData() {


        try {
            polygonList = SqlDataReader.getPolygons();
            fixList = SqlDataReader.getFixes();
            System.out.println("succesfully loaded airspace data from sql");
        }
        catch (Exception e) {
            System.out.println("sql data load error, loading fallback data");
            polygonList = FallbackDataReader.getPolygons();
            fixList = FallbackDataReader.getFixes();
            System.out.println("succesfully loaded airspace data from fallback source");
        }
        finally {

        }




    }
}
