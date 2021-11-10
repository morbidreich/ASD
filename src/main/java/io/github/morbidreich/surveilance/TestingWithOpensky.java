package io.github.morbidreich.surveilance;

import io.github.morbidreich.BasePoint;
import io.github.morbidreich.Coordinates;
import io.github.morbidreich.MapPanel;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestingWithOpensky implements Runnable {

    private MapPanel map;
    private List<BasePoint> tracks = new ArrayList<>();
    final String USERNAME = "Kujda";
    final String PASSWORD = "ePDpu.jDqvV7yci";


    public TestingWithOpensky(MapPanel mapPanel) {
        this.map = mapPanel;
    }

    @Override
    public void run() {
        while (true) {

            OpenSkyApi api = new OpenSkyApi(USERNAME, PASSWORD);
            OpenSkyStates os = null;
            try {
                os = api.getStates(0, null,
                        new OpenSkyApi.BoundingBox(47.8389, 55.8229, 16.9962, 23.5226));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Collection<StateVector> stateVectorList = os.getStates();
            List<StateVector> list = stateVectorList.stream().collect(Collectors.toList());

            List<Track> tracks = new ArrayList<>();

            for (StateVector sv : list) {
                Track bp = new Track(new Coordinates(sv.getLatitude(), sv.getLongitude()), sv.getCallsign());
                tracks.add(bp);
            }
            map.setTracks(tracks);
            map.repaint();
            System.out.println("got data from api, timestamp = " + os.getTime());
            try {
                Thread.sleep(4000);
            }
            catch (InterruptedException e) {
                System.out.println("Interrupted api data acces, quitting");
            }
        }
    }
}
