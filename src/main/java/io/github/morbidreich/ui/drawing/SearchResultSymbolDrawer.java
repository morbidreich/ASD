package io.github.morbidreich.ui.drawing;

import java.awt.*;

public class SearchResultSymbolDrawer {
    public static void draw(Graphics g, int x, int y) {
        int dim1 = 6;
        g.drawOval(x-dim1, y-dim1, dim1 * 2, dim1 * 2);
        g.drawLine(x - dim1, y, x + dim1, y);
        g.drawLine(x, y+dim1, x, y-dim1);
    }
}
