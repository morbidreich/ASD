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

        readSidStar(polygonList, conn);

        conn.close();
        return polygonList;
    }

    private static void readSidStar(List<Polygon> polygonList, Connection conn) {
        String[] stars = new String[] {
                //TODO unhardcode these
                "IBINO1R", "UDROV1R", "ARDUT1R", "IBINO2T", "UDROV2T", "ARDUT2T"
        };

        String[] sids = new String[] {
                //TODO unhardcode these
                "NIVON1S", "UDROV1S", "LUSUL1S", "NIVON1W", "UDROV1W", "LUSUL1W"
        };

        try {
            Statement st = conn.createStatement();
            for (String star: stars) {
                Polygon poly = new Polygon(star);
                poly.setPolygonType(PolygonType.STAR);
                String query = "SELECT * FROM STAR WHERE STAR_NAME = '" + star + "'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String fixName = rs.getString("fix_name");
                    System.out.println(rs.getString("coordinates"));
                    Coordinates fixCoord = CoordinateConverter.getFromDMS((rs.getString("coordinates")));
                    Fix starFix = new Fix(fixName, fixCoord);
                    poly.addFix(starFix);
                }
                polygonList.add(poly);
            }
            // this is copypasted from above, definitely needs refactoring
            for (String sid: sids) {
                Polygon poly = new Polygon(sid);
                poly.setPolygonType(PolygonType.SID);
                String query = "SELECT * FROM SID WHERE SID_NAME = '" + sid + "'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String fixName = rs.getString("fix_name");
                    System.out.println(rs.getString("coordinates"));
                    Coordinates fixCoord = CoordinateConverter.getFromDMS((rs.getString("coordinates")));
                    Fix starFix = new Fix(fixName, fixCoord);
                    poly.addFix(starFix);
                }
                polygonList.add(poly);
            }
        }
        catch (Exception e) {

        }


    }

    private static Polygon readPolygon(String name, Connection conn){
        System.out.println("Attempting to read polygon: " + name);
        Polygon poly = new Polygon(name);

        if (name.startsWith("CTR"))
            poly.setPolygonType(PolygonType.CTR);
        else if(name.startsWith("TMA"))
            poly.setPolygonType(PolygonType.TMA);

        try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM " + name;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                poly.addFix(new Fix("", CoordinateConverter.getFromDMS(rs.getString("Coordinates"))));
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
