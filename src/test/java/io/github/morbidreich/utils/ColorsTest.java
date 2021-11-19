package io.github.morbidreich.utils;

import io.github.morbidreich.airspaceElements.FixType;
import io.github.morbidreich.airspaceElements.PolygonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorsTest {
    Colors colors;

    @BeforeEach
    void setUp() {
        colors = new Colors();
    }

    @Test
    void getColorByFixTypeVFR() {
        Color vfrColor = new Color(255, 250, 0, 122);
        assertEquals(vfrColor, colors.getColor(FixType.VFR));
    }

    @Test
    void getColorByPolygonTypeTMA() {
        Color tmaColor = new Color(170, 170, 170, 122);
        assertEquals(tmaColor, colors.getColor(PolygonType.TMA));
    }
}