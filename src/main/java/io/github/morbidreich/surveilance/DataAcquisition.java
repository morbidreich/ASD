package io.github.morbidreich.surveilance;

import io.github.morbidreich.ui.StatusBar;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.ui.MapPanel;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataAcquisition implements Runnable {

    private MapPanel map;
    private List<Track> tracks = new ArrayList<>();
    private StatusBar statusBar;
    private TrackManager trackManager;

    public DataAcquisition(MapPanel mapPanel, StatusBar statusBar) {
        this.map = mapPanel;
        this.statusBar = statusBar;
        trackManager = new TrackManager();
    }

    private OpenSkyStates fetchData() throws IOException {
        OpenSkyApi api = new OpenSkyApi(AppSettings.USERNAME, AppSettings.PASSWORD);

        // get state vectors from area delimited by below coordinates, basically whole poland
        return api.getStates(0, null,
                new OpenSkyApi.BoundingBox(AppSettings.FETCH_MIN_LAT
                        , AppSettings.FETCH_MAX_LAT
                        , AppSettings.FETCH_MIN_LON
                        , AppSettings.FETCH_MAX_LON));
    }

    @Override
    public void run() {
        while (true) {

            try {
                OpenSkyStates os = fetchData();
                //List<StateVector> list = os.getStates().stream().toList();
                //List<Track> tracks = ParseStateVectors(list);

                trackManager.update(os);


                map.setTracks(trackManager.getTrackList());
                map.repaint();
                //System.out.println("got data from api, no of tracks=" + os.getStates().size() + ", timestamp = " + os.getTime());

                //update statusbar with no. of tracked objects
                statusBar.updateStatusOK(trackManager.getSize());

            } catch (Exception e) {
                //when connection not working clear tracks list
                map.setTracks(new ArrayList<Track>());
                map.repaint();

                statusBar.updateStatusError(" Attempting to reconnect... (Error message: " + e.getMessage() + ")");
                e.printStackTrace();
                alterErrorColor(statusBar);
            }
            try {
                Thread.sleep(AppSettings.RADAR_REFRESH_RATE);
            } catch (InterruptedException e) {
                System.out.println("Interrupted api data acces, quitting");
            }
        }
    }

    private void alterErrorColor(StatusBar statusBar) {
    // periodically alter error font color to signal user know that we're trying to reconnect
        Color orig = new Color(120, 120,120);
        Color second = new Color(80, 80,80);

        if (statusBar.l3.getForeground().equals(orig))
            statusBar.l3.setForeground(second);
        else
            statusBar.l3.setForeground(orig);

    }

    private List<Track> ParseStateVectors(List<StateVector> list) {
        return list.stream()
                .filter(sv -> sv.getLatitude() != null && sv.getLongitude() != null) // lat and lon can be null, filter them
                .filter(sv -> !sv.isOnGround())  // filter ground stuff
                .map(Track::new)
                .collect(Collectors.toList());
    }

    private void collectData(List<StateVector> list) {
        Map<String, Track> trackMap;

    }
}
