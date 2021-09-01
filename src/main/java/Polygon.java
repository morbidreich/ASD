import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Fix> fixList;
    private final String name;
    private boolean isVisible = true;

    public void setFixList(List<Fix> fixList) {
        this.fixList = fixList;
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
        fixList = new ArrayList<>();
    }

    public Polygon(String name, List<Fix> fixList) {
        this.name = name;
        this.fixList = fixList;
    }

    public void addFix(Fix fix) {
        fixList.add(fix);
    }

    public List<Fix> getFixList() {
        return fixList;
    }

    public String getName() {
        return name;
    }
}
