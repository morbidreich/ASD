

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.*;;
/**
 *  code by Cristopher Jacquet with my my slight modifications
 *  https://github.com/ChristopheJacquet/Minigeo
 */

@SuppressWarnings("serial")
public class MapWindow extends JFrame {
    private final MapPanel map;
    JMenuItem miAbout;



    /**
     * Creates a new window.
     */
    public MapWindow() {
        super("EPSY Airspace Display - NIE DO UŻYTKU OPERACYJNEGO!");
        map = new MapPanel();
        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuOptions = new JMenu("Options");
        JMenu menuElements = new JMenu("Select elements");
        JMenu menuClearRbls = new JMenu("Clear RBLs");
        JMenu menuClose = new JMenu("Close");

        menuBar.add(menuElements);
        menuBar.add(menuClearRbls);
        menuBar.add(menuOptions);
        menuBar.add(menuClose);

        JMenuItem miSettings = new JMenuItem("Settings");
        miAbout = new JMenuItem("About...");

        menuOptions.add(miSettings);
        menuOptions.add(miAbout);

        JCheckBoxMenuItem cbTma = new JCheckBoxMenuItem("TMA");
        JCheckBoxMenuItem cbCtr = new JCheckBoxMenuItem("CTR");
        JCheckBoxMenuItem cbAllFixes = new JCheckBoxMenuItem("All fixes");
        JCheckBoxMenuItem cbTmaFixes = new JCheckBoxMenuItem("TMA entry fixes");
        JCheckBoxMenuItem cbSid01 = new JCheckBoxMenuItem("SID 01");
        JCheckBoxMenuItem cbSid19 = new JCheckBoxMenuItem("SID 19");
        JCheckBoxMenuItem cbStar01 = new JCheckBoxMenuItem("STAR 01");
        JCheckBoxMenuItem cbStar19 = new JCheckBoxMenuItem("STAR 19");

        menuElements.add(cbTma);
        menuElements.add(cbCtr);
        menuElements.addSeparator();
        menuElements.add(cbAllFixes);
        menuElements.add(cbTmaFixes);
        menuElements.addSeparator();
        menuElements.add(cbSid01);
        menuElements.add(cbSid19);
        menuElements.add(cbStar01);
        menuElements.add(cbStar19);

        add(menuBar, BorderLayout.NORTH);

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


