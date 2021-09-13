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


        lat = Math.round(lat * 10000d) / 10000d;
        lon = Math.round(lon * 10000d) / 10000d;

        return new Coordinates(lat, lon);
    }
}
