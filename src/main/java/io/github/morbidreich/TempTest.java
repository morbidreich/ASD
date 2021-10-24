package io.github.morbidreich;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TempTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(BasePoint.class)
                .addAnnotatedClass(Fix.class)
                .addAnnotatedClass(Point.class)
                .addAnnotatedClass(Polygon.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            //get session
            session.beginTransaction();

            List<Polygon> polygons = session.createQuery("from io.github.morbidreich.Polygon").getResultList();
            List<Point> points = session.createQuery("from io.github.morbidreich.Point").getResultList();

            session.getTransaction().commit();
            session.close();

            session = factory.getCurrentSession();
            session.beginTransaction();

            List<Fix> fixes = session.createQuery("from io.github.morbidreich.Fix").getResultList();
            for (Fix f : fixes)
                System.out.println(f);

            //System.out.println(polygons);
            //System.out.println(polygons.get(0).getPointList());

//            session.getTransaction().commit();
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//
//
//            List<TempFix> fixes = session.createQuery("from TempFix").getResultList();
//
//            System.out.println(fixes);


            session.getTransaction().commit();

            // try loading fixes/points
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
    }
}
