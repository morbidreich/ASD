public class CoordinateConverter {
    private String latitude;
    private String longitude;

    public static Coordinates getFromDMS(String coordLine) {

        double latDeg, latMin, latSec, lonDeg, lonMin, lonSec;

        latDeg = Double.parseDouble(coordLine.substring(0,2));
        latMin = Double.parseDouble(coordLine.substring(3,5));
        latSec = Double.parseDouble(coordLine.substring(6,8));
        lonDeg = Double.parseDouble(coordLine.substring(11,14));
        lonMin = Double.parseDouble(coordLine.substring(15,17));
        lonSec = Double.parseDouble(coordLine.substring(18,20));

        double lat = latDeg + (latMin * 60 + latSec)/3600;
        double lon = lonDeg + (lonMin * 60 + lonSec)/3600;

        //round to 4 decimal places - if 1 degree longitude is about 111 km, then
        // 4 decimal places gives accuracy of about 10 meters


        //lat = Math.round(lat * 10000d) / 10000d;
        //lon = Math.round(lon * 10000d) / 10000d;

        return new Coordinates(lat, lon);
    }

    /**
     * Method returns specific format helpful for inserting coordinates into
     * queries for sql database. That's the reason for apostrophe at the beginning
     * and at the end, and also double apostrophes inside
     * @param lat latitude
     * @param lon longitude
     * @return sql-friendly formatted coordinates
     */
    public static String getFromLatLon(double lat, double lon) {
        return "'" + getSfromD(lat, CoordType.N) + " " + getSfromD(lon, CoordType.E) + "'";
    }

    private static String getSfromD(double coord, CoordType coordType) {
        double degree = Math.floor(coord);
        double minuteRest = coord - degree;
        double minutes = minuteRest / (1d/60d);
        double minFloor = Math.floor(minutes);
        double secondRest = minutes - minFloor;
        double seconds = secondRest / (1d/60d);



        String degreesS = coordType == CoordType.N ? String.format("%02d", Math.round(degree)) : String.format("%03d", Math.round(degree));
        String minutesS = String.format("%02d", Math.round(minutes) == 60 ? 59 : Math.round(minutes));
        String secondsS = String.format("%02d", Math.round(seconds) == 60 ? 59 : Math.round(seconds));

        // example: '53°38''41"N 020°02''30"E'


        return degreesS + "°" + minutesS + "''" +secondsS + "\"" + coordType.toString();

    }

    private enum CoordType {
        E,
        N
    }
}
