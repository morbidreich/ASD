package io.github.morbidreich.surveilance;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.Coordinates;
import io.github.morbidreich.utils.Calculations;
import org.opensky.model.StateVector;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Track extends BasePoint {
    private boolean isDropping = false;
    private StateVector sv;
    private final TrackLabel trackLabel;

    private final List<TrackPosition> trackHistory;

    public boolean isDropping() {
        return isDropping;
    }

    public void setDropping(boolean dropping) {
        isDropping = dropping;
    }

    public Track(StateVector sv) {
        super(new Coordinates(sv.getLatitude(), sv.getLongitude()));
        this.sv = sv;
        trackHistory = new LinkedList<>();
        trackLabel = new TrackLabel(this);
    }

    public void update(StateVector sv) {
        //update main coordinates
        calculateNorthingEasting(sv.getLatitude(), sv.getLongitude());
        //add to track history
        trackHistory.add(new TrackPosition(sv.getLatitude(), sv.getLongitude()));
        //update state vector
        this.sv = sv;
        // if we have update then track aparrently is not dropping
        //
        setDropping(false);
    }

    /**
     * return 24-bit address assigned to transmitter
     * @return 24-bit addres
     */
    public String getIcao24() { return sv.getIcao24(); }
    public Double getLastContact() { return sv.getLastContact(); }
    public Double getLastPositionUpdate() { return sv.getLastPositionUpdate(); }
    public String getCallsing() {
        return (sv.getCallsign() == null) ? "????" : sv.getCallsign();
    }

    /**
     * Get track speed in knots
     * @return track speed [knots]
     */
    public Double getVelocity() {
        // returns value converted from meters/s to knots
        return (sv.getVelocity() == null ) ? -1.0 : Math.ceil(sv.getVelocity() * 1.9438);
    }

    /**
     * return altitude converted to hundreds of feet
     * @return altitude [hundreds of feet]
     */
    public Double getBaroAltitude() {
        // returns value converted from meters to hundreds of feet
        return (sv.getBaroAltitude() == null) ? -1.0 : Math.ceil(sv.getBaroAltitude() * 0.0328083990);
    }

    public Double getHeading() {
        return sv.getHeading();
    }

    /**
     * return vertical speed converted from meters per second to feet per minute
     * @return vertical speed [feet/min]
     */
    public Double getVerticalRate() {

        return sv.getVerticalRate() == null ? 0 : sv.getVerticalRate() * 196.8503937;
    }

    public Boolean getSpi() {
        return sv.isSpi();
    }

    public TrackLabel getTrackLabel() { return this.trackLabel; }


    public Boolean isOnGround() { return sv.isOnGround(); }

    public List<TrackPosition> getTrackHistory() {
        return trackHistory;
    }

    /**
     * return bearing of a track
     * @return bearing
     */
    public Double getBearing() {
        // i can calculate bearing only if i have at least two historic positions stored in trackHistory
        // maybe in future try average bearing using more than two points from history
        // if less than two historic positions are present then return heading from state vector
        if (trackHistory.size()>1) {
            TrackPosition t1 = trackHistory.get(trackHistory.size()-1);
            TrackPosition t2 = trackHistory.get(trackHistory.size()-2);

            // sometimes api returns same position two times in a row. in such cases Calculations.bearing
            // returns 0 and all displayed track vectors switch rapidly northbound for couple of seconds,
            // until next, different position report is received. To avoid that i check if two consecutive
            // position reports are equal (winh 2 angular seconds margin, about 60m). If they are equal
            // i return heading provided by StateVector instead of calculating bearing
            if (t1.equals(t2)) {
                return sv.getHeading();
            }
            else
            return Calculations.bearing(
                    t2.getPosition().getLatitude(), t2.getPosition().getLongitude(),
                    t1.getPosition().getLatitude(), t1.getPosition().getLongitude());
        }
        // can be null
        else return sv.getHeading();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return this.getIcao24().equals(track.getIcao24());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDropping, sv, trackLabel, trackHistory);
    }
}
