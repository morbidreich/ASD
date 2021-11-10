package io.github.morbidreich.surveilance;

import io.github.morbidreich.BasePoint;
import io.github.morbidreich.Coordinates;

public class Track extends BasePoint {

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

    public Track(Coordinates c, String callsign) {
        super(c);
        this.callsing = callsign;
    }

    public String getCallsing() {
        return callsing;
    }
}
