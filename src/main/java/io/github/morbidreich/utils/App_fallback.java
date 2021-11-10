package io.github.morbidreich.utils;

import io.github.morbidreich.jpa.AirspaceReader;
import io.github.morbidreich.jpa.FallbackDataReader;
import io.github.morbidreich.jpa.HibernateDataReader;
import io.github.morbidreich.ui.MapWindow;

public class App_fallback {
    public static void main(String[] args) {

        AirspaceReader mainReader = new HibernateDataReader();
        AirspaceReader fallbackReader = new FallbackDataReader();

        //use io.github.morbidreich.jpa.FallbackDataReader to speed up launch time
        MapWindow mapWindow = new MapWindow(fallbackReader.readAirspace());
        mapWindow.setVisible(true);
    }
}
