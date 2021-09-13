import javax.persistence.*;

@Entity
@Table(name="fix")
public class TempFix extends TempCoords {

    @Column(name="fix_name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="fix_type_id")
    private FixType fixType;

    @SuppressWarnings("unused")
    public TempFix() {}

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

    @Override
    public String toString() {
        return "Fix: [id=" + getId() + ", name=" + name + ", coordinates=" + getCoordinates() + ", fixType=" + fixType + "]";
    }
}
