package io.github.morbidreich.surveilance;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;
import org.opensky.model.StateVector;

import java.util.LinkedList;
import java.util.List;

public class Track extends BasePoint {
    private int mostRecentTime;
    private StateVector sv;

    private List<TrackPosition> trackHistory;

    private String icao24; //transponder code;
    private String callsing;
    private String originCountry;
    private Integer timePosition;
    private Integer lastContact;
    private Double longitude;
    private Double latitude;
    private Double baroAltitude;
    private Boolean onGround;
    private Double velocity;
    private Double trueTrack;
    private Double verticalRate;
    private Integer[] sensors;
    private Double geoAltitude;
    private String squawk;
    private Boolean spi;
    private Integer positionSource;

    public Track(StateVector sv) {
        super(new Coordinates(sv.getLatitude(), sv.getLongitude()));
        this.sv = sv;

        trackHistory = new LinkedList<>();
    }

    public void update(StateVector sv) {
        //update main coordinates
        calculateNorthingEasting(sv.getLatitude(), sv.getLongitude());
        //add to track history
        trackHistory.add(new TrackPosition(sv.getLatitude(), sv.getLongitude()));
        //update state vector
        this.sv = sv;
    }

    public String getCallsing() {
        return (sv.getCallsign() == null) ? "????" : callsing;
    }

    public Double getVelocity() {
        // returns value converted from meters/s to knots
        return (sv.getVelocity() == null ) ? -1.0 : Math.ceil(sv.getVelocity() * 1.9438);
    }

    public Double getBaroAltitude() {
        // returns value converted from meters to hundreds of feet
        return (sv.getBaroAltitude() == null) ? -1.0 : Math.ceil(sv.getBaroAltitude() * 0.0328083990);
    }

    public Double getHeading() {
        return sv.getHeading();
    }

    public Double getVerticalRate() {
        return sv.getVerticalRate();
    }

    public Boolean getSpi() {
        return sv.isSpi();
    }

    public Boolean isOnGround() { return sv.isOnGround(); }
}
