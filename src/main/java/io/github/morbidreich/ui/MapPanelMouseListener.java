package io.github.morbidreich.ui;

import io.github.morbidreich.airspaceElements.BasePoint;
import io.github.morbidreich.airspaceElements.RBL;
import io.github.morbidreich.surveilance.Track;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

class MapPanelMouseListener implements MouseListener, MouseMotionListener {
    private final MapPanel mapPanel;

    // variables for handling moving map and drawing rbls
    private int dragOriginX, dragOriginY;
    private double dragOriginOEasting, dragOriginONorthing;

    //variables for handling label dragging
    private Track track;

    private int originX, originY;
    private int originalDisplacementX, originalDisplacementY;

    private boolean isLabelDragging = false;

    public MapPanelMouseListener(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {
            handleLeftMousePress(e);

        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (!mapPanel.isDrawingRBL()) {
                // detect if clicked inside RBL label. If so then delete clicked RBL
                // if not draw another RBL
                if (mapPanel.tryDeleteRBL(e)) {
                    return;
                }

                mapPanel.setDrawingRBL(true);
                RBL rbl = new RBL();
                BasePoint endPoint = new BasePoint();

                //i check if right clicked on empty space or on track label.
                // if clicked on track, then set reference to that track as start point of RBL
                // dynamic position - beginning of RBL will follow assigned tracks position

                Optional<Track> tr = getClickedTrack(e);
                if (tr.isPresent()) {
                    Track t = tr.get();
                    rbl.setStartTrack(t);
                }
                // if clicked on empty space then set it's location as beginning of RBL
                // static position, will not update
                else {
                    BasePoint startPoint = new BasePoint();

                    startPoint.setNorthing(mapPanel.convertNorthing(e.getY()));
                    startPoint.setEasting(mapPanel.convertEasting(e.getX()));

                    rbl.setStartPoint(startPoint);
                }

                endPoint.setNorthing(mapPanel.convertNorthing(e.getY()));
                endPoint.setEasting(mapPanel.convertEasting(e.getX()));
                rbl.setEndPoint(endPoint);

                mapPanel.rbls.add(rbl);

            } else {
                // occurs with second click, finishes drawing RBL
                // handle differently depending on clicking tracks label or empty space
                Optional<Track> tr = getClickedTrack(e);
                if (tr.isPresent()) {
                    // we are drawing rbl that is already stored in rbls List. Get that instance and
                    // update its ending position as reference to track, whose label was clicked
                    mapPanel.rbls.get(mapPanel.rbls.size() - 1).setEndTrack(tr.get());
                }
                //clicked empty space, set cursor location during click as end point
                mapPanel.setDrawingRBL(false);
            }
            mapPanel.repaint();
        }
    }

    private void handleLeftMousePress(MouseEvent e) {
        Optional<Track> tr = getClickedTrack(e);
        if (tr.isPresent()) {
            isLabelDragging = true;
            handleLabelMousePress(e, tr);
        } else {
            isLabelDragging = false;
            handleMapPanelMousePress(e);
        }
    }

    private void handleMapPanelMousePress(MouseEvent e) {
        // handling panning
        dragOriginX = e.getX();
        dragOriginY = e.getY();
        dragOriginOEasting = mapPanel.getoEasting();
        dragOriginONorthing = mapPanel.getoNorthing();
    }

    private void handleLabelMousePress(MouseEvent e, Optional<Track> tr) {
        track = tr.get();

        originX = e.getX();

        originY = e.getY();
        originalDisplacementX = track.getTrackLabel().getDisplacementX();
        originalDisplacementY = track.getTrackLabel().getDisplacementY();
    }

    /**
     * get Track object detected by clicking at it's label
     *
     * @param e MouseEvent
     * @return Track whose label was clicked
     */
    private Optional<Track> getClickedTrack(MouseEvent e) {
        return mapPanel.tracks.stream()
                .filter(t -> t.getTrackLabel().isMouseOver(e))
                .findFirst();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {

            if (isLabelDragging) {

                int deltaX = e.getX() - originX + originalDisplacementX;
                int deltaY = e.getY() - originY + originalDisplacementY;

                track.getTrackLabel().setDisplacementX(deltaX);
                track.getTrackLabel().setDisplacementY(deltaY);
            } else {
                int deltaX = e.getX() - dragOriginX;
                int deltaY = e.getY() - dragOriginY;

                mapPanel.setoEasting(dragOriginOEasting - deltaX / mapPanel.getScale());
                mapPanel.setoNorthing(dragOriginONorthing + deltaY / mapPanel.getScale());
            }
            mapPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (mapPanel.isDrawingRBL()) {
            // handle drawing RBL
            mapPanel.cursorX = e.getX();
            mapPanel.cursorY = e.getY();
            // check if list consist any rbls.
            if (mapPanel.rbls.size() > 0) {
                mapPanel.rbls.get(mapPanel.rbls.size() - 1).getEndPoint().setNorthing(mapPanel.convertNorthing(mapPanel.cursorY));
                mapPanel.rbls.get(mapPanel.rbls.size() - 1).getEndPoint().setEasting(mapPanel.convertEasting(mapPanel.cursorX));
            }

        }
        // handle label dragging
        Track tempTrack = null;
        Optional<Track> tr = getClickedTrack(e);
        if (tr.isPresent()) {
            tempTrack = tr.get();
            tempTrack.getTrackLabel().setMouseOver(true);

        }
        for (Track t : mapPanel.tracks) {
            if (t.getTrackLabel().isMouseOver() && !t.equals(tempTrack))
                t.getTrackLabel().setMouseOver(false);
        }
        mapPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
