package io.github.morbidreich.ui.drawing;

import io.github.morbidreich.airspaceElements.Fix;
import io.github.morbidreich.airspaceElements.FixType;

import java.awt.*;

public class FixSymbolDrawer {
    public static void drawFixSymbol(int x, int y, Graphics g, FixType ft) {
        switch (ft) {
            case VFR -> g.drawOval(x, y, 5, 5);
            case AERODROME -> AerodromeSymbolDrawer.draw(x, y, g);
            case SEARCH_RESULT -> SearchResultSymbolDrawer.draw(g, x, y);
            case VOR -> VorSymbolDrawer.draw(g, x, y);
            case DME -> DmeSymbolDrawer.draw(g, x, y);
            default -> DefaultSymbolDrawer.draw(g, x, y);
        }
    }
}
