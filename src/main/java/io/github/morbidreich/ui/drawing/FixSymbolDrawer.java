package io.github.morbidreich.ui.drawing;

import io.github.morbidreich.airspaceElements.Fix;
import io.github.morbidreich.airspaceElements.FixType;

import java.awt.*;

public class FixSymbolDrawer {
    public static void drawFixSymbol(int x, int y, Graphics g, Fix fix) {
        switch (fix.getFixType()) {
            case VFR: g.drawOval(x, y, 5, 5); break;
            case AERODROME: AerodromeSymbolDrawer.draw(x, y, g); break;
            case SEARCH_RESULT: SearchResultSymbolDrawer.draw(g, x, y); break;
            case VOR: VorSymbolDrawer.draw(g, x, y); break;
            case DME: DmeSymbolDrawer.draw(g, x, y); break;
            default: DefaultSymbolDrawer.draw(g, x, y); break;
        }




//        if (fix.getFixType() == FixType.VFR) {
//
//        }
//        else if (fix.getFixType() == FixType.AERODROME) {
//            AerodromeSymbolDrawer.draw(x, y, g);
//        }
//        else if (fix.getFixType() == FixType.SEARCH_RESULT) {
//            SearchResultSymbolDrawer.draw(g, x, y);
//        }
//        //each other type's symbol is triangle
//        else {
//            DefaultSymbolDrawer.draw(g, x, y);
//        }
        //consider adding fancy symbol for VORs, NDBs and think of one for cities
    }
}
