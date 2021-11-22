package io.github.morbidreich.ui;

import io.github.morbidreich.utils.SettingsManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * infobox based on https://docs.oracle.com/javase/tutorial/uiswing/components/html.html
 */
public class AboutWindow extends JFrame {

    public AboutWindow() {

        super("About app");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));

        AboutPanel ap = new AboutPanel();
        add(ap);
        pack();
        setVisible(true);


    }

    //for quicker testing slider events
    public static void main(String[] args) {
        AboutWindow aw = new AboutWindow();
    }

    class AboutPanel extends JPanel {

        JLabel label;

        public AboutPanel() {
            String version = SettingsManager.getInstance().get("version");
            String infoText = "<html>\n" +
                    "<h1 align=center>Airspace Display " + version + "</h1>\n" +
                    "<p>Pomocnicza aplikacja do wizualizacji ruchu lotniczego, elementów przestrzeni powietrznej, " +
                    "procedur, miast, lotnisk etc, stworzona z myślą o wypełnieniu luki spowodowanej brakiem P21 w EPSY<br>" +
                    "<ul>\n" +
                    "<li>Dane ADS-B pochodzą z <b>www.opensky-network.org</b>" +
                    "<li>Dane przestrzeni - <b>AIRAC 2111</b>" +
                    "</ul>" +
                    "Mimo najwyższej troski o dokładność i aktualność danych, aplikacja nie jest do użytku operacyjnego, ale to każdy zapewne wie;)</p>" +
                    "<br><br>W razie jakichkolwiek sugestii, spostrzerzeń czy błędów proszę o kontakt: <br><a href=\"mailto:bartlomiej.kujda@pansa.pl\">bartlomiej.kujda@pansa.pl</a>\n" +
                    "</html>";


            //htmlTextArea = new JTextArea(10,20);
            //htmlTextArea.setText(infoText);

            label = new JLabel(infoText) {
                public Dimension getPreferredSize() {
                    return new Dimension(450, 300);
                }

                public Dimension getMinimumSize() {
                    return new Dimension(450, 300);
                }

                public Dimension getMaximumSize() {
                    return new Dimension(450, 300);
                }
            };
            label.setVerticalAlignment(SwingConstants.TOP);
            label.setHorizontalAlignment(SwingConstants.LEFT);
            add(label);

        }
    }
}
