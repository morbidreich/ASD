import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CoordinateConverterTest {

    @Test
    public void fullDegreeConversionTest() {
        Fix zero = new Fix("", 10, 10);
        Fix testCase = new Fix("", CoordinateConverter.getFromDMS("10°00'00\"N 010°00'00\"E"));

        assertEquals(zero.getLatitude(), testCase.getLatitude());
        assertEquals(zero.getLongitude(), testCase.getLongitude());
    }

    @Test
    public void halfDegreeConversionTest() {

        Fix half = new Fix("", 10.5, 10.5);
        Fix testCase = new Fix("", CoordinateConverter.getFromDMS("10°30'00\"N 010°30'00\"E"));

        assertEquals(half.getLatitude(), testCase.getLatitude());
        assertEquals(half.getLongitude(), testCase.getLongitude());
    }

    @Test
    public void quarterCoordConversionTest() {
        Fix quarter = new Fix("", 10.75, 10.75);
        Fix testCase = new Fix("", CoordinateConverter.getFromDMS("10°45'00\"N 010°45'00\"E"));

        assertEquals(quarter.getLatitude(), testCase.getLatitude());
        assertEquals(quarter.getLongitude(), testCase.getLongitude());
    }

    @Test
    public void secondsCoordConversionTest() {
        Fix seconds = new Fix("", 10.01389, 10.01389);
        Fix testCase = new Fix("", CoordinateConverter.getFromDMS("10°00'50\"N 010°00'50\"E"));

        assertEquals(seconds.getLatitude(), testCase.getLatitude(), 0.001);
        assertEquals(seconds.getLongitude(), testCase.getLongitude(), 0.001);
    }
}
