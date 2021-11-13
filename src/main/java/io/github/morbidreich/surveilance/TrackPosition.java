package io.github.morbidreich.surveilance;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;

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
}
