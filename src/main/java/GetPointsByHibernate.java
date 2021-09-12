import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetPointsByHibernate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Point.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            // get point
            int id = 1;
            Point pt = session.get(Point.class, id);

            //sout northing/easting
            System.out.println("northing:" + pt.getNorthing() + ", easting:" + pt.getEasting());

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
