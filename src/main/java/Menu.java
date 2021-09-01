import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.*;

public class Menu implements ActionListener, MenuListener, MouseListener {
    private Airspace airspace;
    private MapPanel mapPanel;

    private final JMenu menuOptions;
    private final JMenu menuElements;
    private final JMenu menuClearRbls;
    private final JMenu menuClose;
    private final JMenuBar menuBar;
    private final JMenuItem miSettings;
    private final JCheckBoxMenuItem cbTma;
    private final JCheckBoxMenuItem cbCtr;
    private final JCheckBoxMenuItem cbAllFixes;
    private final JCheckBoxMenuItem cbTmaFixes;
    private final JCheckBoxMenuItem cbSid01;
    private final JCheckBoxMenuItem cbSid19;
    private final JCheckBoxMenuItem cbStar01;
    private final JCheckBoxMenuItem cbStar19;
    private final JCheckBoxMenuItem cbPDR;
    private final JCheckBoxMenuItem cbTSA;
    private final JCheckBoxMenuItem cbTRA;
    private final JCheckBoxMenuItem cbTowns;
    private final JCheckBoxMenuItem cbRivers;
    private final JCheckBoxMenuItem cbRoads;
    private final JMenuItem miAbout;

    public Menu(MapPanel mapPanel, Airspace airspace) {
        this.mapPanel = mapPanel;
        this.airspace = airspace;



        menuBar = new JMenuBar();

        menuOptions = new JMenu("Options");
        menuElements = new JMenu("Select elements");
        menuClearRbls = new JMenu("Clear RBLs");
        menuClearRbls.addMouseListener(this);
        menuClose = new JMenu("Close");
        menuClose.addMenuListener(this);
        menuClose.addMouseListener(this);

        menuBar.add(menuElements);
        menuBar.add(menuClearRbls);
        menuBar.add(menuOptions);
        menuBar.add(menuClose);

        miSettings = new JMenuItem("Settings");
        miAbout = new JMenuItem("About...");
        miAbout.addActionListener(this);

        menuOptions.add(miSettings);
        menuOptions.add(miAbout);

        cbTma = new JCheckBoxMenuItem("TMA");
        cbCtr = new JCheckBoxMenuItem("CTR");
        cbAllFixes = new JCheckBoxMenuItem("All fixes");
        cbTmaFixes = new JCheckBoxMenuItem("TMA entry fixes");
        cbSid01 = new JCheckBoxMenuItem("SID 01");
        cbSid19 = new JCheckBoxMenuItem("SID 19");
        cbStar01 = new JCheckBoxMenuItem("STAR 01");
        cbStar19 = new JCheckBoxMenuItem("STAR 19");
        cbPDR = new JCheckBoxMenuItem("P/D/R");
        cbTSA = new JCheckBoxMenuItem("TSA");
        cbTRA = new JCheckBoxMenuItem("TRA");
        cbTowns = new JCheckBoxMenuItem("Towns");
        cbRivers = new JCheckBoxMenuItem("Rivers");
        cbRoads = new JCheckBoxMenuItem("Roads");

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
        menuElements.addSeparator();
        menuElements.add(cbPDR);
        menuElements.add(cbTSA);
        menuElements.add(cbTRA);
        menuElements.addSeparator();
        menuElements.add(cbTowns);
        menuElements.add(cbRivers);
        menuElements.add(cbRoads);

        cbRoads.setEnabled(false);
        cbRivers.setEnabled(false);
        cbTowns.setEnabled(false);
        cbPDR.setEnabled(false);
        cbTSA.setEnabled(false);
        cbTRA.setEnabled(false);

        cbTma.setSelected(true);
        cbCtr.setSelected(true);

    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == miAbout) {
            AboutWindow aw = new AboutWindow();
            aw.setLocationRelativeTo(mapPanel);
            aw.setVisible(true);
        }
        if (e.getSource() == menuClose)
            System.out.println(e.getActionCommand());
    }

    @Override
    public void menuSelected(MenuEvent e) {

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(menuClose))
            System.exit(0);
        else if (e.getSource().equals(menuClearRbls)) {
            mapPanel.getRbls().clear();
            mapPanel.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
