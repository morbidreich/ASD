import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.awt.*;
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
    private final JCheckBoxMenuItem cbTmaFixes;
    private final JCheckBoxMenuItem cbVfrFixes;
    private final JCheckBoxMenuItem cbSid01;
    private final JCheckBoxMenuItem cbSid01fix;
    private final JCheckBoxMenuItem cbSid19;
    private final JCheckBoxMenuItem cbSid19fix;
    private final JCheckBoxMenuItem cbStar01;
    private final JCheckBoxMenuItem cbStar01fix;
    private final JCheckBoxMenuItem cbStar19;
    private final JCheckBoxMenuItem cbStar19fix;
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

        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setPreferredSize(new Dimension(120,20));

        cbTma = new JCheckBoxMenuItem("TMA");
        cbCtr = new JCheckBoxMenuItem("CTR");
        cbTmaFixes = new JCheckBoxMenuItem("TMA entry fixes");
        cbVfrFixes = new JCheckBoxMenuItem("VFR fixes");
        cbSid01 = new JCheckBoxMenuItem("SID 01");
        cbSid01fix = new JCheckBoxMenuItem("SID 01 fix");
        cbSid19 = new JCheckBoxMenuItem("SID 19");
        cbSid19fix = new JCheckBoxMenuItem("SID 19 fix");
        cbStar01 = new JCheckBoxMenuItem("STAR 01");
        cbStar01fix = new JCheckBoxMenuItem("STAR 01 fix");
        cbStar19 = new JCheckBoxMenuItem("STAR 19");
        cbStar19fix = new JCheckBoxMenuItem("STAR 19 fix");
        cbPDR = new JCheckBoxMenuItem("P/D/R");
        cbTSA = new JCheckBoxMenuItem("TSA");
        cbTRA = new JCheckBoxMenuItem("TRA");
        cbTowns = new JCheckBoxMenuItem("Towns");
        cbRivers = new JCheckBoxMenuItem("Rivers");
        cbRoads = new JCheckBoxMenuItem("Roads");

        menuElements.add(cbTma);
        menuElements.add(slider);
        menuElements.add(cbCtr);
        menuElements.addSeparator();
        menuElements.add(cbTmaFixes);
        menuElements.add(cbVfrFixes);
        menuElements.addSeparator();
        menuElements.add(cbSid01);
        menuElements.add(cbSid01fix);
        menuElements.add(cbSid19);
        menuElements.add(cbSid19fix);
        menuElements.add(cbStar01);
        menuElements.add(cbStar01fix);
        menuElements.add(cbStar19);
        menuElements.add(cbStar19fix);
        menuElements.addSeparator();
        menuElements.add(cbPDR);
        menuElements.add(cbTSA);
        menuElements.add(cbTRA);
        menuElements.addSeparator();
        menuElements.add(cbTowns);
        menuElements.add(cbRivers);
        menuElements.add(cbRoads);

        //add action listener to every submenu of menuElements
        for (int i = 0; i < menuElements.getMenuComponentCount(); i++) {
            Component comp = menuElements.getMenuComponent(i);
            if (comp instanceof JCheckBoxMenuItem) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) comp;
                cb.addActionListener(this);

                // prevent menu from closing after click to mimic PEGASUS_21 behaviour
                // https://stackoverflow.com/questions/9198530/how-to-prevent-jmenuitem-from-closing-menu-upon-clicking-the-jmenuitem
                cb.setUI(new BasicCheckBoxMenuItemUI() {
                    @Override
                    protected void doClick(MenuSelectionManager msm) {
                        cb.doClick(0);
                    }
                });
            }
        }


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


        else if (e.getSource().equals(cbStar01) ||
                e.getSource().equals(cbStar19) ||
                e.getSource().equals(cbSid01) ||
                e.getSource().equals(cbSid19)) {

            //toggleSidStarVisibility((JCheckBoxMenuItem) e.getSource());
        }

        else if (e.getSource().equals(cbTma)) {
            //togglePolygonVisibility(cbTma, PolygonType.TMA);
        }
        else if  (e.getSource().equals(cbCtr)) {
            //togglePolygonVisibility(cbCtr, PolygonType.CTR);
        }
        else if (
                e.getSource().equals(cbTmaFixes) || e.getSource().equals(cbVfrFixes) ||
                e.getSource().equals(cbStar01fix) || e.getSource().equals(cbStar19fix) ||
                e.getSource().equals(cbSid01fix) || e.getSource().equals(cbSid19fix)) {
            //toggleFixVisibility((JCheckBoxMenuItem) e.getSource());
        }
        mapPanel.repaint();
    }

    private void toggleFixVisibility(JCheckBoxMenuItem source) {
        switch(source.getText()) {
            case "TMA entry fixes": {
                setFixVisible(FixType.ENTRY, source);
                break;
            }
            case "VFR fixes": {
                setFixVisible(FixType.VFR, source);
                break;
            }
            case "SID 01 fix": {
                setFixVisible(FixType.SID01, source);
                break;
            }
            case "SID 19 fix": {
                setFixVisible(FixType.SID19, source);
                break;
            }
            case "STAR 01 fix": {
                setFixVisible(FixType.STAR01, source);
                break;
            }
            case "STAR 19 fix": {
                setFixVisible(FixType.STAR19, source);
                break;
            }
        }

    }

    private void setFixVisible(FixType fixType, JCheckBoxMenuItem source) {
        for (Fix fix : airspace.getFixList()) {
            if (fix.getFixType() == fixType)
                fix.setVisible(source.isSelected());
        }
    }

    private void toggleSidStarVisibility(JCheckBoxMenuItem source) {

        switch (source.getText()) {
            case "SID 01": {
                for (Sid sid : airspace.getSids()) {
                    if (sid.getRunway() == Runway.RUNWAY_01)
                        sid.setVisible(source.isSelected());
                }
                break;
            }
            case "SID 19": {
                for (Sid sid : airspace.getSids()) {
                    if (sid.getRunway() == Runway.RUNWAY_19)
                        sid.setVisible(source.isSelected());
                }
                break;
            }
            case "STAR 01": {
                for (Star star : airspace.getStars()) {
                    if (star.getRunway() == Runway.RUNWAY_01)
                        star.setVisible(source.isSelected());
                }
                break;
            }
            case "STAR 19": {
                for (Star star : airspace.getStars()) {
                    if (star.getRunway() == Runway.RUNWAY_19)
                        star.setVisible(source.isSelected());
                }
                break;
            }
        }


        mapPanel.repaint();
    }

    private void togglePolygonVisibility(JCheckBoxMenuItem cb, PolygonType pt) {
        for (Polygon poly : airspace.getPolygonList()) {
            if (poly.getPolygonType() == pt) {
                poly.setVisible(cb.isSelected());
            }
        }
        mapPanel.repaint();
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
            menuClearRbls.setSelected(false);

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
