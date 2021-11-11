package io.github.morbidreich.airspaceElements;

import java.util.*;

public class Airspace {

    private List<Fix> fixList = new ArrayList<>();
    private List<Polygon> polygonList = new ArrayList<>();
    private List<Procedure> procedureList = new ArrayList<>();

    public Airspace() {
    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }
    public void setPolygonList(List<Polygon> polygonList) { this.polygonList = polygonList; }

    public List<Fix> getFixList() { return fixList; }
    public void setFixList(List<Fix> fixList) { this.fixList = fixList; }

    public List<Procedure> getProcedureList() {
        return procedureList;
    }
    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }
}
