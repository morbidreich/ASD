package io.github.morbidreich.surveilance;

import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.util.*;

/**
 * This class stores and manages fetched track data.
 */
public class TrackManager {

    private Map<String, Track> trackMap;

    public TrackManager() {
        trackMap = new HashMap<>();
    }

    public void update(OpenSkyStates os) {

        int time = os.getTime();
        List<StateVector> stateVectors = (List<StateVector>) os.getStates();

        for(StateVector sv : stateVectors) {
            // if map contains that track then update it's position history
            if (trackMap.containsKey(sv.getIcao24())) {
                Track t = trackMap.get(sv.getIcao24());
                t = updateTrackHistory(t, sv);
                trackMap.put(sv.getIcao24(), t);
            }
            // if that's a new track then add to collection
            else {
                trackMap.put(sv.getIcao24(), new Track(sv));
            }
            //removeExpiredTracks(time);
        }
    }

    private void removeExpiredTracks(int time) {
        Iterator<Map.Entry<String, Track>> i = trackMap.entrySet().iterator();

        while(i.hasNext()) {
            Map.Entry<String, Track> next = i.next();
            if ((time - next.getValue().getLastPositionUpdate()) > 20)
                trackMap.remove(next.getKey());
        }

    }

    private Track updateTrackHistory(Track track, StateVector sv) {
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
