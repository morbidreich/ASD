package io.github.morbidreich;

public class App_fallback {
    public static void main(String[] args) {

        AirspaceReader mainReader = new HibernateDataReader();
        AirspaceReader fallbackReader = new FallbackDataReader();

        //use io.github.morbidreich.FallbackDataReader to speed up launch time
        MapWindow mapWindow = new MapWindow(fallbackReader.readAirspace());
        mapWindow.setVisible(true);
    }
}
