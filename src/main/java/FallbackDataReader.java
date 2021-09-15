import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Fallback data set in case if H2 is unavailable
 */
public class FallbackDataReader implements AirspaceReader {

    private static List<Polygon> getPolygons() {
        List<Polygon> polygonList = new ArrayList<>();
        String[] paths = new String[]{
                "src/main/resources/EPSY/CTR",
                "src/main/resources/EPSY/TMA_A",
                "src/main/resources/EPSY/TMA_B",
                "src/main/resources/EPSY/TMA_C",
                "src/main/resources/EPSY/TMA_D"};
        for (String path : paths) {

            String polyName = path.substring(path.lastIndexOf('/') + 1);

            Polygon poly = new Polygon();
            if (polyName.equals("CTR"))
                poly.setPolygonType(PolygonType.CTR);
            else
                poly.setPolygonType(PolygonType.TMA);

            try {
                File file = new File(path);
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    Coordinates c = CoordinateConverter.getFromDMS(fileReader.nextLine());
                    poly.addPoint(new Point(c));
                }
                poly.setVisible(true);
                polygonList.add(poly);
            } catch (Exception e) {
                System.out.println("Data read failed; " + e.getMessage());
            }
        }
        return polygonList;
    }

    private static List<Fix> getFixes() {
        //TODO read fixes
        List<Fix> fixList = new ArrayList<Fix>();

        return fixList;
    }

    private static List<Procedure> getProcedures() {
        return new ArrayList<Procedure>();
    }

    @Override
    public Airspace readAirspace() {
        Airspace a = new Airspace();
        a.setPolygonList(getPolygons());
        a.setProcedureList(getProcedures());
        a.setFixList(getFixes());

        return a;
    }
}
