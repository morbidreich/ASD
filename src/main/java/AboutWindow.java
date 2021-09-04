import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

        AboutPanel myPanel = new AboutPanel();

        //myPanel.add(label);
        add(myPanel);

        pack();
        setVisible(true);


    }

    class AboutPanel extends JPanel implements ChangeListener {

        public AboutPanel() {
            super();
            JSlider slider = new JSlider(SwingConstants.HORIZONTAL);
            slider.addChangeListener(this);
            add(slider, BorderLayout.SOUTH);
            JLabel label = new JLabel(String.valueOf(slider.getValue()));
            add(label);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(10,10,100,100);


        }

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            label.setText(String.valueOf(slider.getValue()));
            repaint();

        }
    }
}
