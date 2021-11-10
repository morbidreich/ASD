package io.github.morbidreich.playground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempTest {
    public static void main(String[] args) {

        double deg = 225;

        double rad = Math.toRadians(deg);
        System.out.println(Math.sin(rad));
        System.out.println(Math.cos(rad));


    }
}
