import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Fix> fixList;
    private final String name;
    private boolean isVisible = true;

    public Polygon(String name) {
        this.name = name;
        fixList = new ArrayList<>();
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
