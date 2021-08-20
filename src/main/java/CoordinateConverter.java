public class CoordinateConverter {
    private String latitude;
    private String longitude;

    public static Fix getFromDMS(String coordLine) {

        double latDeg, latMin, latSec, lonDeg, lonMin, lonSec;

        latDeg = Double.parseDouble(coordLine.substring(0,2));
        latMin = Double.parseDouble(coordLine.substring(3,5));
        latSec = Double.parseDouble(coordLine.substring(6,8));
        lonDeg = Double.parseDouble(coordLine.substring(11,14));
        lonMin = Double.parseDouble(coordLine.substring(15,17));
        lonSec = Double.parseDouble(coordLine.substring(18,20));

        double lat = latDeg + (latMin * 60 + latSec)/3600;
        double lon = lonDeg + (lonMin * 60 + lonSec)/3600;

        return new Fix(lat, lon);
    }
}
