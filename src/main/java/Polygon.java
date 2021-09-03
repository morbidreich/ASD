import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Point> pointList;
    private final String name;
    private boolean isVisible = true;

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    private PolygonType polygonType = PolygonType.UNDEFINED;

    public PolygonType getPolygonType() {
        return polygonType;
    }

    public void setPolygonType(PolygonType polygonType) {
        this.polygonType = polygonType;
    }


    public Polygon(String name) {
        this.name = name;
        pointList = new ArrayList<>();
    }

    public Polygon(String name, List<Point> pointList) {
        this.name = name;
        this.pointList = pointList;
    }

    public void addPoint(Point point) {
        pointList.add(point);
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public String getName() {
        return name;
    }
}
