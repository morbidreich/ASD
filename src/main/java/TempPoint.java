import javax.persistence.*;

@Entity
@Table(name="point")
public class TempPoint extends TempCoords {

    @Column(name="polygon_id")
    private int polygon_id;

    @SuppressWarnings("unused")
    public TempPoint() {
    }

    public int getPolygon_id() {
        return polygon_id;
    }

    public void setPolygon_id(int polygon_id) {
        this.polygon_id = polygon_id;
    }

    @Override
    public String toString() {
        return "Point: [id=" + getId() + ", lat/lon=" + getLatitude() + "/" + getLongitude() + "]";
    }
}
