import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TempTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(TempCoords.class)
                .addAnnotatedClass(TempFix.class)
                .addAnnotatedClass(TempPoint.class)
                .addAnnotatedClass(TempPolygon.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            //get session
            session.beginTransaction();

            List<TempPolygon> polygons = session.createQuery("from TempPolygon").getResultList();
            List<TempPoint> points = session.createQuery("from TempPoint").getResultList();

            session.getTransaction().commit();
            session.close();

            session = factory.getCurrentSession();
            session.beginTransaction();

            List<TempFix> fixes = session.createQuery("from TempFix").getResultList();
            for (TempFix f : fixes)
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
