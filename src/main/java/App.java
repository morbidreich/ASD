
import java.awt.*;
import java.util.List;
import java.sql.*;

public class App {

    public static void main(String[] args) {

        MapWindow mapWindow;

        POI poi = new POI(53.5, 20.92, "EPSY");

        Airspace airspace = new Airspace();
        

        mapWindow = new MapWindow();
        mapWindow.setVisible(true);

        mapWindow.addPOI(poi);

        List<Polygon> polygonList = airspace.getPolygonList();

        for(Polygon poly: polygonList) {

            for (int i = 0; i < poly.getFixList().size() - 1; i++) {

                mapWindow.addSegment(new Segment(
                        new Point(poly.getFixList().get(i).getLatitude(), poly.getFixList().get(i).getLongitude()),
                        new Point(poly.getFixList().get(i + 1).getLatitude(), poly.getFixList().get(i + 1).getLongitude()),
                        Color.BLACK
                ));
            }
        }
        getDataFromH2();
    }

    private static void getDataFromH2() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:file:./airspace", "sa", "");
            Statement st = conn.createStatement();
            String query = "SELECT * from CTR";
            ResultSet rs = st.executeQuery(query);

            rs.next();

            System.out.println(rs.getString("coordinates"));




            conn.close();
        }
        catch(Exception e) {
            System.out.println(e.toString());

        }

    }

    private void displayElements(MapWindow mapWindow, Airspace airspace) {

    }
}
