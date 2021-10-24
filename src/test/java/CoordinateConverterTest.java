import io.github.morbidreich.BasePoint;
import io.github.morbidreich.CoordinateConverter;
import io.github.morbidreich.Coordinates;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CoordinateConverterTest {

    @Test
    public void fullDegreeConversionTest() {
        BasePoint zero = new BasePoint(new Coordinates(10,10));
        BasePoint testCase = new BasePoint(CoordinateConverter.getFromDMS("10°00'00\"N 010°00'00\"E"));

        assertEquals(zero.getLatitude(), testCase.getLatitude());
        assertEquals(zero.getLongitude(), testCase.getLongitude());
    }

    @Test
    public void halfDegreeConversionTest() {

        BasePoint half = new BasePoint(new Coordinates(10.5, 10.5));
        BasePoint testCase = new BasePoint(CoordinateConverter.getFromDMS("10°30'00\"N 010°30'00\"E"));

        assertEquals(half.getLatitude(), testCase.getLatitude());
        assertEquals(half.getLongitude(), testCase.getLongitude());
    }

    @Test
    public void quarterCoordConversionTest() {
        BasePoint quarter = new BasePoint(new Coordinates(10.75, 10.75));
        BasePoint testCase = new BasePoint(CoordinateConverter.getFromDMS("10°45'00\"N 010°45'00\"E"));

        assertEquals(quarter.getLatitude(), testCase.getLatitude());
        assertEquals(quarter.getLongitude(), testCase.getLongitude());
    }

    @Test
    public void secondsCoordConversionTest() {
        BasePoint seconds = new BasePoint(new Coordinates(10.01389, 10.01389));
        BasePoint testCase = new BasePoint(CoordinateConverter.getFromDMS("10°00'50\"N 010°00'50\"E"));

        assertEquals(seconds.getLatitude(), testCase.getLatitude(), 0.001);
        assertEquals(seconds.getLongitude(), testCase.getLongitude(), 0.001);
    }
}
