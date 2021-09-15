import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BrightnessSlider implements ChangeListener {
    private PolygonType polygonType;
    private MapPanel mapPanel;
    private Color reference;

    public BrightnessSlider(PolygonType polygonType, MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        this.polygonType = polygonType;
        this.reference = mapPanel.getColors().getColor(polygonType);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider s = (JSlider) e.getSource();

        int multiplier = s.getValue();

        Color newColor = new Color(reference.getRed(), reference.getGreen(), reference.getBlue(), reference.getAlpha() + multiplier);

        mapPanel.getColors().setColor(polygonType, newColor);

        mapPanel.repaint();
    }
}
