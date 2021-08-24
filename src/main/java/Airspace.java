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
        }
        catch (Exception e) {
            polygonList = FallbackDataReader.getPolygons();
            fixList = FallbackDataReader.getFixes();
        }
        finally {

        }




    }
}
