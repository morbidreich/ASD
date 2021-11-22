package io.github.morbidreich.airspaceElements;

import io.github.morbidreich.utils.CoordinateConverter;

import javax.persistence.*;

import static java.lang.Math.*;
import static java.lang.Math.tan;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BasePoint implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="id")
    private int id;

    @Column(name="coordinates")
    private String coordinates;

    @Transient
    private double latitude = -1;

    @Transient
    private double longitude = -1;

    @Transient
    private double northing = -1;

    @Transient
    private double easting = -1;

    @SuppressWarnings("unused")
    public BasePoint() {}

    public BasePoint(Coordinates coordinates) {
        calculateNorthingEasting(coordinates.getLatitude(), coordinates.getLongitude());
        this.latitude = coordinates.getLatitude();
        this.longitude = coordinates.getLongitude();
    }

    // We need to use a single "reference meridian" for all points, for the
    // projection to be meaningful. We use the first point's longitude for
    // this. UTM ensures that distortion is below 1/1000 for a 6° longitude
    // span around lambda0.
    // Alternatively, we could use an explicit UTM zone and project within
    // this zone.

    private static double lambda0 = Double.NaN;
    private static final int N0_NORTH = 0;            // northern hemisphere

    private static final int N0_SOUTH = 10000;        // southern hemisphere
    private static double k0 = .9996;

    private static double a = 6378.137;                // Earth's radius
    private static double e = .0818192;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    //#################################################
    //
    // changes to getLatitude/Longitude/Easting/Northing
    // because of switching to hibernate. Database for points contain only coordinate string. To properly
    // initialize these fields i'm using modified getters. Initial value of params is set to -1,
    // when they're called getter checks if they're -1. If so they get initialized using coordinates string.
    //
    //#################################################
    /**
     * Returns the latitude of the point.
     *
     * @return the latitude
     */
    public double getLatitude() {
        if (latitude != -1)
            return latitude;
        else
            return CoordinateConverter.getFromDMS(coordinates).getLatitude();
    }
    /**
     * Returns the longitude of the point.
     *
     * @return the longitude
     */
    public double getLongitude() {
        if (longitude != -1)
            return longitude;
        else
            return CoordinateConverter.getFromDMS(coordinates).getLongitude();
    }

    /**
     * Returns the easting of the point.
     *
     * @return the easting
     */
    public double getEasting() {
        if (easting == -1)
            calculateNorthingEasting(getLatitude(), getLongitude());
        return easting;
    }

    public void setEasting(double easting) {
        this.easting = easting;
    }

    /**
     * Returns the northing of the point.
     *
     * @return the northing
     */
    public double getNorthing() {
        if (northing == -1)
            calculateNorthingEasting(getLatitude(), getLongitude());
        return northing;
    }

    public void setNorthing(double northing) {
        this.northing = northing;
    }

    public void calculateNorthingEasting(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        // lambda0 is the reference meridian of the projection
        // we use the first point's longitude as lambda0
        if (Double.isNaN(lambda0)) {
            lambda0 = longitude * PI / 180;

            // Note: another approach would be to use an explicit UTM zone, and
            // then deduce lambda0 as the zone's "middle meridian":
            // lambda0 = ((UTM_ZONE - 1) * 6 -180 + 3) * PI/180;
        }


        // project this point
        // see http://en.wikipedia.org/wiki/Universal_Transverse_Mercator_coordinate_system

        int n0 = this.latitude >= 0 ? N0_NORTH : N0_SOUTH;

        // determine easting and northing
        double phi = latitude * PI / 180;
        double lambda = longitude * PI / 180;

        double e2 = e * e;
        double e4 = e2 * e2;
        double e6 = e2 * e4;

        double nu = 1 / sqrt(1 - e2 * pow(sin(phi), 2));
        double A = (lambda - lambda0) * cos(phi);
        double A2 = A * A;
        double A3 = A * A2;
        double A4 = A2 * A2;
        double A5 = A3 * A2;
        double A6 = A3 * A3;
        double s = (1 - e2 / 4 - 3 * e4 / 64 - 5 * e6 / 256) * phi - (3 * e2 / 8 + 3 * e4 / 32 + 45 * e6 / 1024) * sin(2 * phi) + (15 * e4 / 256 + 45 * e6 / 1024) * sin(4 * phi) - 35 * e6 / 3072 * sin(6 * phi);

        double T = Math.pow(tan(phi), 2);
        double C = e2 / (1 - e * e) * pow(cos(phi), 2);

        this.easting = 500 + k0 * a * nu * (A + (1 - T + C) * A3 / 6 + (5 - 18 * T + T * T) * A5 / 120);
        this.northing = n0 + k0 * a * (s + nu * tan(phi) * (A2 / 2 + (5 - T + 9 * C + 4 * C * C) * A4 / 24 + (61 - 58 * T + T * T) * A6 / 720));
    }

    public double distanceTo(BasePoint other) {
        double ratio = Math.PI / 180;
        double deltaLat = ratio * (other.latitude - this.latitude);
        double deltaLon = ratio * (other.longitude - this.longitude);

        double angle = 2 * Math.asin( Math.sqrt(
                Math.pow(Math.sin(deltaLat/2), 2) +
                        Math.cos(ratio * this.latitude) * Math.cos(ratio * other.latitude) *
                                Math.pow(Math.sin(deltaLon/2), 2) ) );

        return a * angle;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "io.github.morbidreich.airspaceElements.Coordinates: [id=" + id + ", coordinates=" + coordinates + "]";
    }
}
