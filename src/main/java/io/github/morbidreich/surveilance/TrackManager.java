package io.github.morbidreich.surveilance;

import io.github.morbidreich.utils.AppSettings;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.util.*;

/**
 * This class stores and manages fetched track data.
 */
public class TrackManager {

    private final Map<String, Track> trackMap;

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

        for(StateVector sv : os.getStates()) {
            // if map contains that track then update it's position history
            if (trackMap.containsKey(sv.getIcao24())) {
                Track t = trackMap.get(sv.getIcao24());
                //t = updateTrackHistory(t, sv);
                trackMap.put(sv.getIcao24(), updateTrackHistory(t, sv));
            }
            // if that's a new, not expired track, then add to collection
            else {
                if (time - sv.getLastPositionUpdate() < AppSettings.EXPIRATION_TIME)
                    trackMap.put(sv.getIcao24(), new Track(sv));
            }
            removeExpiredTracks(time);
        }
    }

    /**
     * Loop over track map and look for inactive ones. If lastPositionUpdate is older more
     * than EXPIRATION_TIME than actual time, then delete track from collection. If pos more than
     * DROP_WARNING_THRESHOLD and less than EXPIRATION, mark track as Dropping
     * @param actualTime actual time reported by OpenSky api
     */
    private void removeExpiredTracks(int actualTime) {
        List<String> icao24listToDelete = new ArrayList<>();

        for (Map.Entry<String, Track> entrySet : trackMap.entrySet()) {
            if (actualTime - entrySet.getValue().getLastPositionUpdate() > AppSettings.EXPIRATION_TIME)
                icao24listToDelete.add(entrySet.getKey());
            else if ((actualTime - entrySet.getValue().getLastPositionUpdate()) > AppSettings.DROP_WARNING_THRESHOLD_TIME &&
                    (actualTime - entrySet.getValue().getLastPositionUpdate()) <= AppSettings.EXPIRATION_TIME) {
                entrySet.getValue().setDropping(true);
            }
        }
        // log messages:
        icao24listToDelete.forEach(s -> System.out.println(trackMap.get(s).getCallsing() + ": remove due delay of " + (actualTime - trackMap.get(s).getLastPositionUpdate())));
        icao24listToDelete.forEach(trackMap::remove);
    }

    private Track updateTrackHistory(Track track, StateVector sv) {
        track.update(sv);
        return track;
    }

    public List<Track> getTrackList() {
        return trackMap.values().stream().toList();
    }

    public int getSize() {
        return trackMap.size();
    }
}
