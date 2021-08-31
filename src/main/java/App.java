
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

        //mapWindow.addPOI(poi);

        List<Polygon> polygonList = airspace.getPolygonList();

        for(Polygon poly: polygonList) {

            for (int i = 0; i < poly.getFixList().size() - 1; i++) {

                mapWindow.addSegment(new Segment(
                        new Point(poly.getFixList().get(i).getLatitude(), poly.getFixList().get(i).getLongitude()),
                        new Point(poly.getFixList().get(i + 1).getLatitude(), poly.getFixList().get(i + 1).getLongitude()),
                        Colors.getColor(poly.getPolygonType())
                ));
            }
        }
    }



    private void displayElements(MapWindow mapWindow, Airspace airspace) {

    }
}
