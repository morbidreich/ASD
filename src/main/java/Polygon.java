import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "polygon")
public class Polygon implements Cloneable{
    //i need cloneable for SearchEngine - it clones object to
    //be able to modify isVisible field without modifying original Polygon

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "polygon_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "polygon_id")
    private List<Point> pointList;

    @Transient
    private boolean isVisible = false;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="polygon_type_id")
    private PolygonType polygonType = PolygonType.UNDEFINED;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public List<Point> getPointList() {
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
    public Polygon() {
    }

    public void addPoint(Point point) {
        if (pointList == null)
            pointList = new ArrayList<>();
        pointList.add(point);
    }
    public void addPoints(List<Point> pointList) {
        for (Point p : pointList) addPoint(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error cloning fix");
            e.printStackTrace();
            return this;
        }
    }

    @Override
    public String toString() {
        return "Polygon: [id=" + id + ", name=" + name + ", type=" + polygonType + "]";
    }
}
