import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateDataReader implements AirspaceReader{
    @Override
    public Airspace readAirspace() {
        Airspace airspace = new Airspace();

        Session session = HibernateUtils.factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Polygon> polygonList = session.createQuery("from Polygon").getResultList();
            airspace.setPolygonList(polygonList);

            List<Fix> fixList = session.createQuery("from Fixes").getResultList();
            airspace.setFixList(fixList);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return airspace;
    }
}
