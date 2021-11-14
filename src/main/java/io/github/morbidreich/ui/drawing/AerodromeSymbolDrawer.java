package io.github.morbidreich.ui.drawing;

import java.awt.*;

public class AerodromeSymbolDrawer {
    public static void draw(int x, int y, Graphics g) {
        int dim1 = 6;
        int dim2 = 8;

        g.drawOval(x -dim1, y -dim1, dim1 * 2, dim1 * 2);
        g.drawLine(x + dim1, y, x + dim2, y);
        g.drawLine(x - dim1, y, x - dim2, y);
        g.drawLine(x, y + dim1, x, y + dim2);
        g.drawLine(x, y - dim1, x, y - dim2);
    }
}
