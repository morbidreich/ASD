package io.github.morbidreich.airspaceElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="fix")
public class Fix extends BasePoint implements Cloneable {
    //i need cloneable for io.github.morbidreich.ui.search.SearchEngine - it clones object to
    //be able to modify isVisible field without modifying original io.github.morbidreich.airspaceElements.Fix

    @Column(name="fix_name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="fix_type_id")
    private FixType fixType;

    @Transient
    private boolean isVisible = false;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH}, mappedBy = "fixList")
//    @JoinTable(
//            name="procedure_fix",
//            joinColumns = @JoinColumn(name="fix_id"),
//            inverseJoinColumns = @JoinColumn(name="procedure_id"))
    private List<Procedure> procedureList;

    @SuppressWarnings("unused")
    public Fix() {}

    public Fix(Coordinates coordinates) {
        super(coordinates);
    }

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

    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    public void addProcedure(Procedure procedure) {
        if (procedureList == null)
            procedureList = new ArrayList<>();
        procedureList.add(procedure);
    }

    @Override
    public Object clone() {
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
        return "Fix: [id=" + getId() + ", name=" + name + ", lat/lon=" + getLatitude() + "/" + getLongitude() + ", fixType=" + fixType + "]";
    }
}
