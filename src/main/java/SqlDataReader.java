import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Reads airspace data from H2 database **/
public class SqlDataReader {

    public static List<Polygon> getPolygons() throws Exception {

        List<Polygon> polygonList = new ArrayList<>();
        List<String> polygonNames = new ArrayList<>();

        Connection conn = DriverManager.getConnection("jdbc:h2:file:./airspace", "sa", "");

        polygonNames = readManifestTable(conn);

        for (String name: polygonNames) {
            polygonList.add(readPolygon(name, conn));
        }

        conn.close();
        return polygonList;


    }

    private static Polygon readPolygon(String name, Connection conn){
        System.out.println("Attempting to read polygon: " + name);
        Polygon poly = new Polygon(name);
        try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM " + name;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                poly.addFix(CoordinateConverter.getFromDMS(rs.getString("Coordinates")));
            }
            System.out.println(name + " read with " + poly.getFixList().size() + " coordinates");

        }
        catch (Exception e) {
            System.out.println("Exception reading " + name + ": " + e);
        }
        return poly;
    }

    private static List<String> readManifestTable(Connection conn) throws Exception {
        List<String> out = new ArrayList<>();
        String query = "SELECT * FROM POLYGONS";
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            out.add(result.getString("polygon_name"));
        }
        System.out.println("Manifest table read");
        return out;
    }

    public static List<Fix> getFixes() {
        List<Fix> fixList = new ArrayList<>();
        return fixList;
    }
}
