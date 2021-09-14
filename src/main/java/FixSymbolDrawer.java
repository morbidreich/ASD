import java.awt.*;

public class FixSymbolDrawer {
    static void drawFixSymbol(int x, int y, Graphics g, Fix fix) {
        //for vfr fixes symbol is circle
        if (fix.getFixType() == FixType.VFR) {
            g.drawOval(x, y, 5, 5);
        } else if (fix.getFixType() == FixType.AERODROME) {
            
            int dim1 = 6;
            int dim2 = 8;
            
            g.drawOval(x-dim1, y-dim1, dim1 * 2, dim1 * 2);
            g.drawLine(x + dim1, y, x + dim2, y);
            g.drawLine(x - dim1, y, x - dim2, y);
            g.drawLine(x, y + dim1, x, y + dim2);
            g.drawLine(x, y - dim1, x, y - dim2);
        }
        //each other type's symbol is triangle
        else {
            g.drawLine(x, y - 4, x + 5, y + 3);
            g.drawLine(x + 5, y + 3, x - 5, y + 3);
            g.drawLine(x - 5, y + 3, x, y - 4);

        }

    }

}
