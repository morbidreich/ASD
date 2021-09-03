
public class Fix extends Point {

    private final String name;

    private boolean isVisible = false;
    private final FixType fixType;
    public Fix(String name, Coordinates coords, FixType fixType) {
        super(coords.getLatitude(), coords.getLongitude());
        this.fixType = fixType;
        this.name = name;
    }

    public Fix(String name, double latitude, double longitude, FixType fixType) {
        super(latitude, longitude);
        this.name = name;
        this.fixType = fixType;
    }

    public String getName() { return name; }

    public FixType getFixType() {
        return fixType;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

}
