package io.github.morbidreich;

import javax.persistence.*;

@Entity
@Table(name="point")
public class Point extends BasePoint {

    @Column(name="polygon_id")
    private int polygon_id;

    @SuppressWarnings("unused")
    public Point() {
    }

    public Point(Coordinates coordinates) {
        super(coordinates);
    }

    public int getPolygon_id() {
        return polygon_id;
    }

    public void setPolygon_id(int polygon_id) {
        this.polygon_id = polygon_id;
    }

    @Override
    public String toString() {
        return "io.github.morbidreich.Point: [id=" + getId() + ", lat/lon=" + getLatitude() + "/" + getLongitude() + "]";
    }
}
