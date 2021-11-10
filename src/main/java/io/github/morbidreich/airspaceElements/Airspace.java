package io.github.morbidreich.airspaceElements;

import java.util.*;

public class Airspace {

    private List<Fix> fixList = new ArrayList<>();
    private List<Polygon> polygonList = new ArrayList<>();
    private List<Procedure> procedureList = new ArrayList<>();


    public Airspace() {
        //fixList = new ArrayList<>();
        //polygonList = new ArrayList<>();

        //readData();
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

    private void readData() {
        try {
            System.out.println("trying to load from sql database");
            //SqlDataReader.readData(this);
            System.out.println("succesfully loaded airspace data from sql");
        }
        catch (Exception e) {
            System.out.println("sql data load error, loading fallback data. " + e);
            //io.github.morbidreich.jpa.FallbackDataReader.readData(this);
            System.out.println("succesfully loaded airspace data from fallback source");
        }
        finally {

        }
    }
}
