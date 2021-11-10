package io.github.morbidreich.utils;

import io.github.morbidreich.airspaceElements.Fix;
import io.github.morbidreich.airspaceElements.Point;
import io.github.morbidreich.airspaceElements.Polygon;
import io.github.morbidreich.airspaceElements.Procedure;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private HibernateUtils() {
    }

    public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(Point.class)
            .addAnnotatedClass(Polygon.class)
            .addAnnotatedClass(Fix.class)
            .addAnnotatedClass(Procedure.class)
            .buildSessionFactory();
}
