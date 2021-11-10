package io.github.morbidreich.surveilance;

import io.github.morbidreich.BasePoint;
import io.github.morbidreich.Coordinates;
import org.opensky.model.StateVector;

import java.util.Optional;

public class Track extends BasePoint {

    private StateVector sv;

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
        this.callsing = sv.getCallsign();
        this.velocity = sv.getVelocity();
        this.baroAltitude = sv.getBaroAltitude();
    }

    public String getCallsing() {
        return (callsing == null) ? "????" : callsing;
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
}
