import java.awt.*;

public class Colors {
    public static final Color BACKGROUND_COLOR = new Color(30,30,30);
    public static final Color CTR_COLOR = new Color(128, 51, 0);
    public static final Color TMA_COLOR = new Color(71, 71, 71);
    public static final Color SID_COLOR = new Color(30, 90, 225);
    public static final Color STAR_COLOR = new Color(184, 141, 71);
    public static final Color RBL_COLOR = new Color(158, 195, 255);
    public static final Color VFR_COLOR = new Color(220, 220, 0);
    public static final Color FIX_COLOR = new Color(200, 200, 200);
    public static final Color RIVER_COLOR = new Color(0, 0, 255);
    public static final Color TOWN_COLOR = new Color(0, 0, 255);
    public static final Color ROAD_COLOR = new Color(0, 0, 255);
    public static final Color D_COLOR = new Color(200, 0, 0);
    public static final Color P_COLOR = new Color(255, 195, 0);
    public static final Color R_COLOR = new Color(0, 150, 0);
    public static final Color TSA_COLOR = new Color(150, 100, 10);
    public static final Color TRA_COLOR = new Color(150, 150, 150);
    public static final Color AREA_COLOR = new Color(150, 150, 150);

    public static final Color getColor(PolygonType pt) {
        switch (pt) {
            case CTR: return CTR_COLOR;
            case TMA: return TMA_COLOR;
            case SID: return SID_COLOR;
            case STAR: return STAR_COLOR;
            case VFR: return VFR_COLOR;
            case FIX: return FIX_COLOR;
            case RIVER: return RIVER_COLOR;
            case TOWN: return TOWN_COLOR;
            case ROAD: return ROAD_COLOR;
            case D: return D_COLOR;
            case P: return P_COLOR;
            case R: return R_COLOR;
            case TRA: return TRA_COLOR;
            case TSA: return TSA_COLOR;
            case AREA: return AREA_COLOR;

            default: return new Color(255,255,255);
        }
    }

    public static final Color getColor(FixType ft) {
        switch (ft) {
            case SID01:
            case SID19:
            case DER:
                return SID_COLOR;
            case STAR01:
            case STAR19:
                return STAR_COLOR;
            case ENTRY:
            case VOR:
            case AERODROME:
                return TMA_COLOR;
            case VFR: return VFR_COLOR;
            case TOWN: return TOWN_COLOR;
            case UNDEFINED:
            default: return new Color(255,255,255);
        }


    }
}
