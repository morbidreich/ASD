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

        // create polygon for CTR

        String[] paths = new String[] {
            "src/main/resources/EPSY/CTR.txt",
            "src/main/resources/EPSY/TMA A.txt",
            "src/main/resources/EPSY/TMA B.txt",
            "src/main/resources/EPSY/TMA C.txt",
            "src/main/resources/EPSY/TMA D.txt"
        };

        for(String path: paths) {

            Polygon poly = new Polygon("poly");

            try {
                File file = new File(path);
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    poly.addFix(CoordinateConverter.getFromDMS(fileReader.nextLine()));
                }
                polygonList.add(poly);
            } catch (Exception e) {
                System.out.println("Data read failed; " + e.getMessage());

            }
        }
    }
}
