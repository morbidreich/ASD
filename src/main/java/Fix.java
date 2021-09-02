
public class Fix extends POI {

    private String name;
    private boolean isVisible = false;

    public FixType getFixType() {
        return fixType;
    }

    private FixType fixType;


    public Fix(String name, Coordinates coords) {
        super(coords.getLatitude(), coords.getLongitude(), name);
    }

    public Fix(String name, Coordinates coords, FixType fixType) {
        super(coords.getLatitude(), coords.getLongitude(), name);
        this.fixType = fixType;
    }

    public Fix(String name, double latitude, double longitude) {
        super(latitude, longitude, name);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

}
