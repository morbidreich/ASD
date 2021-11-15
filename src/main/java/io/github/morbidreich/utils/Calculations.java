package io.github.morbidreich.utils;

public class Calculations {
    public static double getAngleGivenTwoPoints(int x1, int y1, int x2, int y2) {
        //calculate bearing
        //https://math.stackexchange.com/questions/707673/find-angle-in-degrees-from-one-point-to-another-in-2d-space
        double a = y2 - y1;
        double b = x2 - x1;
        //default 0 is pointing eastward so i rotate it counterclockwise by substracting 90 degrees
        return Math.toDegrees(Math.atan2(a, b)) - 90;
    }

    public static double bearing(double lat1, double lon1, double lat2, double lon2){
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double longDiff= Math.toRadians(lon2-lon1);
        double y= Math.sin(longDiff)*Math.cos(latitude2);
        double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }
}
