package io.github.morbidreich.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AboutWindow extends JFrame {
    JLabel label;

    public AboutWindow() {

        super("About app");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));

        setLayout(new BorderLayout());



        //label = new JLabel("This is some important informations This is some important informations This is some important informations This is some important informations This is some important informations This is some important informations v This is some important informations This is some important informations This is some important informations This is some important informationsThis is some important informationsvv ");

        AboutPanel myPanel = new AboutPanel();

        //myPanel.add(label);
        add(myPanel);

        pack();
        setVisible(true);
    }

    class AboutPanel extends JPanel {

        JLabel label;

        public AboutPanel() {
            super();
            //label = new JLabel("it is working");
            //add(label);
            JTextArea jta = new JTextArea(" lorem ipsum ajosdakjsd ahsdhasd asd ansd nasd as da sd  a sd  asdasdasdasd  asd asd asd   ad asd a sd ad   ");
            add(jta);
        }
    }

    //for quicker testing slider events
    public static void main(String[] args) {
        AboutWindow aw = new AboutWindow();
    }
}
