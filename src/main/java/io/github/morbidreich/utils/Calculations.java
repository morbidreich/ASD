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
}
