package io.github.morbidreich.surveilance;

import io.github.morbidreich.utils.AppSettings;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.util.*;

/**
 * This class stores and manages fetched track data.
 */
public class TrackManager {

    private Map<String, Track> trackMap;

    /**
     * create instance of TrackManager
     */
    public TrackManager() {
        trackMap = new HashMap<>();
    }

    /**
     * For each state vector returned by api check if corresponding track
     * is already tracked. If so then update. If not then add to map
     * @param os List of StateVectors returned by api
     */
    public void update(OpenSkyStates os) {

        int time = os.getTime();
        List<StateVector> stateVectors = (List<StateVector>) os.getStates();

        for(StateVector sv : stateVectors) {
            // if map contains that track then update it's position history
            if (trackMap.containsKey(sv.getIcao24())) {
                Track t = trackMap.get(sv.getIcao24());
                t = updateTrackHistory(t, sv, time);
                trackMap.put(sv.getIcao24(), t);
            }
            // if that's a new track then add to collection
            else {
                trackMap.put(sv.getIcao24(), new Track(sv));
            }
            removeExpiredTracks(time);
        }
    }

    /**
     * Loop over track map and look for inactive ones. If lastPositionUpdate is older more
     * than 60 seconds than actual time, then delete track from collection
     * @param actualTime actual time reported by OpenSky api
     */
    private void removeExpiredTracks(int actualTime) {
        for (var entry: trackMap.entrySet()) {
            //debug log
//            System.out.println("Evaluating " + entry.getValue().getCallsing() + "; time diff = "
//            + (actualTime - entry.getValue().getLastPositionUpdate()));
            if (actualTime - entry.getValue().getLastPositionUpdate() > AppSettings.EXPIRATION_TIME)
                trackMap.remove(entry.getKey());
        }
    }

    private Track updateTrackHistory(Track track, StateVector sv, int time) {
        track.update(sv);
        return track;
    }

    public List<Track> getTrackList() {

        Iterator i = trackMap.values().iterator();
        List<Track> list = new ArrayList<>();
        while (i.hasNext()) {
            list.add((Track)i.next());
        }
        return list;
    }

    public int getSize() {
        return trackMap.size();
    }
}