package io.github.morbidreich.utils;

import io.github.morbidreich.airspaceElements.FixType;
import io.github.morbidreich.airspaceElements.PolygonType;

import java.awt.*;

public class Colors {

    public Color BACKGROUND_COLOR = new Color(30,30,30, 255);
    public Color CTR_COLOR = new Color(250, 80, 120, 122);
    public Color TMA_COLOR = new Color(170, 170, 170, 122);
    public Color SID_COLOR = new Color(30, 90, 255, 122);
    public Color STAR_COLOR = new Color(184, 141, 71, 122);
    public Color RBL_COLOR = new Color(158, 195, 255, 220);
    public Color VFR_COLOR = new Color(255, 250, 0, 122);
    public Color FIX_COLOR = new Color(200, 200, 200, 122);
    public Color ACC_FIX_COLOR = new Color(255, 255,122, 122);
    public Color AERODROME_COLOR = new Color(200, 200, 200, 122);
    public Color BORDER_COLOR = new Color(120, 120, 120, 122);
    public Color RIVER_COLOR = new Color(0, 0, 255, 122);
    public Color TOWN_COLOR = new Color(0, 80, 0, 122);
    public Color ROAD_COLOR = new Color(0, 0, 255, 122);
    public Color D_COLOR = new Color(200, 0, 0, 122);
    public Color P_COLOR = new Color(255, 195, 0, 122);
    public Color R_COLOR = new Color(0, 150, 0, 122);
    public Color TSA_COLOR = new Color(250, 90, 0, 122);
    public Color TRA_COLOR = new Color(150, 150, 150, 122);
    public Color AREA_COLOR = new Color(150, 150, 150, 122);
    public Color SEARCH_RESULT_COLOR = new Color(250,90,0,255);
    public static Color TRACK_COLOR = new Color(0,255,0,255);
    public static Color TRACK_COLOR_SPI = new Color(0,190,255);
    public static Color TRACK_COLOR_DROPING = new Color(50,50,50);


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
            case SEARCH_RESULT: return SEARCH_RESULT_COLOR;

            default: return new Color(255,255,122);
        }
    }

    public void setColor(PolygonType pt, Color color) {
        switch (pt) {
            case CTR -> {
                CTR_COLOR = color;
            }
            case TMA -> {
                TMA_COLOR = color;
            }
            case SID -> {
                SID_COLOR = color;
            }
            case STAR -> {
                STAR_COLOR = color;
            }
            case VFR -> {
                VFR_COLOR = color;
            }
            case FIX -> {
                FIX_COLOR = color;
            }
            case RIVER -> {
                RIVER_COLOR = color;
            }
            case TOWN -> {
                TOWN_COLOR = color;
            }
            case ROAD -> {
                ROAD_COLOR = color;
            }
            case BORDER -> {
                BORDER_COLOR = color;
            }
            case TSA -> {
                TSA_COLOR = color;
            }
            case TRA -> {
                TRA_COLOR = color;
            }
            case AREA -> {
                AREA_COLOR = color;
            }
            case P -> {
                P_COLOR = color;
            }
            case R -> {
                R_COLOR = color;
            }
            case D -> {
                D_COLOR = color;
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
            case DME:
            case AERODROME:
                return AERODROME_COLOR;
            case ACC_FIX: return ACC_FIX_COLOR;
            case VFR: return VFR_COLOR;
            case TOWN: return TOWN_COLOR;
            case SEARCH_RESULT: return SEARCH_RESULT_COLOR;
            case UNDEFINED:
            default: return new Color(255,255,122);
        }
    }
    public void setColor(FixType fixType, Color color) {
        switch (fixType) {
            case SID01, SID19, DER -> SID_COLOR = color;
            case STAR01, STAR19 -> STAR_COLOR = color;
            case VFR -> VFR_COLOR = color;
            case ENTRY, VOR, AERODROME -> AERODROME_COLOR = color;
            case ACC_FIX -> ACC_FIX_COLOR = color;
            case TOWN -> TOWN_COLOR = color;
            case UNDEFINED -> {}
        }
    }
}
