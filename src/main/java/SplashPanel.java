import javax.swing.*;
import java.awt.*;

// TODO display this panel while hibernate initializes, maybe add progress bar or other progress messages
public class SplashPanel extends JPanel {
    public SplashPanel() {
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1000, 800));
        JLabel sign = new JLabel("Loading...");
        add(sign);

    }
}
