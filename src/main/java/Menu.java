import org.h2.mvstore.tx.TransactionStore;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private final JCheckBoxMenuItem cbSid19;
    private final JCheckBoxMenuItem cbStar01;
    private final JCheckBoxMenuItem cbStar19;
    private final JCheckBoxMenuItem cbPDR;
    private final JCheckBoxMenuItem cbTSA;
    private final JCheckBoxMenuItem cbTRA;
    private final JCheckBoxMenuItem cbAerodromes;
    private final JCheckBoxMenuItem cbBorder;
    private final JCheckBoxMenuItem cbTowns;
    private final JCheckBoxMenuItem cbRivers;
    private final JCheckBoxMenuItem cbRoads;
    private final JMenuItem miAbout;


    private JSlider sliderTma;
    private JSlider sliderCtr;
    private JSlider sliderTmaEntryFix;
    private JSlider sliderVfrFix;
    private JSlider sliderSid01;
    private JSlider sliderSid19;
    private JSlider sliderStar01;
    private JSlider sliderStar19;
    private JSlider sliderTsa;
    private JSlider sliderBorder;
    private JSlider sliderAerodromes;

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

        JTextField jtfSearchText = new JTextField();
        jtfSearchText.setSize(new Dimension(100, 30));
        jtfSearchText.setMinimumSize(new Dimension(100, 30));
        JLabel jlSearch = new JLabel("Search: ");
        JButton jbClear = new JButton("Clear results");


        menuBar.add(menuElements);
        menuBar.add(menuClearRbls);
        menuBar.add(menuOptions);
        menuBar.add(menuClose);
        menuBar.add(Box.createHorizontalStrut(400));
        menuBar.add(jlSearch);
        //menuBar.add(Box.createHorizontalStrut(400));
        menuBar.add(jtfSearchText);
        menuBar.add(jbClear);

        miSettings = new JMenuItem("Settings");
        miAbout = new JMenuItem("About...");
        miAbout.addActionListener(this);

        menuOptions.add(miSettings);
        menuOptions.add(miAbout);


        sliderCtr = createJSlider("CTR", PolygonType.CTR, mapPanel);
        sliderTma = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderTmaEntryFix = createJSlider("TMA entry fixes", FixType.ENTRY, mapPanel);
        sliderVfrFix = createJSlider("VFR fixes", FixType.VFR, mapPanel);
        sliderSid01 = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderSid19 = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderStar01 = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderStar19 = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderTsa = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderBorder = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderAerodromes = createJSlider("TMA", PolygonType.TMA, mapPanel);


        cbTma = new JCheckBoxMenuItem("TMA");
        cbTma.addMouseListener(this);
        cbCtr = new JCheckBoxMenuItem("CTR");
        cbCtr.addMouseListener(this);
        cbTmaFixes = new JCheckBoxMenuItem("TMA entry fixes");
        cbTmaFixes.addMouseListener(this);
        cbVfrFixes = new JCheckBoxMenuItem("VFR fixes");
        cbVfrFixes.addMouseListener(this);
        cbSid01 = new JCheckBoxMenuItem("SID 01");
        cbSid01.addMouseListener(this);
        cbSid19 = new JCheckBoxMenuItem("SID 19");
        cbSid19.addMouseListener(this);
        cbStar01 = new JCheckBoxMenuItem("STAR 01");
        cbStar01.addMouseListener(this);
        cbStar19 = new JCheckBoxMenuItem("STAR 19");
        cbStar19.addMouseListener(this);
        cbPDR = new JCheckBoxMenuItem("P/D/R");
        cbTSA = new JCheckBoxMenuItem("TSA");
        cbTSA.addMouseListener(this);
        cbTRA = new JCheckBoxMenuItem("TRA");
        cbAerodromes = new JCheckBoxMenuItem("Aerodromes");
        cbAerodromes.addMouseListener(this);
        cbBorder = new JCheckBoxMenuItem("Border");
        cbBorder.addMouseListener(this);
        cbTowns = new JCheckBoxMenuItem("Towns");
        cbRivers = new JCheckBoxMenuItem("Rivers");
        cbRoads = new JCheckBoxMenuItem("Roads");

        menuElements.add(cbTma);
        menuElements.add(sliderTma);
        menuElements.add(cbCtr);
        menuElements.add(sliderCtr);

//        sliderTmaEntryFix
//                sliderVfrFix
//        sliderSid01
//                sliderSid19
//        sliderStar01
//                sliderStar19
//        sliderTsa
//                sliderBorder
//        sliderAerodromes

        menuElements.addSeparator();
        menuElements.add(cbTmaFixes);
        menuElements.add(sliderTmaEntryFix);
        menuElements.add(cbVfrFixes);
        menuElements.add(sliderVfrFix);
        menuElements.addSeparator();
        menuElements.add(cbSid01);
        menuElements.add(sliderSid01);
        menuElements.add(cbSid19);
        menuElements.add(sliderSid19);
        menuElements.add(cbStar01);
        menuElements.add(sliderStar01);
        menuElements.add(cbStar19);
        menuElements.add(sliderStar19);
        menuElements.addSeparator();
        menuElements.add(cbPDR);
        menuElements.add(cbTSA);
        menuElements.add(sliderTsa);
        menuElements.add(cbTRA);
        menuElements.addSeparator();
        menuElements.add(cbBorder);
        menuElements.add(sliderBorder);
        menuElements.add(cbAerodromes);
        menuElements.add(sliderAerodromes);
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
        //cbTSA.setEnabled(false);
        cbTRA.setEnabled(false);

        cbTma.setSelected(true);
        cbCtr.setSelected(true);

    }

    //convenience method for creating JSlider items
    private JSlider createJSlider(String name, PolygonType pt, MapPanel mapPanel) {

        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setVisible(false);
        slider.setName(name);
        slider.setMaximum(123);
        slider.setValue(0);
        slider.setMinimum(-122);
        slider.setPreferredSize(new Dimension(120, 20));

        BrightnessSlider bs = new BrightnessSlider(pt, mapPanel);
        slider.addChangeListener(bs);

        return slider;
    }

    private JSlider createJSlider(String name, FixType ft, MapPanel mapPanel) {

        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setVisible(false);
        slider.setName(name);
        slider.setMaximum(123);
        slider.setValue(0);
        slider.setMinimum(-122);
        slider.setPreferredSize(new Dimension(120, 20));

        BrightnessSlider bs = new BrightnessSlider(ft, mapPanel);
        slider.addChangeListener(bs);

        return slider;
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

        } else if (e.getSource().equals(cbStar01) ||
                e.getSource().equals(cbStar19) ||
                e.getSource().equals(cbSid01) ||
                e.getSource().equals(cbSid19)) {

            toggleProcedureVisibility((JCheckBoxMenuItem) e.getSource());

        } else if (e.getSource().equals(cbTma)) {
            togglePolygonVisibility(cbTma, PolygonType.TMA);
        } else if (e.getSource().equals(cbCtr)) {
            togglePolygonVisibility(cbCtr, PolygonType.CTR);
        } else if (e.getSource().equals(cbTSA)) {
            togglePolygonVisibility(cbTSA, PolygonType.TSA);
        } else if (e.getSource().equals(cbBorder)) {
            togglePolygonVisibility(cbBorder, PolygonType.BORDER);
        } else if (
                e.getSource().equals(cbTmaFixes) || e.getSource().equals(cbVfrFixes) ||
                        e.getSource().equals(cbAerodromes)) {
            toggleFixVisibility((JCheckBoxMenuItem) e.getSource());
        }
        mapPanel.repaint();
    }

    private void toggleFixVisibility(JCheckBoxMenuItem source) {
        switch (source.getText()) {
            case "TMA entry fixes": {
                setFixVisible(FixType.ENTRY, source);
                break;
            }
            case "VFR fixes": {
                setFixVisible(FixType.VFR, source);
                break;
            }
            case "Aerodromes": {
                setFixVisible(FixType.AERODROME, source);
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

    private void toggleProcedureVisibility(JCheckBoxMenuItem source) {

        switch (source.getText()) {
            case "SID 01": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_01 &&
                            p.getProcedureType() == ProcedureType.SID)
                        p.setVisible(source.isSelected());
                }
                break;
            }
            case "SID 19": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_19 &&
                            p.getProcedureType() == ProcedureType.SID)
                        p.setVisible(source.isSelected());
                }
                break;
            }
            case "STAR 01": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_01 &&
                            p.getProcedureType() == ProcedureType.STAR)
                        p.setVisible(source.isSelected());
                }
                break;
            }
            case "STAR 19": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_19 &&
                            p.getProcedureType() == ProcedureType.STAR)
                        p.setVisible(source.isSelected());
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

        if (e.getSource().equals(cbTma)) {

            HandleTma(e);
        }
        if (e.getSource().equals(cbCtr)) {

            System.out.println("Ctr CLICKED");
            if (SwingUtilities.isMiddleMouseButton(e)) {
                sliderCtr.setVisible(!sliderCtr.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD

                cbCtr.setSelected(!cbCtr.isSelected());
                togglePolygonVisibility(cbCtr, PolygonType.CTR);//
            }
        }

        if (e.getSource().equals(cbTmaFixes)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderTmaEntryFix.setVisible(!sliderTmaEntryFix.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleFixVisibility(cb);

            }

        }

        if (e.getSource().equals(cbVfrFixes)) {

        }
        if (e.getSource().equals(cbSid01)) {

        }
        if (e.getSource().equals(cbSid19)) {

        }
        if (e.getSource().equals(cbStar01)) {

        }
        if (e.getSource().equals(cbStar19)) {

        }
        if (e.getSource().equals(cbTSA)) {

        }
        if (e.getSource().equals(cbBorder)) {

        }
        if (e.getSource().equals(cbAerodromes)) {

        }

    }

    private void HandleTma(MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
            sliderTma.setVisible(!sliderTma.isVisible());

            // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
            // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
            // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
            // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
            cb.setSelected(!cb.isSelected());
            togglePolygonVisibility(cb, PolygonType.TMA);


            if (SwingUtilities.isMiddleMouseButton(e)) {

                if (e.getSource().equals(cbCtr)) {
                    System.out.println("Middle click on");
                }
            }
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
