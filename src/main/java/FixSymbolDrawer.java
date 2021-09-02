import java.awt.*;

public class FixSymbolDrawer {
    static void drawFixSymbol(int x, int y, Graphics g, Fix fix) {
        //for vfr fixes symbol is circle
        if (fix.getFixType() == FixType.VFR) {
            g.drawOval(x,y,5,5);
        }
        //each other type's symbol is triangle
        else {
            g.drawLine(x, y-4, x+5, y+3);
            g.drawLine(x+5, y+3, x-5, y+3);
            g.drawLine(x-5, y+3, x, y-4);

        }

    }

}
