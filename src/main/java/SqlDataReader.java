import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Reads airspace data from H2 database **/
public class SqlDataReader {

    public static void readData(Airspace airspace) throws Exception{
        airspace.setPolygonList(getPolygons());
        airspace.setFixList(getFixes());
    }

    public static List<Polygon> getPolygons() throws Exception {

        List<Polygon> polygonList = new ArrayList<>();

        Connection conn = DriverManager.getConnection("jdbc:h2:file:./airspace", "sa", "");

        List<String> polygonNames = readManifestTable(conn);

        for (String name: polygonNames) {
            polygonList.add(readPolygon(name, conn));
        }

        readSidStar(polygonList, conn);

        conn.close();
        return polygonList;
    }

    public static List<Fix> getFixes() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./airspace", "sa", "");
        return readFixes(conn);
    }

    private static void readSidStar(List<Polygon> polygonList, Connection conn) {
        String[] procedures = new String[] {
                //TODO unhardcode these
                "IBINO1R", "UDROV1R", "ARDUT1R", "IBINO2T", "UDROV2T", "ARDUT2T",
                "NIVON1S", "UDROV1S", "LUSUL1S", "NIVON1W", "UDROV1W", "LUSUL1W"
        };


        try {
            Statement st = conn.createStatement();
            for (String procedure: procedures) {
                Polygon poly;

                if (procedure.endsWith("1R")) {
                    poly = new Star(procedure, Runway.RUNWAY_01);
                    poly.setPolygonType(PolygonType.STAR);
                }
                else if (procedure.endsWith("2T")) {
                    poly = new Star(procedure, Runway.RUNWAY_19);
                    poly.setPolygonType(PolygonType.STAR);
                }
                else if (procedure.endsWith("1S")) {
                    poly = new Sid(procedure, Runway.RUNWAY_01);
                    poly.setPolygonType(PolygonType.SID);
                }
                else {
                    poly = new Sid(procedure, Runway.RUNWAY_19);
                    poly.setPolygonType(PolygonType.SID);
                }

                String query = "SELECT * FROM SID_STAR WHERE PROC_NAME = '" + procedure + "'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Coordinates c = CoordinateConverter.getFromDMS((rs.getString("coordinates")));
                    Point pt = new Point(c);
                    poly.addPoint(pt);
                    poly.setVisible(false);
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
                Coordinates c = CoordinateConverter.getFromDMS(rs.getString("Coordinates"));
                poly.addPoint(new Point(c));
            }
            System.out.println(name + " read with " + poly.getPointList().size() + " coordinates");

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

    private static List<Fix> readFixes(Connection conn) {
        List<Fix> out = new ArrayList<>();
        String query = "SELECT * FROM FIXES";
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String name = result.getString("fix_name");
                String coordString = result.getString("coordinates");
                Coordinates coord = CoordinateConverter.getFromDMS(coordString);
                FixType fixType = determineFixType(result.getString("fix_type"));
                out.add(new Fix(name, coord, fixType));
            }
        }
        catch(Exception e) {
            System.out.println("Error " + e);
        }

        return out;
    }

    private static FixType determineFixType(String fix_type) {
        switch (fix_type) {
            case "ENTRY": return FixType.ENTRY;
            case "SID19": return FixType.SID19;
            case "SID01": return FixType.SID01;
            case "STAR01": return FixType.STAR01;
            case "STAR19": return FixType.STAR19;
            case "VFR": return FixType.VFR;
            case "VOR": return FixType.VOR;
            case "AERODROME": return FixType.AERODROME;
            case "TOWN": return FixType.TOWN;
            default: return FixType.UNDEFINED;
        }
    }

}
