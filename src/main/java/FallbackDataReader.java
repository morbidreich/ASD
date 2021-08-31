import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Fallback data set in case if H2 is unavailable
 */
public class FallbackDataReader {

    public static List<Polygon> getPolygons() {
        List<Polygon> polygonList = new ArrayList<>();
        String[] paths = new String[] {
                "src/main/resources/EPSY/CTR.txt",
                "src/main/resources/EPSY/TMA A.txt",
                "src/main/resources/EPSY/TMA B.txt",
                "src/main/resources/EPSY/TMA C.txt",
                "src/main/resources/EPSY/TMA D.txt"};
        for(String path: paths) {

            Polygon poly = new Polygon("poly");

            try {
                File file = new File(path);
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    poly.addFix(new Fix("", CoordinateConverter.getFromDMS(fileReader.nextLine())));
                }
                polygonList.add(poly);
            } catch (Exception e) {
                System.out.println("Data read failed; " + e.getMessage());
            }
        }
        return polygonList;
    }

    public static List<Fix> getFixes() {
        //TODO read fixes
        List<Fix> fixList = new ArrayList<Fix>();


        return fixList;

    }
}
