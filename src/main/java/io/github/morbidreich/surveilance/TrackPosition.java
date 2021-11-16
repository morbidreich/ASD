package io.github.morbidreich.surveilance;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;

import java.util.Objects;

public class TrackPosition {
    // for now i will not track timestamp of position reports,
    // instead i will use order of reports on linkedlist

    //private int timestamp;
    private BasePoint position;

    public TrackPosition(/*int timestamp, */double latitude, double longitude) {
        //this.timestamp = timestamp;
        position = new BasePoint(new Coordinates(latitude, longitude));
    }

//    public int getTimestamp() {
//        //return timestamp;
//    }

    public BasePoint getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "TrackPosition{" +
                "position=" + position +
                '}';
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // i consider positions to be equal if lat/lon pairs are no more than 2 seconds apart (1/1800),
        // which is rougly no more than 60m
        TrackPosition that = (TrackPosition) o;
        if (Math.abs(this.getPosition().getLatitude() - that.getPosition().getLatitude()) < 1.0/1800 &&
        Math.abs(this.getPosition().getLongitude() - that.getPosition().getLongitude()) < 1.0/1800)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
