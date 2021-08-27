import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JFrame {
    JPanel panel;
    JLabel label;

    public AboutWindow() {

        super("About app");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));
        label = new JLabel("This is some important informations");
        panel = new JPanel();

        panel.add(label);
        add(panel);

        pack();
        setVisible(true);

    }
}
