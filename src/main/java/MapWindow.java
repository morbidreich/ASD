import java.awt.*;

import javax.swing.*;

/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

public class MapWindow extends JFrame {
    private final MapPanel map;

    /**
     * Creates a new window..
     */
    public MapWindow(Airspace airspace) {

        super("EPSY Airspace Display - NOT FOR OPERATIONAL USE!");

        map = new MapPanel();
        map.addPolygons(airspace.getPolygonList());
        map.addFixes(airspace.getFixList());
        map.addPolygon(Airport.getRunwayPolygon());
        setMapZoomLevel(map);

        Menu menu = new Menu(map, airspace);
        setJMenuBar(menu.getMenuBar());

        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/icon.png");
        setIconImage(icon);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setMapZoomLevel(MapPanel map) {
        // fool minigeo to zoom out view by adding two dummy invisible fixes outside of tma
        // on southeast and northwest
        Coordinates c1 = CoordinateConverter.getFromDMS("53°40'00\"N 019°48'00\"E");
        Coordinates c2 = CoordinateConverter.getFromDMS("53°10'00\"N 021°24'00\"E");

        map.addFix(new Fix(c1));
        map.addFix(new Fix(c2));
    }

    /**
     * Deletes all the registered segments and POIs.
     */
    @SuppressWarnings("unused")
    public void clear() {
        map.clear();
    }

}


