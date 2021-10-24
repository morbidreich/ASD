package io.github.morbidreich;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GetPolygonsByHibernate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Point.class)
                .addAnnotatedClass(Polygon.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            // get point
            int id = 1;
            Polygon poly = session.get(Polygon.class, id);
            List<Polygon> polyList = session.createQuery("from io.github.morbidreich.Polygon").getResultList();
            System.out.println(polyList + "\n\n");

            //sout northing/easting
            System.out.println(poly);
            System.out.println(poly.getPointList());

            //close session
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }



    }
}
