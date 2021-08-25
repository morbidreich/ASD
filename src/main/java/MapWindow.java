

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JFrame;;
/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

@SuppressWarnings("serial")
public class MapWindow extends JFrame {
    private final MapPanel map;

    /**
     * Creates a new window.
     */
    public MapWindow() {
        super("EPSY Airspace Display - NIE DO UŻYTKU OPERACYJNEGO!");
        map = new MapPanel();
        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Deletes all the registered segments and POIs.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Adds a segment to the list of segments to display.
     * @param segment the segment to add
     */
    public void addSegment(Segment segment) {
        map.addSegment(segment);
    }

    /**
     * Adds a whole collection of segments to the list of segments to display
     * @param segments the collection of segments to add
     */
    public void addSegments(Collection<Segment> segments) {
        map.addSegments(segments);
    }

    /**
     * Adds a point of interest (POI) to the list of POIs to display.
     * @param poi the POI to add
     */
    public void addPOI(POI poi) {
        map.addPOI(poi);
    }

    /**
     * Adds a Range Bearing Line (RBL) to the list of RBLs to display.
     * @param rbl the RBL to add
     */
    public void addRBL(RBL rbl) { map.addRBL(rbl); }
}
