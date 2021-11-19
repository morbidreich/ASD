package io.github.morbidreich.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {

    @Test
    void bearing_north_test() {
        double result_ok = 0.0;

        assertEquals(result_ok, Calculations.bearing(50.0, 20.0, 51.0, 20.0));
    }

    @Test
    void bearing_south_test() {
        double result_ok = 180;
        assertEquals(result_ok, Calculations.bearing(53, 0, 50, 0));
    }
}