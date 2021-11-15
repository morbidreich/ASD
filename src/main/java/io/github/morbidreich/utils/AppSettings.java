package io.github.morbidreich.utils;// TODO better way for storing application settings in link below
// https://docs.oracle.com/javase/1.5.0/docs/guide/preferences/index.html

public class AppSettings {
    public static final int MAGNETIC_VARIATION = 4;

    // OpenSkyApi related settings

    public static final int RADAR_REFRESH_RATE = 5000;
    public static final String USERNAME = "Kujda";
    public static final String PASSWORD = "ePDpu.jDqvV7yci";

    //time diff between actual time and last report time for which track will be deleted
    public static final int EXPIRATION_TIME = 35;

    //time diff between actual time and last report time for which track is considered as 'dropping'
    public static final int DROP_WARNING_THRESHOLD_TIME = 15;

    // set of coordinates representing more or less polish teritory
    // will fetch data for these bound
    public static final double FETCH_MIN_LAT = 49.0;
    public static final double FETCH_MIN_LON = 13.0;
    public static final double FETCH_MAX_LAT = 56.0;
    public static final double FETCH_MAX_LON = 24.0;

    //will display track data for these bounds
    public static final double SHOW_MIN_LAT = 49.3;
    public static final double SHOW_MIN_LON = 13.3;
    public static final double SHOW_MAX_LAT = 55.7;
    public static final double SHOW_MAX_LON = 23.7;


}
