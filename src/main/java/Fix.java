import javax.persistence.*;

@Entity
@Table(name="fixes")
public class Fix extends Point {

    @Column(name="fix_name")
    private String name;


    @Transient
    private boolean isVisible = false;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="fix_type_id")
    private FixType fixType = FixType.UNDEFINED;

    public Fix(String name, Coordinates coords, FixType fixType) {
        super(coords.getLatitude(), coords.getLongitude());
        this.fixType = fixType;
        this.name = name;
    }

    @SuppressWarnings("unused") // for hibernate
    public Fix() {

    }
    public Fix(String name, double latitude, double longitude, FixType fixType) {
        super(latitude, longitude);
        this.name = name;
        this.fixType = fixType;
    }


    public String getName() { return name; }


    @SuppressWarnings("unused") // hibernate
    public void setName(String name) {
        this.name = name;
    }

    public FixType getFixType() {
        return fixType;
    }

    @SuppressWarnings("unused") // hibernate
    public void setFixType(FixType fixType) {
        this.fixType = fixType;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public String toString() {
        return "Fix: [id=" + super.getId() + ", name=" + name + ", fixType=" + fixType + "]";
    }
}
