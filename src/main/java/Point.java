/**
 * code by Cristopher Jacquet with my my slight modifications
 * https://github.com/ChristopheJacquet/Minigeo
 */

import javax.persistence.*;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

/**
 * Geographic point, specified by its coordinates.
 *
 * @author Christophe Jacquet
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name = "coordinates")
    private String coordinates;

    @Transient
    private double latitude = -1;

    @Transient
    private double longitude = -1;

    @Transient
    private double easting = -1;

    @Transient
    private double northing = -1;

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
    @SuppressWarnings("unused") // for hibernate
    public Point() {
    }

    public Point(Coordinates coords) {
        this(coords.getLatitude(), coords.getLongitude());
    }

    /**
     * Creates a new point, given its coordinates according to the WGS84 datum.
     *
     * @param latitude
     * @param longitude
     */
    public Point(double latitude, double longitude) {
        calculateNorthingEasting(latitude, longitude);
    }

    private void calculateNorthingEasting(double latitude, double longitude) {
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

    /**
     * As for now i don't know, neither feel like figuring out how to convert lat/lon to notrhling/easting,
     * but need constructor taking two doubles. To allow that I introduced dummy foo parameter.
     * Set it to whatever you want, it doesn't do anything.
     *
     * @param northing
     * @param easting
     * @param foo      - dummy parameter
     */
    public Point(double northing, double easting, int foo) {
        this.northing = northing;
        this.easting = easting;

        //TODO calculate these given northing/easting
        //for now leave it like that and hope it'll work
        this.latitude = 53;
        this.longitude = 21;

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
    double getEasting() {
        if (easting == -1)
            calculateNorthingEasting(getLatitude(), getLongitude());
        return easting;
    }

    /**
     * Returns the northing of the point.
     *
     * @return the northing
     */
    double getNorthing() {
        if (northing == -1)
            calculateNorthingEasting(getLatitude(), getLongitude());
        return northing;
    }

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

    /**
     * Returns the distance between this point and another point, in
     * kilometers.
     *
     * @param other another point
     * @return the distance between the points
     */
    public double distanceTo(Point other) {
        double ratio = Math.PI / 180;
        double deltaLat = ratio * (other.latitude - this.latitude);
        double deltaLon = ratio * (other.longitude - this.longitude);

        double angle = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(deltaLat / 2), 2) +
                        Math.cos(ratio * this.latitude) * Math.cos(ratio * other.latitude) *
                                Math.pow(Math.sin(deltaLon / 2), 2)));

        return a * angle;
    }

    public void setNorthing(double northing) {
        this.northing = northing;
    }

    public void setEasting(double easting) {
        this.easting = easting;
    }

    @Override
    public String toString() {
        return "Point: [id=" + id + ", nrth=" + getNorthing() + ", estng=" + getEasting() + "]";
    }
}
