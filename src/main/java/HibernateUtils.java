
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private HibernateUtils() {
    }

    public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(TempPoint.class)
            .addAnnotatedClass(TempPolygon.class)
            .addAnnotatedClass(TempFix.class)
            .buildSessionFactory();
}
