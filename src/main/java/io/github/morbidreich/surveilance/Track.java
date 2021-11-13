package io.github.morbidreich.surveilance;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;
import org.opensky.model.StateVector;

import java.util.LinkedList;
import java.util.List;

public class Track extends BasePoint {
    private int mostRecentTime;
    private StateVector sv;

    private final List<TrackPosition> trackHistory;

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

    public String getIcao24() { return sv.getIcao24(); }
    public Double getLastContact() { return sv.getLastContact(); }
    public Double getLastPositionUpdate() { return sv.getLastPositionUpdate(); }
    public String getCallsing() {
        return (sv.getCallsign() == null) ? "????" : sv.getCallsign();
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

    public List<TrackPosition> getTrackHistory() {
        return trackHistory;
    }

    // return sublist of trackHistory for drawing historical plots
    // if history shorter that requested then return all we have
    // else return requested number of fields from end of array - newest

    /**
     * Return subList of trackHistory for drawing historical plots.
     * If history is shorter than requested length then return all we have.
     * Otherwise return subList of requested length
     * @param i numbers of historic trackPositions to return
     * @return sublist of TrackHistory
     */
    public List<TrackPosition> getRecentTrackHistory(int i) {
        if (trackHistory.size() > i)
            return trackHistory.subList(trackHistory.size() - i, trackHistory.size()-1);
        else return trackHistory;

    }
}
