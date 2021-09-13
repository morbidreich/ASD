import java.awt.BorderLayout;
import java.util.Collection;
import java.util.List;

import javax.swing.*;
;
/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

@SuppressWarnings("serial")
public class MapWindow extends JFrame {
    private final MapPanel map;
    private Airspace airspace;
    private Menu menu;


    /**
     * Creates a new window.
     */
    public MapWindow(Airspace airspace) {
        super("EPSY Airspace Display - NOT FOR OPERATIONAL USE!");
        map = new MapPanel();
        this.airspace = airspace;
        map.addPolygons(airspace.getPolygonList());
        map.addFixes(airspace.getFixList());
        map.addPolygon(Airport.getRunwayPolygon());
        //setMapZoomLevel(map);
        menu = new Menu(map, airspace);
        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);

        setJMenuBar(menu.getMenuBar());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    private void setMapZoomLevel(MapPanel map) {
        // fool minigeo to zoom out view by adding two dummy invisible fixes outside of tma
        // on southeast and northwest
        //Coordinates c1 = CoordinateConverter.getFromDMS("53°40'00\"N 019°48'00\"E");
        //Coordinates c2 = CoordinateConverter.getFromDMS("53°10'00\"N 021°24'00\"E");

        //map.addFix(new Fix("", c1, FixType.UNDEFINED));
        //map.addFix(new Fix("", c2, FixType.UNDEFINED));
    }

    /**
     * Deletes all the registered segments and POIs.
     */
    public void clear() {
        map.clear();
    }

    public void addPolygons(List<Polygon> polygons) { map.addPolygons(polygons); }

    /**
     * Adds a Range Bearing Line (RBL) to the list of RBLs to display.
     * @param rbl the RBL to add
     */
    public void addRBL(RBL rbl) { map.addRBL(rbl); }
}


