package io.github.morbidreich.ui;

import javax.swing.*;

// utility wrapper to decrease code complexity in Menu class
public class asdCheckBox extends JCheckBoxMenuItem {
    private JSlider slider;

    public asdCheckBox(String text, JSlider slider) {
        super(text);
        this.slider = slider;
    }

    public JSlider getSlider() {
        return slider;
    }
    public void setSlider(JSlider slider) {
        this.slider = slider;
    }
}
