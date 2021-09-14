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
        map.addAirspace(airspace);
        map.setDefaultElementsVisibility();

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


    /**
     * Deletes all the registered segments and POIs.
     */
    @SuppressWarnings("unused")
    public void clear() {
        map.clear();
    }

}


