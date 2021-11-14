package io.github.morbidreich.ui.drawing;

import java.awt.*;

public class DefaultSymbolDrawer {
    public static void draw(Graphics g, int x, int y) {
        g.drawLine(x, y - 4, x + 5, y + 3);
        g.drawLine(x + 5, y + 3, x - 5, y + 3);
        g.drawLine(x - 5, y + 3, x, y - 4);
    }
}
