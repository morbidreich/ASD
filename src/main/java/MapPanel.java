/*
  code by Cristopher Jacquet with my my slight modifications
  https://github.com/ChristopheJacquet/Minigeo
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
class MapPanel extends JPanel {
    private final List<Polygon> polygons = new ArrayList<Polygon>();
    private final List<Fix> fixes = new ArrayList<Fix>();
    private final List<Procedure> procedures = new ArrayList<>();
    private final List<RBL> rbls = new ArrayList<RBL>();
    private Colors colors = new Colors();
    private SearchResult searchResult = new SearchResult();

    private boolean drawingRBL = false;

    private int cursorX, cursorY = 0;

    private double minEasting, maxEasting, minNorthing, maxNorthing;

    private double oEasting, oNorthing;        // coordinates of the origin
    private double scale = -1;

    public MapPanel() {
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1000, 800));

        resetMinMaxEastingNorthing();
        setDefaultElementsVisibility();

        setDefaultViewPoint();

        addMouseWheelListener(new MouseWheelZoomer());

        MousePanner mousePanner = new MousePanner();
        addMouseListener(mousePanner);
        addMouseMotionListener(mousePanner);
    }

    public void setDefaultElementsVisibility() {
        for (Polygon p : polygons)
            if (p.getPolygonType() == PolygonType.CTR ||
                p.getPolygonType() == PolygonType.TMA)
                p.setVisible(true);
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
        //last in order to display results on top
        drawSearchResults(g, h);
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

    private void drawRBLs(Graphics2D g, int h) {
        g.setColor(colors.RBL_COLOR);
        for (RBL rbl : rbls) {
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

                List<Point> pointList = poly.getPointList();

                for (int i = 0; i < pointList.size() - 1; i++) {
                    g.setColor(colors.getColor(poly.getPolygonType()));

                    g.drawLine(
                            convertX(pointList.get(i).getEasting()), convertY(pointList.get(i).getNorthing(), h),
                            convertX(pointList.get(i + 1).getEasting()), convertY(pointList.get(i + 1).getNorthing(), h));

                    //updateMinMaxEastingNorthing(pointList.get(i));
                    //updateMinMaxEastingNorthing(pointList.get(i + 1));
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
                FixSymbolDrawer.drawFixSymbol(x, y, g, fix);
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
    }

    public synchronized void clear() {
        this.fixes.clear();
        this.polygons.clear();
        this.rbls.clear();
        this.searchResult.clear();

        resetMinMaxEastingNorthing();
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

        this.scale = -1;
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

    private int convertX(double easting) {
        return applyScale(easting - oEasting);
    }

    private int convertY(double northing, int height) {
        return height - applyScale(northing - oNorthing);
    }


    private double convertEasting(int x) {
        double w = getWidth();
        double xd = x;

        return xd / scale + oEasting;
    }

    private double convertNorthing(int y) {
        double h = getHeight();
        double yd = y;

        return (h - yd) / scale + oNorthing;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public void addAirspace(Airspace airspace) {
        addPolygons(airspace.getPolygonList());
        addFixes(airspace.getFixList());
        addProcedures(airspace.getProcedureList());

        addPolygon(Airport.getRunwayPolygon());
    }

    class MouseWheelZoomer implements MouseWheelListener {
        private static final double zoomFactor = .05;

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            double oldScale = scale;

            int rotation = e.getWheelRotation();
            if (rotation > 0) {
                scale /= (1 + rotation * zoomFactor);
            } else {
                scale *= (1 - rotation * zoomFactor);
            }

            // When zooming, the easting/northing at the cursor position must
            // remain the same, so we have to pan in addition to changing the
            // scale. The maths for easting (same goes for northing):
            //
            // before: x = (easting - oEasting) * oldScale
            // after: x = (easting - newOEasting) * scale
            //
            // (x remains the same, easting remains the same)
            //
            // hence: newOEasting = easting - (easting - oEasting) * oldScale / scale
            // with: easting = x/scale + oEasting
            // hence finally: newOEasting = oEasting + x * (1/oldScale - 1/scale)
            int x = e.getX();
            int y = e.getY();
            int h = getHeight();

            oEasting = oEasting + x * (1 / oldScale - 1 / scale);
            oNorthing = oNorthing + (h - y) * (1 / oldScale - 1 / scale);

            //System.out.println(rotation + " => " + scale);
            repaint();
        }
    }

    private class MousePanner implements MouseListener, MouseMotionListener {
        private int dragOriginX, dragOriginY;
        private double dragOriginOEasting, dragOriginONorthing;

        @Override
        public void mousePressed(MouseEvent e) {

            if (SwingUtilities.isLeftMouseButton(e)) {
                dragOriginX = e.getX();
                dragOriginY = e.getY();
                dragOriginOEasting = oEasting;
                dragOriginONorthing = oNorthing;
            } else if (SwingUtilities.isRightMouseButton(e)) {
                if (!drawingRBL) {
                    // detect if clicked inside RBL label. If so then delete clicked RBL
                    // if not draw another RBL

                    if (tryDeleteRBL(e))
                        return;

                    RBL rbl = new RBL(new BasePoint(), new BasePoint());
                    rbls.add(rbl);
                    BasePoint startPoint = new BasePoint(); //convertNorthing(e.getY()), convertEasting(e.getX()));
                    startPoint.setNorthing(convertNorthing(e.getY()));
                    startPoint.setEasting(convertEasting(e.getX()));

                    BasePoint endPoint = new BasePoint();   //convertNorthing(e.getY()), convertEasting(e.getX()));
                    endPoint.setNorthing(convertNorthing(e.getY()));
                    endPoint.setEasting(convertEasting(e.getX()));

                    rbl.setStartPoint(startPoint);
                    rbl.setEndPoint(endPoint);
                    drawingRBL = true;
                } else {
                    drawingRBL = false;
                }
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if (SwingUtilities.isLeftMouseButton(e)) {
                int deltaX = e.getX() - dragOriginX;
                int deltaY = e.getY() - dragOriginY;

                oEasting = dragOriginOEasting - deltaX / scale;
                oNorthing = dragOriginONorthing + deltaY / scale;

                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            if (drawingRBL) {
                cursorX = e.getX();
                cursorY = e.getY();

                rbls.get(rbls.size() - 1).getEndPoint().setNorthing(convertNorthing(cursorY));
                rbls.get(rbls.size() - 1).getEndPoint().setEasting(convertEasting(cursorX));

                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
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

    private boolean tryDeleteRBL(MouseEvent e) {
        boolean out = false;
        for (int i = 0; i < rbls.size(); i++) {
            if (rbls.get(i).labelClicked(e)) {
                rbls.remove(i);
                System.out.println("label nr " + i + " clicked");
                out = true;
            }
        }
        repaint();
        return out;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }
}
