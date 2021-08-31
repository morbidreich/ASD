
public class Fix {

    private double latitude;
    private double longitude;
    private String name;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Fix(String name, Coordinates coords) {
        this.name = name;
        this.latitude = coords.getLatitude();
        this.longitude = coords.getLongitude();
    }

    public Fix(String name, double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }
}
