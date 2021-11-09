package io.github.morbidreich;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener, MenuListener, MouseListener, ChangeListener, KeyListener {
    private Airspace airspace;
    private MapPanel mapPanel;

    private final JMenu menuOptions;
    private final JMenu menuElements;
    private final JMenu menuClearRbls;
    private final JMenu menuClose;
    private final JMenuBar menuBar;
    private final JMenuItem miSettings;
    private final asdCheckBox cbTma;
    private final asdCheckBox cbCtr;
    private final asdCheckBox cbTmaFixes;
    private final asdCheckBox cbAccFixes;
    private final asdCheckBox cbVfrFixes;
    private final asdCheckBox cbSid01;
    private final asdCheckBox cbSid19;
    private final asdCheckBox cbStar01;
    private final asdCheckBox cbStar19;
    private final asdCheckBox cbPDR;
    private final asdCheckBox cbTSA;
    private final asdCheckBox cbTRA;
    private final asdCheckBox cbAerodromes;
    private final asdCheckBox cbBorder;
    private final asdCheckBox cbTowns;
    private final asdCheckBox cbRivers;
    private final asdCheckBox cbRoads;
    private final JMenuItem miAbout;


    private JSlider sliderTma;
    private JSlider sliderCtr;
    private JSlider sliderTmaEntryFix;
    private JSlider sliderAccFix;
    private JSlider sliderVfrFix;
    private JSlider sliderSid01;
    private JSlider sliderSid19;
    private JSlider sliderStar01;
    private JSlider sliderStar19;
    private JSlider sliderTsa;
    private JSlider sliderBorder;
    private JSlider sliderAerodromes;
    private JSlider sliderTowns;

    public Menu(MapPanel mapPanel, Airspace airspace) {
        this.mapPanel = mapPanel;
        this.airspace = airspace;

        SearchTool searchTool = new SearchTool(airspace, mapPanel);


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
        menuBar.add(Box.createHorizontalStrut(400));
        menuBar.add(searchTool.getJlSearch());
        menuBar.add(searchTool.getJtfSearchText());
        menuBar.add(searchTool.getJbClear());

        miSettings = new JMenuItem("Settings");
        miAbout = new JMenuItem("About...");
        miAbout.addActionListener(this);

        menuOptions.add(miSettings);
        menuOptions.add(miAbout);


        sliderCtr = createJSlider("CTR", PolygonType.CTR, mapPanel);
        sliderTma = createJSlider("TMA", PolygonType.TMA, mapPanel);
        sliderTmaEntryFix = createJSlider("TMA entry fixes", FixType.ENTRY, mapPanel);
        sliderAccFix = createJSlider("Acc fixes", FixType.ACC_FIX, mapPanel);
        sliderVfrFix = createJSlider("VFR fixes", FixType.VFR, mapPanel);
        sliderSid01 = createJSlider("SID 01", FixType.SID01, mapPanel);
        sliderSid19 = createJSlider("SID 19", FixType.SID19, mapPanel);
        sliderStar01 = createJSlider("STAR 01", FixType.STAR01, mapPanel);
        sliderStar19 = createJSlider("STAR 19", FixType.STAR19, mapPanel);
        sliderTsa = createJSlider("TSA", PolygonType.TSA, mapPanel);
        sliderBorder = createJSlider("Border", PolygonType.BORDER, mapPanel);
        sliderAerodromes = createJSlider("Aerodromes", FixType.AERODROME, mapPanel);
        sliderTowns = createJSlider("Towns", FixType.TOWN, mapPanel);


        cbTma = new asdCheckBox("TMA", sliderTma);
        cbTma.addMouseListener(this);
        cbCtr = new asdCheckBox("CTR", sliderCtr);
        cbCtr.addMouseListener(this);
        cbTmaFixes = new asdCheckBox("TMA entry fixes", sliderTmaEntryFix);
        cbTmaFixes.addMouseListener(this);
        cbAccFixes = new asdCheckBox("ACC fixes", sliderAccFix);
        cbAccFixes.addMouseListener(this);
        cbVfrFixes = new asdCheckBox("VFR fixes", sliderVfrFix);
        cbVfrFixes.addMouseListener(this);
        cbSid01 = new asdCheckBox("SID 01", sliderSid01);
        cbSid01.addMouseListener(this);
        cbSid19 = new asdCheckBox("SID 19", sliderSid19);
        cbSid19.addMouseListener(this);
        cbStar01 = new asdCheckBox("STAR 01", sliderStar01);
        cbStar01.addMouseListener(this);
        cbStar19 = new asdCheckBox("STAR 19", sliderStar19);
        cbStar19.addMouseListener(this);
        cbPDR = new asdCheckBox("P/D/R", null);
        cbTSA = new asdCheckBox("TSA", sliderTsa);
        cbTSA.addMouseListener(this);
        cbTRA = new asdCheckBox("TRA", null);
        cbAerodromes = new asdCheckBox("Aerodromes", sliderAerodromes);
        cbAerodromes.addMouseListener(this);
        cbBorder = new asdCheckBox("Border", sliderBorder);
        cbBorder.addMouseListener(this);
        cbTowns = new asdCheckBox("Towns", sliderTowns);
        cbTowns.addMouseListener(this);
        cbRivers = new asdCheckBox("Rivers", null);
        cbRoads = new asdCheckBox("Roads", null);

        menuElements.add(cbTma);
        menuElements.add(sliderTma);
        menuElements.add(cbCtr);
        menuElements.add(sliderCtr);

        menuElements.addSeparator();
        menuElements.add(cbTmaFixes);
        menuElements.add(sliderTmaEntryFix);
        menuElements.add(cbAccFixes);
        menuElements.add(sliderAccFix);
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
        menuElements.add(sliderTowns);
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
        //cbTowns.setEnabled(false);
        cbPDR.setEnabled(false);
        //cbTSA.setEnabled(false);
        cbTRA.setEnabled(false);

        cbTma.setSelected(true);
        cbCtr.setSelected(true);
        cbTmaFixes.setSelected(true);
        cbBorder.setSelected(true);

    }

    //convenience method for creating JSlider items
    private JSlider createJSlider(String name, PolygonType pt, MapPanel mapPanel) {

        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setVisible(false);
        slider.setName(name);
        slider.setMaximum(123);
        slider.setValue(0);
        slider.setMinimum(-100);
        slider.setPreferredSize(new Dimension(60, 20));
        slider.setMinimumSize(new Dimension(60, 25));

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
        slider.setMinimum(-100);
        slider.setPreferredSize(new Dimension(60, 20));
        slider.setMinimumSize(new Dimension(60, 25));

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
                e.getSource().equals(cbTmaFixes) ||
                        e.getSource().equals(cbAccFixes) ||
                        e.getSource().equals(cbVfrFixes) ||
                        e.getSource().equals(cbAerodromes) ||
                        e.getSource().equals(cbTowns)) {
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
            case "ACC fixes": {
                setFixVisible(FixType.ACC_FIX, source);
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
            case "Towns": {
                setFixVisible(FixType.TOWN, source);
                break;
            }
        }
    }

    private void setFixVisible(FixType fixType, JCheckBoxMenuItem source) {
        airspace.getFixList().stream()
                .filter(f -> f.getFixType() == fixType)
                .forEach(f -> f.setVisible(true));
    }

    private void toggleProcedureVisibility(JCheckBoxMenuItem source) {

        switch (source.getText()) {
            case "SID 01": {

                airspace.getProcedureList().stream()
                        .filter(p -> p.getRunway() == Runway.RUNWAY_01 && p.getProcedureType() == ProcedureType.SID)
                        .forEach(p -> p.setVisibility(source.isSelected()));
                break;
            }
            case "SID 19": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_19 &&
                            p.getProcedureType() == ProcedureType.SID)
                        p.setVisibility(source.isSelected());
                }
                break;
            }
            case "STAR 01": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_01 &&
                            p.getProcedureType() == ProcedureType.STAR)
                        p.setVisibility(source.isSelected());
                }
                break;
            }
            case "STAR 19": {
                for (Procedure p : airspace.getProcedureList()) {
                    if (p.getRunway() == Runway.RUNWAY_19 &&
                            p.getProcedureType() == ProcedureType.STAR)
                        p.setVisibility(source.isSelected());
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
            if(SwingUtilities.isMiddleMouseButton(e)) {
                JSlider slider = cbTma.getSlider();
                slider.setVisible(!slider.isVisible());
                cbTma.setSelected(!cbTma.isSelected());
                togglePolygonVisibility(cbTma, PolygonType.TMA);
            }
        }

        if (e.getSource().equals(cbCtr)) {

            if (SwingUtilities.isMiddleMouseButton(e)) {
                sliderCtr.setVisible(!sliderCtr.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD

                cbCtr.setSelected(!cbCtr.isSelected());
                togglePolygonVisibility(cbCtr, PolygonType.CTR);//
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
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
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);

            }

        }

        if (e.getSource().equals(cbAccFixes)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderAccFix.setVisible(!sliderAccFix.isVisible());


                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleFixVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);

            }

        }

        if (e.getSource().equals(cbVfrFixes)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderVfrFix.setVisible(!sliderVfrFix.isVisible());


                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleFixVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);

            }

        }
        if (e.getSource().equals(cbSid01)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderSid01.setVisible(!sliderSid01.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleProcedureVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }

        }
        if (e.getSource().equals(cbSid19)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderSid19.setVisible(!sliderSid19.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleProcedureVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
        }
        if (e.getSource().equals(cbStar01)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderStar01.setVisible(!sliderStar01.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleProcedureVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
        }
        if (e.getSource().equals(cbStar19)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderStar19.setVisible(!sliderStar19.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleProcedureVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
        }
        if (e.getSource().equals(cbTSA)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderTsa.setVisible(!sliderTsa.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                togglePolygonVisibility(cb, PolygonType.TSA);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
        }
        if (e.getSource().equals(cbBorder)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderBorder.setVisible(!sliderBorder.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                togglePolygonVisibility(cb, PolygonType.BORDER);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
        }
        if (e.getSource().equals(cbAerodromes)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderAerodromes.setVisible(!sliderAerodromes.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleFixVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
        }

        if (e.getSource().equals(cbTowns)) {
            if (SwingUtilities.isMiddleMouseButton(e)) {
                JCheckBoxMenuItem cb = (JCheckBoxMenuItem) e.getSource();
                sliderTowns.setVisible(!sliderTowns.isVisible());

                // thats soooo messy. When detecting middle mouse click to show/hide brightness slider
                // what happens is that ActionEvent is also fired, repeatedly checking checkbox and
                // therefore disabling visibility of polygon. My fix for now is to, when ActionEvent fired,
                // reverse isSelected and polygon visibility. Works with ultra short flicker/glich of checkbox xD
                cb.setSelected(!cb.isSelected());
                toggleFixVisibility(cb);
                menuElements.setPopupMenuVisible(false);
                menuElements.setPopupMenuVisible(true);
            }
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
            menuElements.setPopupMenuVisible(false);
            menuElements.setPopupMenuVisible(true);
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

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        System.out.println(slider.getValue());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
