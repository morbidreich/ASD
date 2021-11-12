package io.github.morbidreich.surveilance;

import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// updates track data with incoming reports
// mainly to keep track of sereral previous position reports
// i need them to calculate speed vector and display historical positions behind blip
public class TrackUpdater {

    private Map<String, Track> trackMap;

    public TrackUpdater() {
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
        }
    }

    private Track updateTrackHistory(Track track, StateVector sv) {
        track.update(sv);
        return track;
    }
}
