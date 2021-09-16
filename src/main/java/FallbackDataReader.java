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
        System.out.println("FallbackDataReader begin data read...");
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
        System.out.println("FallbackDataReader polygons read: " + polygonList.size());
        return polygonList;
    }

    private static List<Fix> getFixes() {
        //TODO read fixes
        List<Fix> fixList = new ArrayList<Fix>();
        String path = "src/main/resources/EPSY/FIXES";

        try {
            File file = new File(path);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {

                Fix fix = new Fix();
                fix.setFixType(FixType.UNDEFINED);

                String[] out = fileReader.nextLine().split("\s");

                System.out.println(out[0] + " " + out[1]);
                fix.setCoordinates(out[0] + " " + out[1]);
                fix.setName(out[2]);
                fix.setVisible(true);
                fixList.add(fix);
            }

        } catch (Exception e) {
            System.out.println("Data read failed; " + e.getMessage());
        }
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
