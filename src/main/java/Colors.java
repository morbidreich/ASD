import java.awt.*;

public class Colors {

    public Color BACKGROUND_COLOR = new Color(30,30,30, 255);
    public Color CTR_COLOR = new Color(250, 170, 10, 122);
    public Color TMA_COLOR = new Color(170, 170, 170, 122);
    public Color SID_COLOR = new Color(30, 90, 225, 122);
    public Color STAR_COLOR = new Color(184, 141, 71, 122);
    public Color RBL_COLOR = new Color(158, 195, 255, 220);
    public Color VFR_COLOR = new Color(220, 220, 0, 122);
    public Color FIX_COLOR = new Color(200, 200, 200, 122);
    public Color AERODROME_COLOR = new Color(200, 200, 200, 122);
    public Color BORDER_COLOR = new Color(0, 200, 0, 122);
    public Color RIVER_COLOR = new Color(0, 0, 255, 122);
    public Color TOWN_COLOR = new Color(0, 0, 255, 122);
    public Color ROAD_COLOR = new Color(0, 0, 255, 122);
    public Color D_COLOR = new Color(200, 0, 0, 122);
    public Color P_COLOR = new Color(255, 195, 0, 122);
    public Color R_COLOR = new Color(0, 150, 0, 122);
    public Color TSA_COLOR = new Color(150, 100, 10, 122);
    public Color TRA_COLOR = new Color(150, 150, 150, 122);
    public Color AREA_COLOR = new Color(150, 150, 150, 122);


    public Color getColor(PolygonType pt) {
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
            case BORDER: return BORDER_COLOR;
            case D: return D_COLOR;
            case P: return P_COLOR;
            case R: return R_COLOR;
            case TRA: return TRA_COLOR;
            case TSA: return TSA_COLOR;
            case AREA: return AREA_COLOR;

            default: return new Color(255,255,122);
        }
    }

    public void setColor(PolygonType pt, Color color) {
        switch (pt) {
            case CTR -> {
                CTR_COLOR = color;
                break;
            }
            case TMA -> {
                TMA_COLOR = color;
                break;
            }
            case SID -> {
                SID_COLOR = color;
                break;
            }
            case STAR -> {
                STAR_COLOR = color;
                break;
            }
            case VFR -> {
                VFR_COLOR = color;
                break;
            }
            case FIX -> {
                FIX_COLOR = color;
                break;
            }
            case RIVER -> {
                RIVER_COLOR = color;
                break;
            }
            case TOWN -> {
                TOWN_COLOR = color;
                break;
            }
            case ROAD -> {
                ROAD_COLOR = color;
                break;
            }
            case BORDER -> {
                BORDER_COLOR = color;
                break;
            }
            case TSA -> {
                TSA_COLOR = color;
                break;
            }
            case TRA -> {
                TRA_COLOR = color;
                break;
            }
            case AREA -> {
                AREA_COLOR = color;
                break;
            }
            case P -> {
                P_COLOR = color;
                break;
            }
            case R -> {
                R_COLOR = color;
                break;
            }
            case D -> {
                D_COLOR = color;
                break;
            }
            case UNDEFINED -> {
            }
        }
    }


    public Color getColor(FixType ft) {
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
                return AERODROME_COLOR;
            case VFR: return VFR_COLOR;
            case TOWN: return TOWN_COLOR;
            case UNDEFINED:
            default: return new Color(255,255,122);
        }
    }
    public void setColor(FixType fixType, Color color) {
        switch (fixType) {
            case ENTRY -> {
                TMA_COLOR = color;
                break;
            }
            case SID01 -> {
                SID_COLOR = color;
                break;
            }
            case SID19 -> {
                SID_COLOR = color;
                break;
            }
            case DER -> {
                SID_COLOR = color;
                break;
            }
            case STAR01 -> {
                STAR_COLOR = color;
                break;
            }
            case STAR19 -> {
                STAR_COLOR = color;
                break;
            }
            case VFR -> {
                VFR_COLOR = color;
                break;
            }
            case VOR -> {
                AERODROME_COLOR = color;
                break;
            }
            case AERODROME -> {
                AERODROME_COLOR = color;
                break;
            }
            case TOWN -> {
                TOWN_COLOR = color;
                break;
            }
            case UNDEFINED -> {
                break;
            }
        }
    }
}
