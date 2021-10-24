package io.github.morbidreich;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class AboutWindow extends JFrame {
    JLabel label;

    public AboutWindow() {

        super("About app");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));

        label = new JLabel("This is some important informations");

        AboutPanel myPanel = new AboutPanel();

        //myPanel.add(label);
        add(myPanel);

        pack();
        setVisible(true);


    }

    class AboutPanel extends JPanel implements ChangeListener {

        JSlider slider;
        JLabel label;
        int x = 100;
        int y = 100;

        public AboutPanel() {
            super();
            slider = new JSlider(SwingConstants.HORIZONTAL);
            slider.setPaintTicks(true);
            //slider.setMajorTickSpacing(30);
            slider.setMinorTickSpacing(10);
            slider.addChangeListener(this);
            add(slider, BorderLayout.SOUTH);
            label = new JLabel("it is working");
            add(label);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(10,10,x,y);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            label.setText("Val " + slider.getValue());
            x = slider.getValue();
            y = slider.getValue() * 2;

            repaint();
            System.out.println(x + " " + y);
        }
    }

    //for quicker testing slider events
    public static void main(String[] args) {
        AboutWindow aw = new AboutWindow();
    }
}
