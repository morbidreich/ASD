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

        label = new JLabel("This is some important informations This is some important informations This is some important informations This is some important informations This is some important informations This is some important informations v This is some important informations This is some important informations This is some important informations This is some important informationsThis is some important informationsvv ");

        AboutPanel myPanel = new AboutPanel();

        myPanel.add(label);
        add(myPanel);

        pack();
        setVisible(true);
    }

    class AboutPanel extends JPanel {

        JLabel label;

        public AboutPanel() {
            super();
            label = new JLabel("it is working");
            add(label);
        }
    }

    //for quicker testing slider events
    public static void main(String[] args) {
        AboutWindow aw = new AboutWindow();
    }
}
