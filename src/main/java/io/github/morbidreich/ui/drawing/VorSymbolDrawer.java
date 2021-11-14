package io.github.morbidreich.ui.drawing;

import java.awt.*;

public class VorSymbolDrawer {
    public static void draw(Graphics g, int x, int y) {
        // fix name is drawn 10px right of x, make sure to fit symbol before name
        //vor symbol is hexagon with dot inside

        // keep a:b ratio as 2:3
        int a = 4;
        int b = 6;

        g.drawOval(x - a / 2, y - a / 2, a, a);

        //outline
        Polygon p = new Polygon();
        p.addPoint(x - a, y - b);
        p.addPoint(x + a, y - b);
        p.addPoint(x + 2 * a, y);
        p.addPoint(x + a, y + b);
        p.addPoint(x - a, y + b);
        p.addPoint(x - 2 * a, y);
        p.addPoint(x - a, y - b);

        g.drawPolygon(p);
    }
}
