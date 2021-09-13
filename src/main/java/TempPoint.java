import javax.persistence.*;

@Entity
@Table(name="point")
public class TempPoint extends TempCoords {

    @Enumerated(EnumType.ORDINAL)
    @Column(name="polygon_id")
    private PolygonType polygon_id;

    @SuppressWarnings("unused")
    public TempPoint() {
    }

    public PolygonType getPolygon_id() {
        return polygon_id;
    }

    public void setPolygon_id(PolygonType polygon_id) {
        this.polygon_id = polygon_id;
    }

    @Override
    public String toString() {
        return "Point: [id=" + getId() + ", coordinates=" + getCoordinates() + ", PolygonType=" + polygon_id + "]";
    }
}
