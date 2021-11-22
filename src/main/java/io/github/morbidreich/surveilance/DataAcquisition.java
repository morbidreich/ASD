package io.github.morbidreich.surveilance;

import io.github.morbidreich.ui.statusbar.StatusBar;
import io.github.morbidreich.utils.AppSettings;
import io.github.morbidreich.ui.MapPanel;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DataAcquisition implements Runnable {

    private final MapPanel map;
    private final StatusBar statusBar;
    private final TrackManager trackManager;

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

                trackManager.update(os);

                map.setTracks(trackManager.getTrackList());

                //update statusbar with no. of tracked objects
                statusBar.updateStatusOK(trackManager.getSize());

            }
            catch (Exception e) {

                map.setTracks(new ArrayList<>());
                statusBar.updateStatusError(" Attempting to reconnect... (Error message: " + e.getMessage() + ")");
                e.printStackTrace();
                alterErrorColor(statusBar);
            }
            finally {
                map.repaint();

            }
            try {
                Thread.sleep(AppSettings.RADAR_REFRESH_RATE);
            } catch (InterruptedException e) {

                System.out.println("Interrupted api data acces, quitting");
                map.setTracks(new ArrayList<>());
                statusBar.updateStatusOffline();
                break;
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
}
