package io.github.morbidreich.ui;/*
  code by Cristopher Jacquet with my my slight modifications
  https://github.com/ChristopheJacquet/Minigeo
 */

import io.github.morbidreich.airspaceElements.*;
import io.github.morbidreich.airspaceElements.Point;
import io.github.morbidreich.airspaceElements.Polygon;
import io.github.morbidreich.ui.drawing.FixSymbolDrawer;
import io.github.morbidreich.ui.drawing.TrackDrawer;
import io.github.morbidreich.surveilance.Track;
import io.github.morbidreich.ui.search.SearchResult;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.utils.Colors;
import io.github.morbidreich.utils.CoordinateConverter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {

    protected List<Track> tracks = new ArrayList<>();

    private List<Polygon> polygons = new ArrayList<Polygon>();
    private List<Fix> fixes = new ArrayList<Fix>();
    private final List<Procedure> procedures = new ArrayList<>();
    protected final List<RBL> rbls = new ArrayList<RBL>();
    private final Colors colors = new Colors();
    private SearchResult searchResult = new SearchResult();

    protected int cursorX, cursorY = 0;

    private double minEasting, maxEasting, minNorthing, maxNorthing;

    private double oEasting, oNorthing;        // coordinates of the origin
    private double scale = -1.0;

    public MapPanel() {
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1000, 800));

        resetMinMaxEastingNorthing();
        setDefaultElementsVisibility();

        setDefaultViewPoint();

        addMouseWheelListener(new MapPanelMouseWheelListener(this));

        MapPanelMouseListener mouseListener = new MapPanelMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    public void setDefaultElementsVisibility() {
        polygons = polygons.stream()
                .peek(p -> {
                    if (p.getPolygonType() == PolygonType.CTR ||
                            p.getPolygonType() == PolygonType.TMA ||
                            p.getPolygonType() == PolygonType.BORDER)
                        p.setVisible(true);
                })
                .collect(Collectors.toList());

        fixes = fixes.stream().peek(f -> {
                    if (f.getFixType() == FixType.ENTRY)
                        f.setVisible(true);
                })
                .collect(Collectors.toList());
    }

    private void setDefaultViewPoint() {
        //create two fixes that will keep map focused on EPSY_TMA
        Coordinates c1 = CoordinateConverter.getFromDMS("53°40'00\"N 019°48'00\"E");
        Coordinates c2 = CoordinateConverter.getFromDMS("53°10'00\"N 021°24'00\"E");
        Fix f1 = new Fix(c1);
        Fix f2 = new Fix(c2);
        updateMinMaxEastingNorthing(f1);
        updateMinMaxEastingNorthing(f2);
    }

    public List<RBL> getRbls() {
        return rbls;
    }

    @Override
    protected synchronized void paintComponent(Graphics g_) {
        super.paintComponent(g_);

        Graphics2D g = (Graphics2D) g_;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_SPEED);

        int w = getWidth();
        int h = getHeight();

        g.setColor(colors.BACKGROUND_COLOR);
        g.fillRect(0, 0, w, h);

        if (polygons.size() == 0) return;
        if (this.scale == -1) scale();


        drawPolygons(polygons, g, h);
        drawProcedures(procedures, g, h);
        drawFixes(fixes, g, h);
        drawRBLs(g, h);
        drawScale(g);

        // example arcs drawn as needed by initial segment of ILS approach, may be usefull in future
        // DO NOT DELETE
        // Fix syn = fixes.stream().filter(f -> f.getName().equals("SYN")).findFirst().get();
        // Fix mra = fixes.stream().filter(f -> f.getName().equals("MRA")).findFirst().get();
        // drawArc(g, syn, 19.4, 215, 199);
        // drawArc(g, mra, 30.6, 208, 198);

        //next to last in order to display results on top of other layers
        drawSearchResults(g, h);

        //and airplane tracks topmost
        drawTracks(g);
    }

    /**
     * this method draws arc around Fix at specified distance in NM, restricted by specified radials
     * @param g graphics
     * @param fix fix
     * @param arcDistanceNM radius of arc in NM
     * @param radial1 radial with greater value
     * @param radial2 radial with smaller value
     */
    private void drawArc(Graphics2D g, Fix fix, double arcDistanceNM, int radial1, int radial2) {
        int radius = (int) (arcDistanceNM * 1.852 * scale); // distNM * convert to km * scale
        int X = convertX(fix.getEasting()) - radius;
        int Y = convertY(fix.getNorthing(), getHeight()) - radius;
        g.setColor(Color.white);
        g.drawArc(X, Y, radius * 2, radius * 2, 360 - radial1 + 90 - AppSettings.MAGNETIC_VARIATION,Math.abs(radial1 - radial2));
    }

    private synchronized void drawTracks(Graphics2D g) {
        tracks.forEach(t -> TrackDrawer.drawTrack(t, g, this));
    }

    private void drawScale(Graphics2D g) {
        g.setColor(Color.GREEN);

        // unit is the unit of the scale. It must be a power of ten, such that unit * scale in [25, 250]
        double unit = Math.pow(10, Math.ceil(Math.log10(25 / scale)));
        String strUnit;
        if (unit >= 1) strUnit = ((int) unit) + " km";
        else strUnit = ((int) (1000 * unit)) + " m";
        g.drawString(strUnit + " \u2194 " + ((int) (unit * scale)) + " px", 10, 10 + g.getFontMetrics().getHeight());
        // draw a 1-kilometer segment
        for (int i = 6; i <= 9; i++) {
            g.drawLine(10, i, 10 + (int) (unit * scale * (i < 8 ? 1 : .5)), i);
        }
    }

    private synchronized void drawRBLs(Graphics2D g, int h) {
        g.setColor(colors.RBL_COLOR);
        for (Iterator<RBL> iterator = rbls.iterator(); iterator.hasNext(); ) {

            RBL rbl = iterator.next();

            if (rbl.getStartPoint() == null || rbl.getEndPoint() == null) {
                iterator.remove();
                continue;
            }

            BasePoint startPoint = rbl.getStartPoint();
            BasePoint endPoint = rbl.getEndPoint();

            int x1 = convertX(endPoint.getEasting());
            int y1 = convertY(endPoint.getNorthing(), h);

            int x2 = convertX(startPoint.getEasting());
            int y2 = convertY(startPoint.getNorthing(), h);

            g.drawLine(x1, y1, x2, y2);

            rbl.drawLabel(x1, y1, x2, y2, scale, g);
        }
    }

    private void drawPolygons(List<Polygon> polygonList, Graphics2D g, int h) {
        for (Polygon poly : polygonList) {
            if (poly.isVisible()) {

                List<io.github.morbidreich.airspaceElements.Point> pointList = poly.getPointList();

                for (int i = 0; i < pointList.size() - 1; i++) {
                    g.setColor(colors.getColor(poly.getPolygonType()));

                    g.drawLine(
                            convertX(pointList.get(i).getEasting()), convertY(pointList.get(i).getNorthing(), h),
                            convertX(pointList.get(i + 1).getEasting()), convertY(pointList.get(i + 1).getNorthing(), h));
                }
            }
        }
    }

    private void drawFixes(List<Fix> fixes, Graphics2D g, int h) {
        for (Fix fix : fixes) {

            if (fix.isVisible()) {
                g.setColor(colors.getColor(fix.getFixType()));
                int x = convertX(fix.getEasting());
                int y = convertY(fix.getNorthing(), h);
                FixSymbolDrawer.drawFixSymbol(x, y, g, fix.getFixType());
                g.drawString(fix.getName(), x + 10, y + 2);
            }
        }
    }

    private void drawProcedures(List<Procedure> procedures, Graphics2D g, int h) {
        for (Procedure procedure : procedures) {
            if (procedure.isVisible()) {
                List<Fix> fixList = procedure.getFixList();
                for (int i = 0; i < fixList.size() - 1; i++) {
                    //that's awful, did that to avoid creating another getColor for procedureType
                    g.setColor(colors.getColor(procedure.getFixList().get(1).getFixType()));

                    g.drawLine(
                            convertX(fixList.get(i).getEasting()), convertY(fixList.get(i).getNorthing(), h),
                            convertX(fixList.get(i + 1).getEasting()), convertY(fixList.get(i + 1).getNorthing(), h));
                }
            }
        }
    }

    private void drawSearchResults(Graphics2D g, int h) {
        drawFixes(searchResult.getFixList(), g, h);
        drawPolygons(searchResult.getPolygonList(), g, h);
        drawProcedures(searchResult.getProcedureList(), g, h);

        for (Procedure p : searchResult.getProcedureList()) {
            drawFixes(p.getFixList(), g, h);
        }
    }

    public synchronized void addFix(Fix fix) {
        this.fixes.add(fix);

        //updateMinMaxEastingNorthing(fix);
    }

    public void addFixes(Collection<Fix> fixes) {
        for (Fix fix : fixes) addFix(fix);
    }

    public synchronized void addProcedure(Procedure procedure) {
        this.procedures.add(procedure);
    }

    public void addProcedures(List<Procedure> procedureList) {
        for (Procedure proc : procedureList) addProcedure(proc);
    }

    public synchronized void addPolygon(Polygon poly) {
        this.polygons.add(poly);

        List<Point> pointList = poly.getPointList();

        for (BasePoint point : pointList) {
            //updateMinMaxEastingNorthing(point);
        }
    }

    public void addPolygons(List<Polygon> polygons) {
        for (Polygon poly : polygons) addPolygon(poly);
    }

    public synchronized void setTracks(List<Track> tracks) {
        this.tracks = tracks;
        repaint();
    }


    public synchronized void addRBL(RBL rbl) {
        this.rbls.add(rbl);

        //updateMinMaxEastingNorthing(rbl.getStartPoint());
        //updateMinMaxEastingNorthing(rbl.getEndPoint());
    }

    private synchronized void updateMinMaxEastingNorthing(BasePoint point) {
        double easting = point.getEasting();
        if (easting > maxEasting) maxEasting = easting;
        if (easting < minEasting) minEasting = easting;

        double northing = point.getNorthing();
        if (northing > maxNorthing) maxNorthing = northing;
        if (northing < minNorthing) minNorthing = northing;
    }

    private synchronized void resetMinMaxEastingNorthing() {
        minEasting = Double.MAX_VALUE;
        maxEasting = Double.MIN_VALUE;
        minNorthing = Double.MAX_VALUE;
        maxNorthing = Double.MIN_VALUE;

        this.scale = -1.0;
    }

    private synchronized void scale() {
        int w = getWidth();
        int h = getHeight();

        this.scale = Math.min(
                w / (maxEasting - minEasting),
                h / (maxNorthing - minNorthing));

        oEasting = minEasting;
        oNorthing = minNorthing;
    }

    private int applyScale(double km) {
        return (int) (km * scale);
    }

    public int convertX(double easting) {
        return applyScale(easting - oEasting);
    }

    public int convertY(double northing, int height) {
        return height - applyScale(northing - oNorthing);
    }


    protected double convertEasting(int x) {
        double xd = x;

        return xd / scale + oEasting;
    }

    protected double convertNorthing(int y) {
        double h = getHeight();
        double yd = y;

        return (h - yd) / scale + oNorthing;
    }

    public Colors getColors() {
        return colors;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getoEasting() {
        return oEasting;
    }

    public void setoEasting(double oEasting) {
        this.oEasting = oEasting;
    }

    public double getoNorthing() {
        return oNorthing;
    }

    public void setoNorthing(double oNorthing) {
        this.oNorthing = oNorthing;
    }

    public void addAirspace(Airspace airspace) {
        addPolygons(airspace.getPolygonList());
        addFixes(airspace.getFixList());
        addProcedures(airspace.getProcedureList());

        addPolygon(Airport.getRunwayPolygon());
    }

    protected boolean tryDeleteRBL(MouseEvent e) {
        boolean out = false;

        for (Iterator<RBL> iterator = rbls.iterator(); iterator.hasNext(); ) {
            RBL rbl = iterator.next();
            if (rbl.labelClicked(e)) {
                iterator.remove();
                out = true;
            }
        }
        repaint();
        return out;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
        repaint();
    }
}
