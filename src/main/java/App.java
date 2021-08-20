import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;

import java.awt.*;
import java.util.List;
import java.sql.*;

public class App {

    public static void main(String[] args) {

        MapWindow mapWindow;

        Airspace airspace = new Airspace();

        mapWindow = new MapWindow();
        mapWindow.setVisible(true);

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
        connectToDatabase();
    }

    private static void connectToDatabase() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:file:./test", "sa", "");

            Statement statement = conn.createStatement();
            //statement.executeUpdate("create database airspace");
            //System.out.println("database created");
            statement.executeUpdate("DROP TABLE IF EXISTS TEST");

            statement.executeUpdate("CREATE TABLE TEST(id int primary key, name varchar(30))");
            statement.executeUpdate("insert into test values (1, 'Bartek')");
            statement.executeUpdate("insert into test values (2, 'Aleksandra')");
            statement.executeUpdate("insert into test values (3, 'Hanna')");
            statement.executeUpdate("insert into test values (4, 'Kalasanty')");
            System.out.println("Test table created");
            conn.close();
        }
        catch(Exception e) {
            System.out.println(e.toString());

        }

    }

    private void displayElements(MapWindow mapWindow, Airspace airspace) {

    }
}
