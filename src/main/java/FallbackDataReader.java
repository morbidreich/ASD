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
        String[] paths = new String[]{
                "src/main/resources/EPSY/CTR",
                "src/main/resources/EPSY/TMA_A",
                "src/main/resources/EPSY/TMA_B",
                "src/main/resources/EPSY/TMA_C",
                "src/main/resources/EPSY/TMA_D"};
        for (String path : paths) {

            String polyName = path.substring(path.lastIndexOf('/') + 1);
            Polygon poly = new Polygon(polyName);

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
