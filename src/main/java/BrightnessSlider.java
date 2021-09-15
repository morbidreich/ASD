import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BrightnessSlider implements ChangeListener {
    private PolygonType polygonType;
    private MapPanel mapPanel;
    public BrightnessSlider(PolygonType polygonType, MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        this.polygonType = polygonType;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider js = (JSlider) e.getSource();
        System.out.println(js.getValue());
    }
}
