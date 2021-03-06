package io.github.morbidreich.jpa;

import io.github.morbidreich.airspaceElements.Airspace;
import io.github.morbidreich.airspaceElements.Fix;
import io.github.morbidreich.airspaceElements.Polygon;
import io.github.morbidreich.airspaceElements.Procedure;
import io.github.morbidreich.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class HibernateDataReader implements AirspaceReader {
    @Override
    public Airspace readAirspace() {
        Airspace airspace = new Airspace();

        Session session = HibernateUtils.factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Polygon> polygonList = session.createQuery("from Polygon", Polygon.class).getResultList();
            airspace.setPolygonList(polygonList);

            // not sure why, but i get error message when executing two queries without closing session
            // and creating new one in between (org.hibernate.WrongClassException: Object [id=1] was not of
            // the specified subclass [TempFix] : loaded object was of wrong class class TempPoint)
            // closing and reopening session seems to help

            session.getTransaction().commit();
            session = HibernateUtils.factory.getCurrentSession();
            session.beginTransaction();

            airspace.setFixList(session.createQuery("from Fix", Fix.class).getResultList());

            airspace.setProcedureList(session.createQuery("from Procedure", Procedure.class).getResultList());
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
