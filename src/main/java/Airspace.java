import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            //read sql
        }
        catch (Exception e) {

        }
        finally {
            polygonList = FallbackDataReader.getPolygons();
        }




    }
}
