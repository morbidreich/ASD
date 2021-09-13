import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "polygon")
public class TempPolygon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "polygon_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "polygon_id")
    private List<TempPoint> pointList;

    @Transient
    private boolean isVisible = true;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="polygon_type_id")
    private PolygonType polygonType = PolygonType.UNDEFINED;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPointList(List<TempPoint> pointList) {
        this.pointList = pointList;
    }

    public List<TempPoint> getPointList() {
        return pointList;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public PolygonType getPolygonType() {
        return polygonType;
    }

    public void setPolygonType(PolygonType polygonType) {
        this.polygonType = polygonType;
    }

    @SuppressWarnings("unused") // hibernate needs it
    public TempPolygon() {
    }


    public TempPolygon(String name) {
        this.name = name;
        pointList = new ArrayList<>();
    }

    public TempPolygon(String name, List<TempPoint> pointList) {
        this.name = name;
        this.pointList = pointList;
    }

    public void addPoint(TempPoint point) {
        pointList.add(point);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Polygon: [id=" + id + ", name=" + name + ", type=" + polygonType + "]";
    }
}
