import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private List<Fix> fixList;
    private List<Polygon> polygonList;
    private List<Procedure> procedureList;

    public SearchResult() {
        fixList = new ArrayList<>();
        polygonList = new ArrayList<>();
        procedureList = new ArrayList<>();

    }

    public void clear() {
        fixList.clear();
        polygonList.clear();
        procedureList.clear();
    }

    public List<Fix> getFixList() {
        return fixList;
    }

    public void setFixList(List<Fix> fixList) {
        this.fixList = fixList;
    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }

    public void setPolygonList(List<Polygon> polygonList) {
        this.polygonList = polygonList;
    }

    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    @Override
    public String toString() {
        return "SearchResult: [Fixes: " +
                fixList.size() + ", polygons: " +
                polygonList.size() + ", procedures: " +
                procedureList.size() + "]\n" +
                fixList + "\n" + polygonList + "\n" + procedureList;
    }
}
