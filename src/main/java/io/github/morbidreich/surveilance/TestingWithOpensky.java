package io.github.morbidreich.surveilance;

import io.github.morbidreich.ApplicationSettings;
import io.github.morbidreich.MapPanel;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestingWithOpensky implements Runnable {

    private MapPanel map;
    private List<Track> tracks = new ArrayList<>();
    private final String USERNAME = "Kujda";
    private final String PASSWORD = "ePDpu.jDqvV7yci";


    public TestingWithOpensky(MapPanel mapPanel) {
        this.map = mapPanel;
    }

    @Override
    public void run() {
        while (true) {

            try {
                OpenSkyApi api = new OpenSkyApi(USERNAME, PASSWORD);
                OpenSkyStates os = null;

                // get state vectors from area delimited by below coordinates, basically whole poland
                os = api.getStates(0, null,
                        new OpenSkyApi.BoundingBox(49.8389, 55.8229, 13.9962, 23.5226));

                Collection<StateVector> collection = os.getStates();
                List<StateVector> list = collection.stream().toList();

                List<Track> tracks = new ArrayList<>();

                for (StateVector sv : list) {
                    // reject all state vectors that has latitude or longutude as null

                    if (sv.getLatitude() == null && sv.getLongitude() == null) {
                        System.out.println("rejecting SV id:" + sv.getIcao24() + ", callsign:" + sv.getCallsign());
                        continue;
                    }

                    Track bp = new Track(sv);
                    tracks.add(bp);
                }

                map.setTracks(tracks);
                map.repaint();
                System.out.println("got data from api, no of tracks=" + os.getStates().size() + ", timestamp = " + os.getTime());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(ApplicationSettings.RADAR_REFRESH_RATE);
            } catch (InterruptedException e) {
                System.out.println("Interrupted api data acces, quitting");
            }
        }
    }
}
