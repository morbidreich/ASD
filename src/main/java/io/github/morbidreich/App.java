package io.github.morbidreich;

import io.github.morbidreich.jpa.AirspaceReader;
import io.github.morbidreich.jpa.HibernateDataReader;
import io.github.morbidreich.ui.MapWindow;
import io.github.morbidreich.jpa.FallbackDataReader;

public class App {
    public static void main(String[] args) {

        AirspaceReader mainReader = new HibernateDataReader();
        AirspaceReader fallbackReader = new FallbackDataReader();

        //use FallbackDataReader to speed up launch time
        MapWindow mapWindow = new MapWindow(mainReader.readAirspace());
        mapWindow.setVisible(true);
    }
}
