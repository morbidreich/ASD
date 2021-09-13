import javax.persistence.*;

@Entity
@Table(name="fix")
public class Fix extends BasePoint {

    @Column(name="fix_name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="fix_type_id")
    private FixType fixType;

    @Transient
    private boolean isVisible = false;

    @SuppressWarnings("unused")
    public Fix() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FixType getFixType() {
        return fixType;
    }

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
        return "Fix: [id=" + getId() + ", name=" + name + ", lat/lon=" + getLatitude() + "/" + getLongitude() + ", fixType=" + fixType + "]";
    }
}
