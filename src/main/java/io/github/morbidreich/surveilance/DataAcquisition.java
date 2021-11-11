package io.github.morbidreich.surveilance;

import io.github.morbidreich.ui.StatusBar;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.ui.MapPanel;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataAcquisition implements Runnable {

    private MapPanel map;
    private List<Track> tracks = new ArrayList<>();
    private StatusBar statusBar;

    public DataAcquisition(MapPanel mapPanel, StatusBar statusBar) {
        this.map = mapPanel;
        this.statusBar = statusBar;
    }

    private OpenSkyStates fetchData() throws IOException {
        OpenSkyApi api = new OpenSkyApi(AppSettings.USERNAME, AppSettings.PASSWORD);

        // get state vectors from area delimited by below coordinates, basically whole poland
        return api.getStates(0, null,
                new OpenSkyApi.BoundingBox(AppSettings.MIN_LAT
                        , AppSettings.MAX_LAT
                        , AppSettings.MIN_LON
                        , AppSettings.MAX_LON));
    }

    @Override
    public void run() {
        while (true) {

            try {
                OpenSkyStates os = fetchData();
                List<StateVector> list = os.getStates().stream().toList();
                List<Track> tracks = ParseStateVectors(list);


                map.setTracks(tracks);
                map.repaint();
                //System.out.println("got data from api, no of tracks=" + os.getStates().size() + ", timestamp = " + os.getTime());

                statusBar.updateStatusOK(tracks.size());

            } catch (Exception e) {
                statusBar.updateStatusError(" Attempting to reconnect, restart advised; error message: " + e.getMessage());
            }


            try {
                Thread.sleep(AppSettings.RADAR_REFRESH_RATE);
            } catch (InterruptedException e) {
                System.out.println("Interrupted api data acces, quitting");
            }
        }
    }

    private List<Track> ParseStateVectors(List<StateVector> list) {
        return list.stream()
                .filter(sv -> sv.getLatitude() != null && sv.getLongitude() != null) // lat and lon can be null, filter them
                .filter(sv -> !sv.isOnGround())  // filter ground stuff
                .map(Track::new)
                .collect(Collectors.toList());
    }
}
